#pragma once
#include <iostream>
#include <string>
#include <memory>
#include "CPoint.h"
#include "ICanvasDrawable.h"
#include <SFML/Graphics.hpp>

class CShapeDecorator : public sf::Shape
{
public:
	int GetArea() const;
	int GetPerimeter() const;
	std::string ToString() const;
	uint32_t GetOutlineColor() const;
	uint32_t GetFillColor() const;
	virtual bool Draw(sf::RenderWindow& window) = 0;
	//virtual ~CShapeDecorator() = default;

protected:
	CShapeDecorator() {};
	virtual int GetShapeArea() const = 0;
	virtual int GetShapePerimeter() const = 0;
	virtual std::string GetShapeName() const = 0;
	virtual uint32_t GetShapeOutlineColor() const = 0;
	virtual uint32_t GetShapeFillColor() const = 0;
	sf::Color GetColor(uint32_t color);
};
