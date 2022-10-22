#include "CShapeDecorator.h"

int CShapeDecorator::GetArea() const
{
	return GetShapeArea();
}

int CShapeDecorator::GetPerimeter() const
{
	return GetShapePerimeter();
}

std::string CShapeDecorator::ToString() const
{
	std::string str = GetShapeName() + ": P = ";
	str += std::to_string(GetShapePerimeter());
	str += "; S = ";
	str += std::to_string(GetShapeArea());
	return str;
}

uint32_t CShapeDecorator::GetOutlineColor() const
{
	return GetShapeOutlineColor();
}

uint32_t CShapeDecorator::GetFillColor() const
{
	return GetShapeFillColor();
}

sf::Color CShapeDecorator::GetColor(uint32_t color)
{
	uint8_t blue = color % 256;
	uint8_t green = (color / 256) % 256;
	uint8_t red = ((color / 256) / 256) % 256;

	return sf::Color(red, green, blue);
}