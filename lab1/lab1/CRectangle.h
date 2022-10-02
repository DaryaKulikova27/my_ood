#pragma once
#include "IShape.h"

class CRectangle: public IShape
{
public:
	CRectangle(int x1, int y1, int x2, int y2, uint32_t outlineColor, uint32_t fillColor)
		: m_leftTop(x1, y1),
		m_rightBottom(x2, y2),
		m_outlineColor(outlineColor),
		m_fillColor(fillColor)
	{};
	int GetArea() override;
	int GetPerimeter() override;
	std::string ToString() override;
	uint32_t GetOutlineColor() override;
	uint32_t GetFillColor() override;
	CPoint GetLeftTop();
	CPoint GetRightBottom();
	bool Draw(ICanvas& canvas) override;
private:
	CPoint m_leftTop, m_rightBottom;
	uint32_t m_outlineColor, m_fillColor;
	int FindVectorLength(CPoint p1, CPoint p2);
};

