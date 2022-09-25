#ifndef DUCKFUNCTIONS_H
#define DUCKFUNCTIONS_H

#include "CDuck.h"

void DrawDuck(CDuck const& duck)
{
	duck.Display();
}

void PlayWithDuck(CDuck& duck)
{
	DrawDuck(duck);
	duck.Quack();
	duck.Fly();
	duck.Dance();
}

#endif
