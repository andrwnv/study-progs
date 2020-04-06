#pragma once

#include <QPaintEvent>
#include <QPainter>
#include <QWidget>
#include <QLabel>

class Canvas : public QWidget
{
    Q_OBJECT

public:
    explicit Canvas(QWidget *parent = nullptr);
    void drawAxis();
    void drawFigure();

    void scaleFigure(double scaleIndex);
    void rotateFigure(double angle);
    void reflectFigure();
    void transferFigure(int trasferX, int transferY);

    void clearPixmap();
    void clearFigureChanges();

private:
    QPainter* painter;
    QPixmap _pixmap;
    QLabel* _label;

    int             _padding = 50;
    QVector<QPoint> _figureDots;
    QVector<QPoint> _transformedFigureDots;
};


