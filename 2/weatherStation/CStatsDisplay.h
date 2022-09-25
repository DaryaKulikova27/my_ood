#pragma once
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include "SweatherInfo.h"
#include "CObservable.h"

class CStatsDisplay : public IObserver<SWeatherInfo>
{
private:
	void Update(SWeatherInfo const& data) override
	{
		++m_countAcc;
		GetNewStatistics(m_maxTemperature, m_minTemperature, data.temperature);
		m_accTemperature += data.temperature;
		GetNewStatistics(m_maxHumidity, m_minHumidity, data.humidity);
		m_accHumidity += data.humidity;
		GetNewStatistics(m_maxPressure, m_minPressure, data.pressure);
		m_accPressure += data.pressure;

		std::cout << "Max Temp " << m_maxTemperature << std::endl;
		std::cout << "Min Temp " << m_minTemperature << std::endl;
		std::cout << "Average Temp " << (m_accTemperature / m_countAcc) << std::endl;
		std::cout << std::endl;
		std::cout << "Max Hum " << m_maxHumidity << std::endl;
		std::cout << "Min Hum " << m_minHumidity << std::endl;
		std::cout << "Average Hum " << (m_accHumidity / m_countAcc) << std::endl;
		std::cout << std::endl;
		std::cout << "Max Pressure " << m_maxPressure << std::endl;
		std::cout << "Min Pressure " << m_minPressure << std::endl;
		std::cout << "Average Pressure " << (m_accPressure / m_countAcc) << std::endl;
		std::cout << "----------------" << std::endl;
	}

	void GetNewStatistics(double& maxValue, double& minValue, double curValue)
	{
		if (minValue > curValue)
		{
			minValue = curValue;
		}
		if (maxValue < curValue)
		{
			maxValue = curValue;
		}
	}

	double m_minTemperature = std::numeric_limits<double>::infinity();
	double m_maxTemperature = -std::numeric_limits<double>::infinity();
	double m_accTemperature = 0;
	double m_minHumidity = std::numeric_limits<double>::infinity();
	double m_maxHumidity = -std::numeric_limits<double>::infinity();
	double m_accHumidity = 0;
	double m_minPressure = std::numeric_limits<double>::infinity();
	double m_maxPressure = -std::numeric_limits<double>::infinity();
	double m_accPressure = 0;
	unsigned m_countAcc = 0;
};