#pragma once
#include <iostream>
#include "CPoint.h"
#include <vector>

class ICanvas
{
public:
	virtual bool DrawLine(CPoint from, CPoint to, uint32_t lineColor) = 0;
	virtual bool FillPolygon(std::vector<CPoint> points, uint32_t fillColor) = 0;
	virtual bool DrawCircle(CPoint center, double radius, uint32_t fillColor, uint32_t outlineColor) = 0;
	virtual ~ICanvas() {};
};