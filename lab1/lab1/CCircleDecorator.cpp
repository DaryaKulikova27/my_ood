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

void CCircleDecorator::Move(sf::Vector2f const& offset)
{
	this->m_shape->move(offset);
	m_center += offset;
}

sf::Rect<float> CCircleDecorator::GetShapeBounds() const
{
	sf::Vector2f halfSize = { (float) m_radius, (float) m_radius };
	return { m_center - halfSize, halfSize * 2.0f };
}