#ifndef SQUEAKBEHAVIOR_H
#define SQUEAKBEHAVIOR_H

#include "IQuackBehavior.h"
#include <iostream>

class CSqueakBehavior : public IQuackBehavior
{
public:
	void Quack() override;
};

#endif
