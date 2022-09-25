#include "CModelDuck.h"

CModelDuck::CModelDuck()
	: CDuck(std::make_unique<CFlyNoWay>(), std::make_unique<CQuackBehavior>(), std::make_unique<CNoDance>())
{
}

void CModelDuck::Display() const
{
	std::cout << "I'm model duck" << std::endl;
}