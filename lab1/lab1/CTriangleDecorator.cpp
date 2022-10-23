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

