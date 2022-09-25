#ifndef MUTEQUAKBEHAVIOR_H
#define MUTEQUAKBEHAVIOR_H

#include "IQuackBehavior.h"

class CMuteQuackBehavior : public IQuackBehavior
{
public:
	void Quack() override;
};

#endif
