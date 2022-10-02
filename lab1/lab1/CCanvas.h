#pragma once
#include "ICanvas.h"
#include <SFML/Graphics.hpp>

class CCanvas : public ICanvas
{
public:
	CCanvas(sf::RenderWindow& window);
	bool DrawLine(const CPoint from, const CPoint to, uint32_t lineColor) override;
	bool FillPolygon(std::vector<CPoint> points, uint32_t fillColor) override;
	bool DrawCircle(CPoint center, double radius, uint32_t fillColor, uint32_t outlineColor) override;

private:
	sf::RenderWindow& m_window;
};

sf::Color GetColor(uint32_t color);

