#pragma once
#include <iostream>
#include "IObservable.h"
#include <vector>
#include <algorithm>
#include <map>

template <class T>
class CObservable : public IObservable<T>
{
public:
	typedef IObserver<T> ObserverType;

	void RegisterObserver(ObserverType& observer, int priority) override
	{
		m_observers.push_back({ &observer, priority });
	}

	bool Comp(std::pair<ObserverType*, int> a, std::pair<ObserverType*, int> b)
	{
		return *a.second < *b.second;
	}

	void NotifyObservers() override
	{
		T data = GetChangedData();
		sort(m_observers.begin(), m_observers.end(), Comp);
		for (auto& observer : m_observers)
		{
			observer.first->Update(data);
		}
	}

	void RemoveObserver(ObserverType & observer) override
	{
		int index = 0;
		for (int i = 0; i < m_observers.size(); i++)
		{
			if (m_observers[i].first == &observer)
			{
				index = i;
				break;
			}
		}	
		m_observers.erase(m_observers.begin() + index);
	}

protected:
	virtual T GetChangedData() const = 0;

private:
	std::vector<std::pair<ObserverType*, int>> m_observers;
	
};

