#include "CTriangleDecorator.h"

int CTriangleDecorator::FindVectorLength(int x1, int y1, int x2, int y2) const
{
	double vectorLength = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	return vectorLength;
}

int CTriangleDecorator::GetShapeArea() const
{
	double area = abs((m_vertex2.x - m_vertex1.x) * (m_vertex3.y - m_vertex1.y) - (m_vertex3.x - m_vertex1.x) * (m_vertex2.y - m_vertex1.y)) / 2;
	return area;
}

int CTriangleDecorator::GetShapePerimeter() const
{
	double segment1 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex2.x, m_vertex2.y);
	double segment2 = FindVectorLength(m_vertex2.x, m_vertex2.y, m_vertex3.x, m_vertex3.y);
	double segment3 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex3.x, m_vertex3.y);
	int perimeter = segment1 + segment2 + segment3;
	return perimeter;
};

std::string CTriangleDecorator::GetShapeName() const
{
	return "TRIANGLE";
}

uint32_t CTriangleDecorator::GetShapeFillColor() const
{
	return m_fillColor;
};

uint32_t CTriangleDecorator::GetShapeOutlineColor() const
{
	return m_outlineColor;
};

CPoint CTriangleDecorator::GetVertex1() const
{
	return m_vertex1;
};

CPoint CTriangleDecorator::GetVertex2() const
{
	return m_vertex2;
};

CPoint CTriangleDecorator::GetVertex3() const
{
	return m_vertex3;
};

bool CTriangleDecorator::Draw(sf::RenderWindow& window)
{
	window.draw(*m_triangle);
	return true;
}