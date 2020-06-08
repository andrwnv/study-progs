#include "application.h"

#include <QHBoxLayout>
#include <QVBoxLayout>
#include <QPushButton>

#include "canvas.h"

Application::Application(QWidget *parent)
    : QWidget(parent)
{
    auto layout = new QVBoxLayout();

    setLayout(layout);
    setMinimumSize(1280, 720);

    setWindowTitle("Computer Grapics. Practice 3");

    auto drawAxixButton = new QPushButton("Draw axis");
    auto drawFigureButton = new QPushButton("Draw figure");

    drawAxixButton->setMaximumSize(200, 40);
    drawFigureButton->setMaximumSize(200, 40);

    auto buttonGroup = new QHBoxLayout();
    buttonGroup->addWidget(drawAxixButton);
    buttonGroup->addWidget(drawFigureButton);

    auto canvas = new Canvas(this);
    canvas->show();

    layout->addLayout(buttonGroup, Qt::AlignHCenter);
    layout->addStretch(5);
    layout->addWidget(canvas);

    layout->setAlignment(canvas, Qt::AlignHCenter);

    // Создаем реакию на нажатие кнопки.
    connect(drawAxixButton  , &QPushButton::clicked, canvas, &Canvas::drawAxis);
    connect(drawFigureButton, &QPushButton::clicked, canvas, &Canvas::drawFigure);
}

Application::~Application() = default;

