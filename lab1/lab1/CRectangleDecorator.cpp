#include "CRectangleDecorator.h"

int CRectangleDecorator::GetShapeArea() const
{
	CPoint rightTop = CPoint(m_rightBottom.x, m_leftTop.y);
	double area = FindVectorLength(m_leftTop, rightTop) * FindVectorLength(rightTop, m_rightBottom);
	return area;
}

int CRectangleDecorator::FindVectorLength(CPoint p1, CPoint p2) const
{
	double vectorLength = sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	return vectorLength;
}

int CRectangleDecorator::GetShapePerimeter() const
{
	CPoint rightTop = CPoint(m_rightBottom.x, m_leftTop.y);
	double perimeter = 2 * (FindVectorLength(m_leftTop, rightTop) + FindVectorLength(rightTop, m_rightBottom));
	return perimeter;
}

std::string CRectangleDecorator::GetShapeName() const
{
	return "RECTANGLE";
}

uint32_t CRectangleDecorator::GetShapeFillColor() const
{
	return m_fillColor;
};

uint32_t CRectangleDecorator::GetShapeOutlineColor() const
{
	return m_outlineColor;
};

CPoint CRectangleDecorator::GetLeftTop()
{
	return m_leftTop;
};

CPoint CRectangleDecorator::GetRightBottom()
{
	return m_rightBottom;
};

bool CRectangleDecorator::Draw(sf::RenderWindow& window)
{
	window.draw(*m_rectangle);
	return true;
}


