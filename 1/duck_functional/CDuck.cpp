#include "CDuck.h"

CDuck::CDuck(void quackBehavior(), void flyBehavior(), void danceBehavior())
{
	SetQuackBehavior(quackBehavior);
	SetFlyBehavior(flyBehavior);
	SetDanceBehavior(danceBehavior);
}

void CDuck::Quack() const
{
	m_quackBehavior;
}

void CDuck::Swim()
{
	std::cout << "I'm swimming" << std::endl;
}

void CDuck::Fly()
{
	m_flyBehavior;
}

void CDuck::Dance() const
{
	m_danceBehavior;
}

void CDuck::SetQuackBehavior(void quackBehavior())
{
	assert(quackBehavior);
	m_quackBehavior = quackBehavior;
}

void CDuck::SetFlyBehavior(void flyBehavior())
{
	assert(flyBehavior);
	m_flyBehavior = flyBehavior;
}

void CDuck::SetDanceBehavior(void danceBehavior())
{
	assert(danceBehavior);
	m_danceBehavior = danceBehavior;
}





