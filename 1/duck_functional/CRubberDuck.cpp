#include "CRubberDuck.h"

CRubberDuck::CRubberDuck()
	: CDuck(FlyABC(), QuackABC(), NoDanceABC())
{
}

void QuackABC()
{
	std::cout << "Squeek!!!" << std::endl;
}

void FlyABC()
{
	std::cout << "I can't fly" << std::endl;
}

void NoDanceABC()
{
}

void CRubberDuck::Display() const
{
	std::cout << "I'm rubber duck" << std::endl;
}

