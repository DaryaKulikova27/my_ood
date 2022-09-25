#ifndef FLYWITHWINGS_H
#define FLYWITHWINGS_H

#include "IFlyBehavior.h"
#include <iostream>

class CFlyWithWings : public IFlyBehavior
{
public:
	void Fly() override;
	void IncreaseNumberOfFlights() override;

private:
	int m_numberOfFlight = 0;
};

#endif
