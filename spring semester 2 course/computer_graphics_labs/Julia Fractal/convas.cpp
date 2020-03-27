#include "convas.h"

#include <QApplication>
#include <QToolBar>
#include <QSlider>
#include <QLabel>

#include <cmath>

Canvas::Canvas(QWidget* parent) : QMainWindow(parent)
{
    auto toolBar = new QToolBar(this);
    toolBar->setAllowedAreas(Qt::TopToolBarArea | Qt::BottomToolBarArea);
    addToolBar(toolBar);

    setWindowTitle("Julia Set");

    auto brightnessLabel = new QLabel("Brightness", this);
    brightnessLabel->setStyleSheet("QLabel { background-color : white; }");

    auto brightnessSlider = new QSlider(Qt::Horizontal, this);
    brightnessSlider->setRange(0, 50);


    auto sensitivityLabel = new QLabel("Sensitivity", this);
    sensitivityLabel->setStyleSheet("QLabel { background-color : white; }");

    auto sensitivitySlider = new QSlider(Qt::Horizontal, this);
    sensitivitySlider->setRange(20, 300);

    // Setup toolBar.
    toolBar->addWidget(brightnessLabel);
    toolBar->addWidget(brightnessSlider);
    toolBar->addSeparator();
    toolBar->addWidget(sensitivityLabel);
    toolBar->addWidget(sensitivitySlider);

    // Slider events.
    connect(brightnessSlider, &QSlider::valueChanged, this, [this](int val) -> void
    {
        _brightness = val;
        update();
    });

    connect(sensitivitySlider, &QSlider::valueChanged, this, [this](int val) -> void
    {
        _sensitivity = val;
        update();
    });

}

int Canvas::getColorValue(double x, double y)
{
    std::complex<double> point(x * (_endPositions.first  - _startingPositions.first) / width() - 1,
                               y * (_endPositions.second - _startingPositions.second) / height() - 1);

    auto brightness = _brightness;

    while( std::abs(point) < 2 and brightness <= _sensitivity)
    {
        point = point * point + _defaultPadding;
        ++brightness;
    }

    if (brightness < _sensitivity)
        return 255 * brightness / _sensitivity;

    return 0;
}

void Canvas::paintEvent(QPaintEvent *)
{
    QPainter painter(this);
    painter.setRenderHint(QPainter::SmoothPixmapTransform);

    QPen pen;
    int color = 0;

    for (auto x = 0; x < width(); ++x)
    {
        for (auto y = 0; y < height(); ++y)
        {
            color = getColorValue(x, y);

            pen.setColor(QColor(0, color, color));
            painter.setPen(pen);
            painter.drawPoint(x, y);
        }
    }
}
