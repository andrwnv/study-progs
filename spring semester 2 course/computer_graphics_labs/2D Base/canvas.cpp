#include "canvas.h"

#include <cmath>

#include <QPolygon>
#include <QPainter>
#include <QVector>
#include <QPixmap>
#include <QLayout>
#include <QLabel>
#include <QPoint>
#include <QDebug>

Canvas::Canvas(QWidget *parent) : QWidget(parent)
{
    setLayout(new QHBoxLayout());
    _label = new QLabel();

    _pixmap = QPixmap(1000, 600);
    _pixmap.fill(0xF0F0F0);

    painter = new QPainter(&_pixmap);

    layout()->addWidget(_label);

    _label->setPixmap(_pixmap);
}

void Canvas::drawAxis()
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::black, 2, Qt::SolidLine);

    QBrush brush;
    brush.setColor(Qt::black);
    brush.setStyle(Qt::SolidPattern);

    painter->setPen(pen);

    // Рисуем Оx.
    painter->drawLine( 0, _pixmap.height() / 2,
                      _pixmap.width(), _pixmap.height() / 2 );

    // Рисуем Оy.
    painter->drawLine( _pixmap.width() / 2, 0,
                      _pixmap.width() / 2, _pixmap.height() );

    // Рисуем единичные интервалы, на Ox и Oy соответственно.
    painter->drawLine( _pixmap.width() / 2 + _padding, _pixmap.height() / 2 - 10,
                      _pixmap.width() / 2 + _padding, _pixmap.height() / 2 + 10 );

    painter->drawLine( _pixmap.width() / 2 - 10, _pixmap.height() / 2 - _padding,
                      _pixmap.width() / 2 + 10, _pixmap.height() / 2 - _padding );

    // Треугольник - указатель на Оx.
    QVector<QPoint> triagnleDots { { _pixmap.width() - 5, _pixmap.height() / 2 - 5},
                                   { _pixmap.width(),     _pixmap.height() / 2    },
                                   { _pixmap.width() - 5, _pixmap.height() / 2 + 5} };

    QPolygon triangle(triagnleDots);

    // Рисуем указатель на Оx.
    painter->setBrush(brush);
    painter->drawPolygon(triangle);

    // Треугольник - указатель на Оy.
    triagnleDots = { { _pixmap.width() / 2, 0     },
                     { _pixmap.width() / 2 - 5, 5 },
                     { _pixmap.width() / 2 + 5, 5 } };

    // Очищаем прдыдушие точки треугольника и выстовляем новые.
    triangle.clear();
    triangle.append(triagnleDots);

    // Рисуем указатель на Оx.
    painter->setBrush(brush);
    painter->drawPolygon(triangle);

    // Удаляем ксить из painer.
    painter->setBrush(Qt::BrushStyle::NoBrush);

    // Подписываем оси и единичные интервалы.
    painter->setFont( QFont("Arial", 12) );
    painter->drawText( _pixmap.width() / 2 - 15, _pixmap.height() / 2 - 5 , "0" );
    painter->drawText( _pixmap.width() / 2 + 35, _pixmap.height() / 2 - 5 , "1" );
    painter->drawText( _pixmap.width() / 2 - 15, _pixmap.height() / 2 - 55, "1" );
    painter->drawText( _pixmap.width() - 15    , _pixmap.height() / 2 - 5 , "Y" );
    painter->drawText( _pixmap.width() / 2 + 5 , 15                       , "X" );

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::drawFigure()
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::red, 2, Qt::SolidLine);
    painter->setPen(pen);

    // Записываем точки многоугольника.
    _figureDots.clear();

    // Так как некоторые значения находятся посередине,
    // а QPoint может создаваться только из int значений
    // пришлось добавить каст значений с плавающей точкой к int (static_cast).
    _figureDots = {
        { _pixmap.width() / 2 - _padding * 2, static_cast<int>(_pixmap.height() / 2 + _padding * 2.5) },
        { _pixmap.width() / 2 + _padding * 3,                  _pixmap.height() / 2 + _padding * 2    },
        { _pixmap.width() / 2 - _padding * 2, static_cast<int>(_pixmap.height() / 2 - _padding * 4.5) },
        { _pixmap.width() / 2 + _padding * 2, static_cast<int>(_pixmap.height() / 2 - _padding * 4.5) }
    };

    _transformedFigureDots = _figureDots;

    // Рисуем многоугольник.
    QPolygon figure(_figureDots);
    painter->drawPolygon(figure);

    reflectFigure();

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::scaleFigure(double scaleIndex)
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::blue, 2, Qt::SolidLine);
    painter->setPen(pen);

    QVector< QPair<double, double> > rotationMatrix = {
        { scaleIndex, 0 },
        { 0, scaleIndex }
    };

    for (auto & point : _transformedFigureDots)
    {
        auto tmpPoint = point;

        point.setX( tmpPoint.x() * rotationMatrix.at(0).first + tmpPoint.y() * rotationMatrix.at(0).second );
        point.setY( tmpPoint.x() * rotationMatrix.at(1).first + tmpPoint.y() * rotationMatrix.at(1).second );
    }

    QPolygon figure(_transformedFigureDots);
    painter->drawPolygon(figure);

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::rotateFigure(double angle)
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::blue, 2, Qt::SolidLine);
    painter->setPen(pen);

    QVector< QPair<double, double> > rotationMatrix = {
        {  std::cos(angle * 3.14), std::sin(angle * 3.14) },
        { -std::sin(angle * 3.14), std::cos(angle * 3.14) }
    };

    for (auto & point : _transformedFigureDots)
    {
        auto tmpPoint = point;

        point.setX( tmpPoint.x() * rotationMatrix.at(0).first + tmpPoint.y() * rotationMatrix.at(0).second );
        point.setY( tmpPoint.x() * rotationMatrix.at(1).first + tmpPoint.y() * rotationMatrix.at(1).second );
    }

    QPolygon figure(_transformedFigureDots);
    painter->drawPolygon(figure);

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::reflectFigure()
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::blue, 2, Qt::SolidLine);
    painter->setPen(pen);

    QVector< QPair<double, double> > reflectionMatrix = {
        { -1, 0 },
        {  0, 1 }
    };

    for (auto & point : _transformedFigureDots)
    {
        auto tmpPoint = point;

        if (reflectionMatrix.at(0).first < 0)
            point.setX( width() + reflectionMatrix.at(0).first * tmpPoint.x() );
        else if (reflectionMatrix.at(0).second < 0)
            point.setY( height() + reflectionMatrix.at(1).second * tmpPoint.y());
    }

    QPolygon figure(_transformedFigureDots);
    painter->drawPolygon(figure);

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::transferFigure(int trasferX, int transferY)
{
    painter->setRenderHint(QPainter::Antialiasing, true); // Добавляем сглаживание для линии.

    QPen pen(Qt::blue, 2, Qt::SolidLine);
    painter->setPen(pen);

    QVector< QPair<int, int> > transferMatrix = {
        { trasferX * _padding, 0  },
        { 0, transferY * _padding }
    };

    QVector<QPoint> figureDotesCopy = _figureDots;

    for (auto & point : figureDotesCopy)
    {
        auto tmpPoint = point;

        point.setX( tmpPoint.x() + transferMatrix.at(0).first );
        point.setY( tmpPoint.y() + transferMatrix.at(1).second );
    }

    QPolygon figure(figureDotesCopy);
    painter->drawPolygon(figure);

    // Обновляем pixmap.
    _label->setPixmap(_pixmap);
}

void Canvas::clearPixmap()
{
    _pixmap.fill(Qt::white);
    _label->setPixmap(_pixmap);
}

void Canvas::clearFigureChanges() { _transformedFigureDots = _figureDots; }


