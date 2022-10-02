#include "CCanvas.h"

CCanvas::CCanvas(sf::RenderWindow& window)
	:m_window(window) {};

bool CCanvas::DrawLine(CPoint from, CPoint to, uint32_t lineColor)
{
	sf::Vertex line[] =
	{
		sf::Vertex(sf::Vector2f(static_cast<float>(from.x), static_cast<float>(from.y))),
		sf::Vertex(sf::Vector2f(static_cast<float>(to.x), static_cast<float>(to.y)))
	};

	line[0].color = GetColor(lineColor);
	line[1].color = GetColor(lineColor);

	m_window.draw(line, 1, sf::Lines);

	return true;
}

bool CCanvas::FillPolygon(std::vector<CPoint> points, uint32_t fillColor)
{
	sf::ConvexShape polygon;
	polygon.setPointCount(points.size());
	polygon.setFillColor(GetColor(fillColor));

	for (size_t i = 0; i < points.size(); i++)
	{
		polygon.setPoint(i, sf::Vector2f(static_cast<float>(points[i].x), static_cast<float>(points[i].y)));
	}

	m_window.draw(polygon);
	return true;
}

bool CCanvas::DrawCircle(CPoint center, double radius, uint32_t fillColor, uint32_t outlineColor)
{
	sf::CircleShape shape(radius);
	shape.setPosition(center.x, center.y);
	shape.setOutlineColor(GetColor(outlineColor));
	shape.setFillColor(GetColor(fillColor));
	shape.setOutlineThickness(1);
	m_window.draw(shape);
	return true;
}

sf::Color GetColor(uint32_t color)
{
	uint8_t blue = color % 256;
	uint8_t green = (color / 256) % 256;
	uint8_t red = ((color / 256) / 256) % 256;

	return sf::Color(red, green, blue);
}