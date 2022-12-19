#include "CCompositeShape.h"

bool CCompositeShape::Draw(sf::RenderWindow& window) const {
	for (auto shape : m_shapes_list)
		shape->Draw(window);
	return true;
}

int CCompositeShape::GetArea() const {
	auto bounds = GetShapeBounds();
	return bounds.width * bounds.height;
}

int CCompositeShape::GetPerimeter() const {
	auto bounds = GetShapeBounds();
	return 2* (bounds.width + bounds.height);
}

std::string CCompositeShape::ToString() const {
	std::string str = "COMPOSITE: \n";
	for (auto shape : m_shapes_list)
		str += "    " + shape->ToString() + "\n";
	return str;
}

void CCompositeShape::Move(sf::Vector2f const& offset) {
	for (auto shape : m_shapes_list)
		shape->Move(offset);
};

sf::FloatRect CombineFRects(sf::FloatRect first, sf::FloatRect second)
{
	sf::Vector2f min = { std::min(first.left, second.left), std::min(first.top, second.top) };
	sf::Vector2f max = { std::max(first.left + first.width, second.left + second.width), std::max(first.top + first.height, second.top + second.height) };

	return {
		min, max - min
	};
}

sf::Rect<float> CCompositeShape::GetShapeBounds() const {
	if (m_shapes_list.size() > 0) {
		auto bounds = (*m_shapes_list.begin())->GetShapeBounds();
		for (auto& shape : m_shapes_list)
			bounds = CombineFRects(bounds, shape->GetShapeBounds());
		return bounds;
	}
	return { 0,0,0,0 };
}

bool CCompositeShape::IsComposite() const
{
	return true;
}

std::set<std::shared_ptr<CShape>> CCompositeShape::GetShapeList() const
{
	return m_shapes_list;
}