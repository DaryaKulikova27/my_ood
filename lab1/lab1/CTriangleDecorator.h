#pragma once
#include "CShapeDecorator.h"

class CTriangleDecorator : public CShapeDecorator
{
public:
	CTriangleDecorator(std::unique_ptr<sf::ConvexShape> && shape, int x1, int y1, int x2, int y2, int x3, int y3, uint32_t outlineColor, uint32_t fillColor)
		: m_triangle(std::move(shape))
		, m_vertex1(x1, y1)
		, m_vertex2(x2, y2)
		, m_vertex3(x3, y3)
		, m_outlineColor(outlineColor)
		, m_fillColor(fillColor)
	{};

protected:
	int GetShapeArea() const override;
	int GetShapePerimeter() const override;
	std::string GetShapeName() const override;
	uint32_t GetShapeOutlineColor() const override;
	uint32_t GetShapeFillColor() const override;
	bool Draw(sf::RenderWindow& window) override;

private:
	std::unique_ptr<sf::ConvexShape> m_triangle;
	CPoint m_vertex1, m_vertex2, m_vertex3;
	uint32_t m_outlineColor, m_fillColor;
	int FindVectorLength(int x1, int y1, int x2, int y2) const;
	CPoint GetVertex1() const;
	CPoint GetVertex2() const;
	CPoint GetVertex3() const;
};

