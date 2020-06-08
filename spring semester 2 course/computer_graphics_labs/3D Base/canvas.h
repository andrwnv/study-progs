#pragma once

#include <QPaintEvent>
#include <QPainter>
#include <QWidget>
#include <QLabel>

class Canvas : public QWidget
{
    Q_OBJECT

public:
    enum class Transform
    {
        Rotate,
        Reflect,
        Scale,
        Transfer
    };

public:
    explicit Canvas(QWidget *parent = nullptr);
    void drawAxis();
    void drawFigure();

    void clearPixmap();

private:
    QPainter* painter;
    QPixmap _pixmap;
    QLabel* _label;

    int             _padding = 50;
    QVector<QPoint> _figureDots;
};


