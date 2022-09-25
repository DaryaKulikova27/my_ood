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

class CDuck
{
public: 
	CDuck(std::unique_ptr<IFlyBehavior>&& flyBehavior,
		std::unique_ptr<IQuackBehavior>&& quackBehavior,
		std::unique_ptr<IDanceBehavior>&& danceBehavior);
	void Quack() const;
	void Swim();
	void Fly();
	void Dance() const;
	void SetFlyBehavior(std::unique_ptr<IFlyBehavior>&& flyBehavior);
	void SetDanceBehavior(std::unique_ptr<IDanceBehavior>&& danceBehavior);
	virtual void Display() const = 0;
	virtual ~CDuck() = default;

private:
	std::unique_ptr<IFlyBehavior> m_flyBehavior;
	std::unique_ptr<IQuackBehavior> m_quackBehavior;
	std::unique_ptr<IDanceBehavior> m_danceBehavior;
};

#endif