#pragma once
#include "CShapeDecorator.h"

class CRectangleDecorator : public CShapeDecorator
{
public:
	CRectangleDecorator(std::unique_ptr<sf::RectangleShape> && shape, int x1, int y1, int x2, int y2, uint32_t outlineColor, uint32_t fillColor)
		: m_rectangle(std::move(shape)),
		m_leftTop(x1, y1),
		m_rightBottom(x2, y2),
		m_outlineColor(outlineColor),
		m_fillColor(fillColor)
	{};
	int GetShapeArea() const override;
	int GetShapePerimeter() const override;
	std::string GetShapeName() const override;
	uint32_t GetShapeOutlineColor() const override;
	uint32_t GetShapeFillColor() const override;
	bool Draw(sf::RenderWindow& window) override;

private:
	std::unique_ptr<sf::RectangleShape> m_rectangle;
	CPoint m_leftTop, m_rightBottom;
	uint32_t m_outlineColor, m_fillColor;
	int FindVectorLength(CPoint p1, CPoint p2) const;
	CPoint GetLeftTop();
	CPoint GetRightBottom();
};

