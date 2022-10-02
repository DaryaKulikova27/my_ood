#pragma once
#include "IShape.h"

class CTriangle: public IShape
{
public:
	CTriangle(int x1, int y1, int x2, int y2, int x3, int y3, uint32_t outlineColor, uint32_t fillColor)
		: m_vertex1(x1, y1)
		, m_vertex2(x2, y2)
		, m_vertex3(x3, y3)
		, m_outlineColor(outlineColor)
		, m_fillColor(fillColor)
	{};
	int GetArea() override;
	int GetPerimeter() override;
	std::string ToString() override;
	uint32_t GetOutlineColor() override;
	uint32_t GetFillColor() override;
	CPoint GetVertex1() const;
	CPoint GetVertex2() const;
	CPoint GetVertex3() const;
	bool Draw(ICanvas& canvas) override;

private:
	CPoint m_vertex1, m_vertex2, m_vertex3;
	uint32_t m_outlineColor, m_fillColor;
	int FindVectorLength(int x1, int y1, int x2, int y2);
};



