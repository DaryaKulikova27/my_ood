#include "CMallardDuck.h"

CMallardDuck::CMallardDuck()
	: CDuck(std::make_unique<void(Fly())>(), std::make_unique<CQuackBehavior>(), std::make_unique<CWaltz>())
{
}

void Fly() {
	
}

void CMallardDuck::Display() const
{
	std::cout << "I'm mallard duck" << std::endl;
}