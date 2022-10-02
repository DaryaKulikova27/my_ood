#include "CDecoyDuck.h"
#include "CMallardDuck.h"
#include "CModelDuck.h"
#include "CRedheadDuck.h"
#include "CRubberDuck.h"
#include "DuckFunctions.h"
#include <cstdlib>

int main()
{
	CMallardDuck mallardDuck;
	PlayWithDuck(mallardDuck);
	mallardDuck.Fly();
	std::cout << std::endl;

	CRedheadDuck redheadDuck;
	PlayWithDuck(redheadDuck);
	redheadDuck.Fly();
	redheadDuck.Fly();
	redheadDuck.Fly();
	redheadDuck.Fly();
	std::cout << std::endl;

	CRubberDuck rubberDuck;
	PlayWithDuck(rubberDuck);
	rubberDuck.Fly();

	CDecoyDuck decoyDuck;
	PlayWithDuck(decoyDuck);
	decoyDuck.Fly();
	std::cout << std::endl;

	CModelDuck modelDuck;
	PlayWithDuck(modelDuck);
	modelDuck.Fly();
	std::cout << std::endl;

	modelDuck.SetFlyBehavior(std::make_unique<CFlyWithWings>());
	PlayWithDuck(modelDuck);
	modelDuck.Fly();
	std::cout << std::endl;

	modelDuck.SetFlyBehavior(std::make_unique<CFlyNoWay>());
	modelDuck.Fly();
	modelDuck.Fly();
	std::cout << std::endl;

	modelDuck.SetFlyBehavior(std::make_unique<CFlyWithWings>());
	modelDuck.Fly();
	modelDuck.Fly();
	std::cout << std::endl;

	return EXIT_SUCCESS;
}