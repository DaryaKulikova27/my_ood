#pragma once
#include "CShapeDecorator.h"

class CTriangleDecorator : public CShapeDecorator
{
public:
	CTriangleDecorator(std::unique_ptr<sf::Shape>&& shape, sf::Vector2f const& vertex1, sf::Vector2f const& vertex2, sf::Vector2f const& vertex3, sf::Color fillColor, sf::Color outlineColor) :
		CShapeDecorator(std::move(shape), fillColor, outlineColor),
		m_vertex1(vertex1),
		m_vertex2(vertex2),
		m_vertex3(vertex3) {};

protected:
	int GetArea() const override;
	int GetPerimeter() const override;
	std::string ToString() const override;

private:
	sf::Vector2f m_vertex1, m_vertex2, m_vertex3;
	int FindVectorLength(int x1, int y1, int x2, int y2) const;
};

