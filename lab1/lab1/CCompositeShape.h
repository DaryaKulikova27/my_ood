#pragma once
#include "CShapeDecorator.h"
#include <set>

class CCompositeShape : public CShape
{
public:
	CCompositeShape(std::set<std::shared_ptr<CShape>> shapes_list) :
		m_shapes_list(std::move(shapes_list)) {};

	bool Draw(sf::RenderWindow& window) const override;
	int GetArea() const override;
	int GetPerimeter() const override;
	std::string ToString() const override;
	void Move(sf::Vector2f const& offset) override;
	sf::Rect<float> GetShapeBounds() const override;
	bool IsComposite() const override;
	std::set<std::shared_ptr<CShape>> GetShapeList() const;


private:
	std::set<std::shared_ptr<CShape>> m_shapes_list;
};

