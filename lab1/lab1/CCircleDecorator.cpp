#include "CCircleDecorator.h"
#include <cmath>

int CCircleDecorator::GetShapeArea() const
{
	const double PI = acos(-1.0);
	double area = m_radius * m_radius * PI;
	return area;
};

int CCircleDecorator::GetShapePerimeter() const
{
	const double PI = acos(-1.0);
	double perimeter = 2 * m_radius * PI;
	return perimeter;
};

CPoint CCircleDecorator::GetCenter()
{
	return m_center;
};

int CCircleDecorator::GetRadius()
{
	return m_radius;
};

uint32_t CCircleDecorator::GetShapeFillColor() const
{
	return m_fillColor;
};

uint32_t CCircleDecorator::GetShapeOutlineColor() const
{
	return m_outlineColor;
};

std::string CCircleDecorator::GetShapeName() const
{
	return "CIRCLE";
};

bool CCircleDecorator::Draw(sf::RenderWindow& window)
{
	window.draw(*m_circle);
	return true;
}