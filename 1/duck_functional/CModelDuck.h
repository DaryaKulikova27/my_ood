#ifndef MODELDUCK_H
#define MODELDUCK_H

#include "CDuck.h"
#include "CNoDance.h"
#include "CFlyNoWay.h"
#include "CQuackBehavior.h"

class CModelDuck : public CDuck
{
public:
	CModelDuck();
	void Display() const override;
};

#endif
