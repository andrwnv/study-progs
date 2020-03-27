#pragma once

#include <QMainWindow>
#include <QPainter>

#include <complex>

class Canvas : public QMainWindow
{
public:
    Canvas(QWidget* parent = nullptr);
    int getColorValue(double x, double y);

protected:
    void paintEvent(QPaintEvent* ) override;

private:
    int _brightness  = 0;
    int _sensitivity = 300;

    std::pair<double, double> _startingPositions { -1., -1.2 };
    std::pair<double, double> _endPositions      { 1., 1.2 };

    std::complex<double>      _defaultPadding    { .36, .36 };
};


