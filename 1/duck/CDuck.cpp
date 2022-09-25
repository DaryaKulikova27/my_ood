#include "CDuck.h"

CDuck::CDuck(std::unique_ptr<IFlyBehavior>&& flyBehavior,
	std::unique_ptr<IQuackBehavior>&& quackBehavior,
	std::unique_ptr<IDanceBehavior>&& danceBehavior)
	: m_quackBehavior(std::move(quackBehavior))
{
	assert(m_quackBehavior);
	SetFlyBehavior(std::move(flyBehavior));
	SetDanceBehavior(std::move(danceBehavior));
}

void CDuck::Quack() const
{
	m_quackBehavior->Quack();
}

void CDuck::Swim()
{
	std::cout << "I'm swimming" << std::endl;
}

void CDuck::Fly()
{
	m_flyBehavior->IncreaseNumberOfFlights();
	m_flyBehavior->Fly();
}

void CDuck::Dance() const
{
	m_danceBehavior->Dance();
}

void CDuck::SetFlyBehavior(std::unique_ptr<IFlyBehavior>&& flyBehavior)
{
	assert(flyBehavior);
	m_flyBehavior = std::move(flyBehavior);
}

void CDuck::SetDanceBehavior(std::unique_ptr<IDanceBehavior>&& danceBehavior)
{
	assert(danceBehavior);
	m_danceBehavior = std::move(danceBehavior);
}





