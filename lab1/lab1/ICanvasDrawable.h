#pragma once
#include <iostream>
#include "ICanvas.h"

class ICanvasDrawable
{
public:
	virtual bool Draw(ICanvas& canvas) = 0;
	virtual ~ICanvasDrawable() {};
};