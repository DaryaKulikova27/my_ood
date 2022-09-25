#ifndef DECOYDUCK_H
#define DECOYDUCK_H

#include "CDuck.h"
#include "CFlyNoWay.h"
#include "CMuteBehavior.h"
#include "CNoDance.h"
#include <memory>

class CDecoyDuck : public CDuck
{
public:
	CDecoyDuck();
	void Display() const override;
};

#endif
