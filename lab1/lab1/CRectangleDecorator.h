#pragma once
#include "CShapeDecorator.h"

class CRectangleDecorator : public CShapeDecorator
{
public:
	CRectangleDecorator(std::unique_ptr<sf::Shape>&& shape, sf::Vector2f const& leftTop, sf::Vector2f const& rightBottom, sf::Color fillColor, sf::Color outlineColor) :
		CShapeDecorator(std::move(shape), fillColor, outlineColor),
		m_leftTop(leftTop),
		m_rightBottom(rightBottom) {};
	int GetArea() const override;
	int GetPerimeter() const override;
	std::string ToString() const override;

private:
	sf::Vector2f m_leftTop, m_rightBottom;
	uint32_t m_outlineColor, m_fillColor;
	int FindVectorLength(sf::Vector2f p1, sf::Vector2f p2) const;
};

