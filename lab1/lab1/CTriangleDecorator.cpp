#include "CTriangleDecorator.h"

int CTriangleDecorator::FindVectorLength(int x1, int y1, int x2, int y2) const
{
	double vectorLength = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	return vectorLength;
}

int CTriangleDecorator::GetArea() const
{
	double area = abs((m_vertex2.x - m_vertex1.x) * (m_vertex3.y - m_vertex1.y) - (m_vertex3.x - m_vertex1.x) * (m_vertex2.y - m_vertex1.y)) / 2;
	return area;
}

int CTriangleDecorator::GetPerimeter() const
{
	double segment1 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex2.x, m_vertex2.y);
	double segment2 = FindVectorLength(m_vertex2.x, m_vertex2.y, m_vertex3.x, m_vertex3.y);
	double segment3 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex3.x, m_vertex3.y);
	int perimeter = segment1 + segment2 + segment3;
	return perimeter;
};

std::string CTriangleDecorator::ToString() const
{
	std::string str = "TRIANGLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
}

void CTriangleDecorator::Move(sf::Vector2f const& offset)
{
	this->m_shape->move(offset);
	m_vertex1 += offset;
	m_vertex2 += offset;
	m_vertex3 += offset;
}

sf::Rect<float> CTriangleDecorator::GetShapeBounds() const
{
	sf::Vector2f minVertex = { m_vertex1 };
	sf::Vector2f maxVertex = { m_vertex2 };
	minVertex.x = std::min(m_vertex1.x, minVertex.x);
	minVertex.y = std::min(m_vertex1.y, minVertex.y);
	maxVertex.x = std::max(m_vertex1.x, maxVertex.x);
	maxVertex.y = std::max(m_vertex1.y, maxVertex.y);

	minVertex.x = std::min(m_vertex2.x, minVertex.x);
	minVertex.y = std::min(m_vertex2.y, minVertex.y);
	maxVertex.x = std::max(m_vertex2.x, maxVertex.x);
	maxVertex.y = std::max(m_vertex2.y, maxVertex.y);

	minVertex.x = std::min(m_vertex3.x, minVertex.x);
	minVertex.y = std::min(m_vertex3.y, minVertex.y);
	maxVertex.x = std::max(m_vertex3.x, maxVertex.x);
	maxVertex.y = std::max(m_vertex3.y, maxVertex.y);
	return { minVertex, maxVertex - minVertex };
}
