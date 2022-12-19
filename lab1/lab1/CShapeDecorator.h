#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include <string>
#include <memory>
#include "CShape.h"


class CShapeDecorator: public CShape
{
public:
	bool Draw(sf::RenderWindow& window) const override;
	bool IsComposite() const override;
	
protected:
	CShapeDecorator(std::unique_ptr<sf::Shape>&& shape, sf::Color fillColor, sf::Color outlineColor)
		: m_shape(std::move(shape)),
		m_fillColor(fillColor),
		m_outlineColor(outlineColor) {}

	std::unique_ptr<sf::Shape> m_shape;

private:
	sf::Color m_fillColor;
	sf::Color m_outlineColor;
};
