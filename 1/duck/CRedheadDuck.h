#ifndef REDHEADDUCK_H
#define REDHEADDUCK_H

#include "CDuck.h"
#include "CFlyWithWings.h"
#include "CQuackBehavior.h"
#include "CMenuet.h"
#include <memory>

class CRedheadDuck : public CDuck
{
public:
	CRedheadDuck();
	void Display() const override;
};

#endif
