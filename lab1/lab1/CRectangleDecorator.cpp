#include "CRectangleDecorator.h"

int CRectangleDecorator::GetArea() const
{
	sf::Vector2f rightTop = sf::Vector2f(m_rightBottom.x, m_leftTop.y);
	double area = FindVectorLength(m_leftTop, rightTop) * FindVectorLength(rightTop, m_rightBottom);
	return area;
}

int CRectangleDecorator::FindVectorLength(sf::Vector2f p1, sf::Vector2f p2) const
{
	double vectorLength = sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	return vectorLength;
}

int CRectangleDecorator::GetPerimeter() const
{
	sf::Vector2f rightTop = sf::Vector2f(m_rightBottom.x, m_leftTop.y);
	double perimeter = 2 * (FindVectorLength(m_leftTop, rightTop) + FindVectorLength(rightTop, m_rightBottom));
	return perimeter;
}

std::string CRectangleDecorator::ToString() const
{
	std::string str = "RECTANGLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
}



