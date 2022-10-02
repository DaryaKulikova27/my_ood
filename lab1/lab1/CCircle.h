#pragma once
#include "IShape.h"

class CCircle: public IShape
{
public:
	CCircle(int x, int y, int radius, uint32_t outlineColor, uint32_t fillColor)
		: m_center(x, y)
		, m_radius(radius)
		, m_outlineColor(outlineColor)
		, m_fillColor(fillColor)
	{};
	int GetArea() override;
	int GetPerimeter() override;
	bool Draw(ICanvas& canvas) override;
	std::string ToString() override;
	uint32_t GetOutlineColor() override;
	uint32_t GetFillColor() override;
	CPoint GetCenter();
	int GetRadius();

private:
	int m_radius;
	CPoint m_center;
	uint32_t m_outlineColor, m_fillColor;
};

