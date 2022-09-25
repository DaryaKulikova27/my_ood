#include "CFlyWithWings.h"

void CFlyWithWings::Fly()
{
	std::cout << "I'm flying with wings!!" << std::endl;
}

void CFlyWithWings::IncreaseNumberOfFlights()
{
	m_numberOfFlight++;
	std::cout << "Number of flights: " << m_numberOfFlight << std::endl;
}