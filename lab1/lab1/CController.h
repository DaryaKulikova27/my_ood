#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include "CCircle.h"
#include "CRectangle.h"
#include "CTriangle.h"
#include "CCircleDecorator.h"
#include "CRectangleDecorator.h"
#include "CTriangleDecorator.h"
#include "CCanvas.h"
#include <vector>
#include <memory>
#include <map>
#include <functional>
#include <stdio.h>
#include <tchar.h>

class CController
{
public:
	CController(std::istream& input, std::ostream& output);
	bool HandleCommand() const;
	bool Info() const;
	bool PrintInfoShapes() const;
	bool Draw();

private:
	bool AddRectangle(std::istream& args);
	bool AddTRiangle(std::istream& args);
	bool AddCircle(std::istream& args);

	using Handler = std::function<bool(std::istream& args)>;
	using ActionMap = std::map<std::string, Handler>;

	std::vector<std::shared_ptr<CShapeDecorator>> m_shapeListSf;
	std::ostream& m_output;
	std::istream& m_input;

	const ActionMap m_actionMap;
};
