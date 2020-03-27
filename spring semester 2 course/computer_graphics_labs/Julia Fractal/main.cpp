#include "convas.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    Canvas widget;
    widget.resize(1000, 1000);

    widget.show();

    return app.exec();
}
