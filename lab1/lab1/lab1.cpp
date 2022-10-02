#include <iostream>
#include <SFML/Graphics.hpp>
#include "CController.h"

using namespace std;

int main()
{  
	CController controller(std::cin, std::cout);
	controller.Info();
	while (!cin.eof() && !cin.fail())
	{
		cout << "> ";
		if (!controller.HandleCommand())
		{
			cout << "Unknown command!" << endl;
		}
	}
  
	return 0;
}
