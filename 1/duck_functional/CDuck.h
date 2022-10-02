#pragma once
#include "IFlyBehavior.h"
#include "IQuackBehavior.h"
#include "IDanceBehavior.h"
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#ifndef DUCK_H
#define DUCK_H
#include <functional>


class CDuck
{
public: 
	CDuck(void quackBehavior(), void flyBehavior(), void danceBehavior());
	void Quack() const;
	void Swim();
	void Fly();
	void Dance() const;

	void SetDanceBehavior(void danceBehavior());
	void SetQuackBehavior(void quackBehavior());
	void SetFlyBehavior(void flyBehavior());
	virtual void Display() const = 0;
	virtual ~CDuck() = default;

private:
	void (*m_flyBehavior);
	void (*m_quackBehavior);
	void (*m_danceBehavior);
};

#endif