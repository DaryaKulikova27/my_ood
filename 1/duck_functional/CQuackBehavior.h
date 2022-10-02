#ifndef QUACKBEHAVIOR_H
#define QUACKBEHAVIOR_H

#include "IQuackBehavior.h"
#include <iostream>

class CQuackBehavior : public IQuackBehavior
{
public:
	void Quack() override;
};

#endif
