#pragma once
#include <iostream>
#include "CCircleDecorator.h"
#include "CRectangleDecorator.h"
#include "CTriangleDecorator.h"
#include <vector>
#include <map>
#include <set>
#include <functional>
#include <optional>
#include "CShape.h"

enum TouchState {
	IDLE,
	TOUCH_DOWN_ON_OBJECT,
	TOUCH_MOVED
};

class CController
{
public:
	CController(std::istream& input, std::ostream& output);
	bool Info() const;
	bool HandleCommand() const;

private:
	bool PrintInfoShapes() const;
	bool Draw();

	bool AddRectangle(std::istream& args);
	bool AddTRiangle(std::istream& args);
	bool AddCircle(std::istream& args);
	std::optional<std::shared_ptr<CShape>> GetTouchedShape(sf::Vector2f point);
	TouchState m_touchState;
	sf::Vector2f m_previousTouchPoint;
	std::unique_ptr<sf::RectangleShape> m_shapeBorder;
	std::set<std::shared_ptr<CShape>> m_shapeList;
	std::set<std::shared_ptr<CShape>> m_selectedShapeSet;

	using Handler = std::function<bool(std::istream& args)>;
	using ActionMap = std::map<std::string, Handler>;

	std::ostream& m_output;
	std::istream& m_input;

	const ActionMap m_actionMap;
};

sf::Color GetColor(uint32_t color);
sf::FloatRect CombineRects(sf::FloatRect first, sf::FloatRect second);
