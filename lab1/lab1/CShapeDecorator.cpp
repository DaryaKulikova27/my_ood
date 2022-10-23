#include "CShapeDecorator.h"

bool CShapeDecorator::Draw(sf::RenderWindow& window)
{
	window.draw(*m_shape);
	return true;
}