#include "CTriangle.h"

int CTriangle::FindVectorLength(int x1, int y1, int x2, int y2)
{
	double vectorLength = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	return vectorLength;
}

int CTriangle::GetArea()
{
	double area = abs((m_vertex2.x - m_vertex1.x) * (m_vertex3.y - m_vertex1.y) - (m_vertex3.x - m_vertex1.x) * (m_vertex2.y - m_vertex1.y)) / 2;
	return area;
}

int CTriangle::GetPerimeter()
{
	double segment1 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex2.x, m_vertex2.y);
	double segment2 = FindVectorLength(m_vertex2.x, m_vertex2.y, m_vertex3.x, m_vertex3.y);
	double segment3 = FindVectorLength(m_vertex1.x, m_vertex1.y, m_vertex3.x, m_vertex3.y);
	int perimeter = segment1 + segment2 + segment3;
	return perimeter;
};

std::string CTriangle::ToString()
{
	std::string str = "TRIANGLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
}

uint32_t CTriangle::GetFillColor()
{
	return m_fillColor;
};

uint32_t CTriangle::GetOutlineColor()
{
	return m_outlineColor;
};

CPoint CTriangle::GetVertex1() const
{
	return m_vertex1;
};

CPoint CTriangle::GetVertex2() const
{
	return m_vertex2;
};

CPoint CTriangle::GetVertex3() const
{
	return m_vertex3;
};

bool CTriangle::Draw(ICanvas& canvas)
{
	std::vector<CPoint> vertexes = { m_vertex1, m_vertex2, m_vertex3 };
	bool isDraw = canvas.FillPolygon(vertexes, m_fillColor);
	return true;
}