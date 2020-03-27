#pragma once

#include <QPaintEvent>
#include <QWidget>
#include <QLabel>

class Canvas : public QWidget
{
    Q_OBJECT
public:
    explicit Canvas(QWidget *parent = nullptr);
    void drawAxis();
    void drawFigure();

private:
    QPixmap _pixmap;
    QLabel* _label;

    int             _padding = 50;
    QVector<QPoint> _figureDots;
};


