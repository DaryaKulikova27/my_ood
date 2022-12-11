#include "CController.h"
#include "CCompositeShape.h"
#include <algorithm>
#include <sstream>
#include <string>
#include <cmath>

CController::CController(std::istream& input, std::ostream& output)
	: m_input(input)
	, m_output(output)
	, m_shapeList()
	, m_shapeBorder()
	, m_selectedShapeSet()
	, m_actionMap({
			{ "RECTANGLE", [this](std::istream& strm) {
			   return AddRectangle(strm);
			} },
			{ "TRIANGLE", [this](std::istream& strm) {
			   return AddTRiangle(strm);
			} },
			{ "CIRCLE", [this](std::istream& strm) {
			   return AddCircle(strm);
			} },
			{ "Info", [this](std::istream& strm) {
				return Info();
			} },
			{ "PrintInfoShapes", [this](std::istream& strm) {
				return PrintInfoShapes();
			} },
			{ "Draw", [this](std::istream& strm) {
				return Draw();
			} }
}) {
	m_touchState = IDLE;
	m_shapeBorder = std::make_unique<sf::RectangleShape>();
	m_shapeBorder->setSize({ 0, 0 });
	m_shapeBorder->setPosition({ 0, 0 });
	m_shapeBorder->setOutlineThickness(1);
	m_shapeBorder->setOutlineColor(sf::Color::Cyan);
	m_shapeBorder->setFillColor(sf::Color::Transparent);
};

bool CController::HandleCommand() const
{
	std::string commandLine;
	getline(m_input, commandLine);
	std::istringstream strm(commandLine);

	std::string action;
	strm >> action;

	auto it = m_actionMap.find(action);
	if (it != m_actionMap.end())
	{
		return it->second(strm);
	}
	return false;
}

bool CController::Info() const
{
	m_output << "If you want to add rectangle, write \"RECTANGLE <left top (x, y)> <right bottom (x, y)> <outline color> <fill color>\"" << std::endl;
	m_output << "If you want to add triangle, write \"TRIANGLE <x1> <y1> <x2> <y2> <x3> <y3> <outline color> <fill color>\"" << std::endl;
	m_output << "If you want to add circle, write \"CIRCLE <x> <y> <radius> <outline color> <fill color>\"" << std::endl;
	m_output << "If you want to get info about shapes, write \"PrintInfoShapes\"" << std::endl;
	m_output << "If you want to know this info, write \"Info\"" << std::endl;
	m_output << "If you want to draw shapes, write \"Draw\"" << std::endl;
	return true;
}

bool CController::PrintInfoShapes() const
{
	for (auto shape : m_shapeList)
	{
		m_output << shape->ToString();
		m_output << std::endl;
	}
	m_output << std::endl;
	return true;
}

bool CController::AddRectangle(std::istream& args)
{
	std::string x1, y1, x2, y2;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x1 >> y1 >> x2 >> y2 >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	sf::Vector2f leftTop = { std::stof(x1), std::stof(y1) };
	sf::Vector2f rightBottom = { std::stof(x2), std::stof(y2) };

	auto rectangle = std::make_unique<sf::RectangleShape>();
	rectangle->setSize({ abs(std::stof(x2) - std::stof(x1)) , abs(std::stof(y1) - std::stof(y2))});
	rectangle->setPosition(leftTop);
	rectangle->setOutlineThickness(5);
	rectangle->setOutlineColor(GetColor(outlineColor));
	rectangle->setFillColor(GetColor(fillColor));

	auto shape = std::make_shared<CRectangleDecorator>(std::move(rectangle), leftTop, rightBottom, GetColor(fillColor), GetColor(outlineColor));
	m_shapeList.insert(std::move(shape));
	return true;
}

bool CController::AddTRiangle(std::istream& args)
{
	std::string x1, y1, x2, y2, x3, y3;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
	args >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	sf::Vector2f vertex1 = { std::stof(x1), std::stof(y1) };
	sf::Vector2f vertex2 = { std::stof(x2), std::stof(y2) };
	sf::Vector2f vertex3 = { std::stof(x3), std::stof(y3) };

	auto triangle = std::make_unique<sf::ConvexShape>();
	triangle->setPointCount(3);
	triangle->setPoint(0, vertex1);
	triangle->setPoint(1, vertex2);
	triangle->setPoint(2, vertex3);
	triangle->setOutlineThickness(5);
	triangle->setOutlineColor(GetColor(outlineColor));
	triangle->setFillColor(GetColor(fillColor));

	auto shape = std::make_shared<CTriangleDecorator>(std::move(triangle), vertex1, vertex2, vertex3, GetColor(fillColor), GetColor(outlineColor));
	m_shapeList.insert(std::move(shape));
	return true;
}

