#pragma once

#include <QWidget>

class Application : public QWidget
{
    Q_OBJECT

public:
    Application(QWidget *parent = nullptr);
    ~Application();
};




