#pragma once
#include <iostream>
#include <string>
#include "CPoint.h"
#include "ICanvasDrawable.h"

class IShape : public ICanvasDrawable
{
public:
	virtual int GetArea() = 0;
	virtual int GetPerimeter() = 0;
	virtual std::string ToString() = 0;
	virtual uint32_t GetOutlineColor() = 0;
	virtual uint32_t GetFillColor() = 0;
	virtual bool Draw(ICanvas& canvas) = 0;
	virtual ~IShape() = default;
};