bool CController::AddCircle(std::istream& args)
{
	std::string x, y;
	int radius;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x >> y >> radius;
	args >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	sf::Vector2f center = { std::stof(x), std::stof(y) };

	auto circle = std::make_unique<sf::CircleShape>();
	circle->setPosition(center);
	circle->setRadius(radius);
	circle->setOutlineThickness(5);
	circle->setOutlineColor(GetColor(outlineColor));
	circle->setFillColor(GetColor(fillColor));

	auto shape = std::make_shared<CCircleDecorator>(std::move(circle), center, radius, GetColor(fillColor), GetColor(outlineColor));
	m_shapeList.insert(std::move(shape));
	return true;
}

bool CController::Draw()
{
	sf::RenderWindow window(sf::VideoMode(800, 600), "Result");
	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			switch (event.type) {
			case sf::Event::Closed:
				window.close();
				break;
			case sf::Event::KeyPressed:
				if (event.key.code == sf::Keyboard::G && event.key.control)
					if (m_selectedShapeSet.size() > 0)
					{
						for (auto element : m_selectedShapeSet)
							m_shapeList.erase(element);
						m_shapeList.insert(std::make_shared<CCompositeShape>(m_selectedShapeSet));
					}
				
				break;
			case sf::Event::MouseButtonPressed:
				if (event.key.code == sf::Mouse::Left)
				{
					sf::Vector2f point = { (float)event.mouseButton.x, (float)event.mouseButton.y };
					auto shape = GetTouchedShape(point);
					if (shape)
						m_touchState = TOUCH_DOWN_ON_OBJECT;
					m_previousTouchPoint = point;
				}
				break;
			case sf::Event::MouseButtonReleased:
				if (event.key.code == sf::Mouse::Left)
				{
					if (m_touchState != TOUCH_MOVED) {
						sf::Vector2f point = { (float)event.mouseButton.x, (float)event.mouseButton.y };
						auto shape = GetTouchedShape(point);
						if (shape) {
							if (!sf::Keyboard::isKeyPressed(sf::Keyboard::LShift) && !sf::Keyboard::isKeyPressed(sf::Keyboard::RShift))
								m_selectedShapeSet.clear();
							m_selectedShapeSet.insert(*shape);
						}
						else
							m_selectedShapeSet.clear();
					}
					m_touchState = IDLE;
				}
				break;
			case sf::Event::MouseMoved:
				if (m_touchState == TOUCH_DOWN_ON_OBJECT || m_touchState == TOUCH_MOVED)
				{
					sf::Vector2f newPoint = { (float)event.mouseMove.x, (float)event.mouseMove.y };
					for (auto& shape : m_selectedShapeSet)
						shape->Move(newPoint - m_previousTouchPoint);
					m_previousTouchPoint = newPoint;
					m_touchState = TOUCH_MOVED;
				}
				break;
			}
			std::cout << event.type;
		}

		window.clear(sf::Color::White);
		for (auto& shape : m_shapeList)
			shape->Draw(window);

		if (m_selectedShapeSet.size() > 0) {
			auto bounds = (*m_selectedShapeSet.begin())->GetShapeBounds();
			for (auto& shape : m_selectedShapeSet)
				bounds = CombineRects(bounds, shape->GetShapeBounds());
			m_shapeBorder->setPosition({ bounds.left, bounds.top });
			m_shapeBorder->setSize({ bounds.width, bounds.height });
			window.draw(*m_shapeBorder);
		}
		
		window.display();
	}
	return true;
}

std::optional<std::shared_ptr<CShape>> CController::GetTouchedShape(sf::Vector2f point)
{
	for (auto& shape : m_shapeList)
		if (shape->GetShapeBounds().contains(point))
			return { shape };
	return std::nullopt;
}


sf::Color GetColor(uint32_t color)
{
	uint8_t blue = color % 256;
	uint8_t green = (color / 256) % 256;
	uint8_t red = ((color / 256) / 256) % 256;

	return sf::Color(red, green, blue);
}

sf::FloatRect CombineRects(sf::FloatRect first, sf::FloatRect second)
{
	sf::Vector2f min = { std::min(first.left, second.left), std::min(first.top, second.top) };
	sf::Vector2f max = { std::max(first.left + first.width, second.left + second.width), std::max(first.top + first.height, second.top + second.height) };

	return {
		min, max - min
	};
}