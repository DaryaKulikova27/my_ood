#include "CRectangle.h"

int CRectangle::FindVectorLength(CPoint p1, CPoint p2)
{
	double vectorLength = sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	return vectorLength;
}

int CRectangle::GetArea()
{
	CPoint rightTop = CPoint(m_rightBottom.x, m_leftTop.y);
	double area = FindVectorLength(m_leftTop, rightTop) * FindVectorLength(rightTop, m_rightBottom);
	return area;
}

int CRectangle::GetPerimeter()
{
	CPoint rightTop = CPoint(m_rightBottom.x, m_leftTop.y);
	double perimeter = 2 * (FindVectorLength(m_leftTop, rightTop) + FindVectorLength(rightTop, m_rightBottom));
	return perimeter;
}

std::string CRectangle::ToString()
{
	std::string str = "RECTANGLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
}

uint32_t CRectangle::GetFillColor()
{
	return m_fillColor;
};

uint32_t CRectangle::GetOutlineColor()
{
	return m_outlineColor;
};

CPoint CRectangle::GetLeftTop()
{
	return m_leftTop;
};

CPoint CRectangle::GetRightBottom()
{
	return m_rightBottom;
};

bool CRectangle::Draw(ICanvas& canvas)
{
	CPoint rightTop(m_rightBottom.x, m_leftTop.y);
	CPoint leftBottom(m_leftTop.x, m_rightBottom.y);
	std::vector<CPoint> vertexes = { m_leftTop, rightTop, m_rightBottom, leftBottom };
	bool isDraw = canvas.FillPolygon(vertexes, m_fillColor);
	return true;
}