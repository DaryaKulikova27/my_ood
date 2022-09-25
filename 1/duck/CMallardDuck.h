#ifndef MALLARDDUCK_H
#define MALLARDDUCK_H

#include "CDuck.h"
#include "CFlyWithWings.h"
#include "CQuackBehavior.h"
#include "CWaltz.h"

class CMallardDuck : public CDuck
{
public:
	CMallardDuck();
	void Display() const override;
};

#endif
