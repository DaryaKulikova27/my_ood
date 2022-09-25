#ifndef RUBBERDUCK_H
#define RUBBERDUCK_H

#include "CDuck.h"
#include "CFlyNoWay.h"
#include "CSqueakBehavior.h"
#include "CNoDance.h"

class CRubberDuck : public CDuck
{
public:
	CRubberDuck();
	void Display() const override;
};

#endif
