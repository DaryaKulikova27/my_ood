#include "CShapeDecorator.h"

bool CShapeDecorator::Draw(sf::RenderWindow& window) const 
{
	window.draw(*m_shape);
	return true;
}

bool CShapeDecorator::IsComposite() const 
{
	return false;
}