#include "CCircleDecorator.h"
#include <cmath>

int CCircleDecorator::GetArea() const
{
	const double PI = acos(-1.0);
	double area = m_radius * m_radius * PI;
	return area;
};

int CCircleDecorator::GetPerimeter() const
{
	const double PI = acos(-1.0);
	double perimeter = 2 * m_radius * PI;
	return perimeter;
};

sf::Vector2f CCircleDecorator::GetCenter() const
{
	return m_center;
};

std::string CCircleDecorator::ToString() const
{
	std::string str = "CIRCLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
}