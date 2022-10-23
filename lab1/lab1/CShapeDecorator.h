#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include <string>
#include <memory>


class CShapeDecorator : public sf::Shape
{
public:
	std::size_t getPointCount() const override { return 0; };
	sf::Vector2f getPoint(std::size_t index) const override { return { 0, 0 }; };

	virtual int GetArea() const = 0;
	virtual int GetPerimeter() const = 0;
	virtual std::string ToString() const = 0;
	bool Draw(sf::RenderWindow& window);
	
protected:
	CShapeDecorator(std::unique_ptr<sf::Shape>&& shape, sf::Color fillColor, sf::Color outlineColor)
		: m_shape(std::move(shape)),
		m_fillColor(fillColor),
		m_outlineColor(outlineColor) {}

private:
	std::unique_ptr<sf::Shape> m_shape;
	sf::Color m_fillColor;
	sf::Color m_outlineColor;
};
