#include "CCircle.h"
#include <cmath>

int CCircle::GetArea()
{
	const double PI = acos(-1.0);
	double area = m_radius * m_radius * PI;
	return area;
};

int CCircle::GetPerimeter()
{
	const double PI = acos(-1.0);
	double perimeter = 2 * m_radius * PI;
	return perimeter;
};

CPoint CCircle::GetCenter()
{
	return m_center;
};

int CCircle::GetRadius()
{
	return m_radius;
};

uint32_t CCircle::GetFillColor()
{
	return m_fillColor;
};

uint32_t CCircle::GetOutlineColor()
{
	return m_outlineColor;
};

std::string CCircle::ToString()
{
	std::string str = "CIRCLE: P = ";
	str += std::to_string(GetPerimeter());
	str += "; S = ";
	str += std::to_string(GetArea());
	return str;
};

bool CCircle::Draw(ICanvas& canvas)
{
	bool isDraw = canvas.DrawCircle(m_center, m_radius, m_fillColor, m_outlineColor);
	return true;
}
