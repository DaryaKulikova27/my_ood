#pragma once
#include "CShapeDecorator.h"

class CCircleDecorator : public CShapeDecorator
{
public:
	CCircleDecorator(std::unique_ptr<sf::Shape>&& shape, sf::Vector2f const& center, float const& radius, sf::Color fillColor, sf::Color outlineColor) :
		CShapeDecorator(std::move(shape), fillColor, outlineColor),
		m_center(center),
		m_radius(radius)
	{};
	int GetArea() const override;
	int GetPerimeter() const override;
	std::string ToString() const override;
	sf::Vector2f GetCenter() const;

	void Move(sf::Vector2f const& offset) override;
	sf::Rect<float> GetShapeBounds() const override;
	

private:
	sf::Vector2f m_center;
	int m_radius;
};

