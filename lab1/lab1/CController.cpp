#include "CController.h"
#include <algorithm>
#include <sstream>

using namespace std::placeholders;
using namespace std;

CController::CController(std::istream& input, std::ostream& output)
	: m_input(input)
	, m_output(output)
	, m_shapeListSf()
	, m_actionMap({
			{ "RECTANGLE", [this](std::istream& strm) {
			   return AddRectangle(strm);
			} },
			{ "TRIANGLE", [this](istream& strm) {
			   return AddTRiangle(strm);
			} },
			{ "CIRCLE", [this](istream& strm) {
			   return AddCircle(strm);
			} },
			{ "Info", [this](istream& strm) {
				return Info();
			} },
			{ "PrintInfoShapes", [this](istream& strm) {
				return PrintInfoShapes();
			} },
			{ "Draw", [this](istream& strm) {
				return Draw();
			} }
}) {};

bool CController::HandleCommand() const
{
	std::string commandLine;
	getline(m_input, commandLine);
	istringstream strm(commandLine);

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
	m_output << "If youy want to get info about shapes, write \"PrintInfoShapes\"" << std::endl;
	m_output << "If you want to know this info, write \"Info\"" << std::endl;
	m_output << "If you want to draw shapes, write \"Draw\"" << std::endl;
	return true;
}

bool CController::PrintInfoShapes() const
{
	for (int i = 0; i < m_shapeListSf.size(); i++)
	{
		m_output << m_shapeListSf[i]->ToString();
		m_output << std::endl;
	}
	m_output << std::endl;
	return true;
}

bool CController::AddRectangle(std::istream& args)
{
	int x1, y1, x2, y2;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x1 >> y1 >> x2 >> y2 >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	auto shape = make_unique<sf::RectangleShape>();
	auto rectangle = make_unique<CRectangleDecorator>(std::move(shape), x1, y1, x2, y2, outlineColor, fillColor);
	m_shapeListSf.emplace_back(rectangle);
	return true;
}

bool CController::AddTRiangle(std::istream& args)
{
	int x1, y1, x2, y2, x3, y3;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
	args >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	auto shape = make_unique<sf::ConvexShape>();
	auto triangle = make_unique<CTriangleDecorator>(std::move(shape), x1, y1, x2, y2, x3, y3, outlineColor, fillColor);
	m_shapeListSf.emplace_back(triangle);
	return true;
}

bool CController::AddCircle(std::istream& args)
{
	double x, y, radius;
	std::string outlineColorStr;
	std::string fillColorStr;
	args >> x >> y >> radius;
	args >> outlineColorStr >> fillColorStr;
	uint32_t outlineColor = stoul(outlineColorStr, nullptr, 16);
	uint32_t fillColor = stoul(fillColorStr, nullptr, 16);
	auto shape = make_unique<sf::CircleShape>();
	auto circle = make_unique<CCircleDecorator>(std::move(shape), x, y, radius, outlineColor, fillColor);
	m_shapeListSf.emplace_back(circle);
	return true;
}

bool CController::Draw()
{
	sf::RenderWindow window(sf::VideoMode(800, 600), "Result");
	CCanvas canvas(window);
	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();
		}

		window.clear(sf::Color::White);
		for (int i = 0; i < m_shapeListSf.size(); i++)
		{
			auto& shape = m_shapeListSf[i];
			shape->Draw(window);
		}
		
		window.display();
	}
	return true;
}
