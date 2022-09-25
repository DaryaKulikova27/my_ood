#ifndef IDANCEBEHAVIOR_H
#define IDANCEBEHAVIOR_H
#include <iostream>

struct IDanceBehavior
{
	virtual ~IDanceBehavior() {};
	virtual void Dance() = 0;
};

#endif