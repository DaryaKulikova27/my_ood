#pragma once
#include <iostream>
#include "CCircleDecorator.h"
#include "CRectangleDecorator.h"
#include "CTriangleDecorator.h"
#include <vector>
#include <map>
#include <functional>
#include "CShapeDecorator.h"

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
	std::vector<std::unique_ptr<CShapeDecorator>> m_shapeList;

	using Handler = std::function<bool(std::istream& args)>;
	using ActionMap = std::map<std::string, Handler>;

	std::ostream& m_output;
	std::istream& m_input;

	const ActionMap m_actionMap;
};

sf::Color GetColor(uint32_t color);
