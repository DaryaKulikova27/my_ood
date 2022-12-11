#pragma once
#include "CShapeDecorator.h"

class CShape
{
public:
	virtual int GetArea() const = 0;
	virtual int GetPerimeter() const = 0;
	virtual std::string ToString() const = 0;
	virtual void Move(sf::Vector2f const& offset) = 0;
	virtual sf::Rect<float> GetShapeBounds() const = 0;
	virtual bool Draw(sf::RenderWindow& window) const = 0;
};
