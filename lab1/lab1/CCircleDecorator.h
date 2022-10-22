#pragma once
#include "CShapeDecorator.h"

class CCircleDecorator : public CShapeDecorator
{
public:
	CCircleDecorator(std::unique_ptr<sf::CircleShape> && circle, int x, int y, int radius, uint32_t outlineColor, uint32_t fillColor)
		: m_circle(std::move(circle))
		, m_center(x, y)
		, m_radius(radius)
		, m_outlineColor(outlineColor)
		, m_fillColor(fillColor)
	{};
	int GetShapeArea() const override;
	int GetShapePerimeter() const override;
	std::string GetShapeName() const override;
	uint32_t GetShapeOutlineColor() const override;
	uint32_t GetShapeFillColor() const override;
	bool Draw(sf::RenderWindow& window) override;

private:
	int m_radius;
	CPoint m_center;
	uint32_t m_outlineColor, m_fillColor;
	CPoint GetCenter();
	int GetRadius();
	std::unique_ptr<sf::CircleShape> m_circle;
};

