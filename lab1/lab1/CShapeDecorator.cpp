#include "CShapeDecorator.h"

bool CShapeDecorator::Draw(sf::RenderWindow& window) const 
{
	window.draw(*m_shape);
	return true;
}