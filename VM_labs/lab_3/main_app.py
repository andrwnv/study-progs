#!/bin/python3

import math
import sys

from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout, QHBoxLayout, QTextEdit

from NewtonPolynomial import NewtonPolynomial


class Widget(QWidget):
    test = NewtonPolynomial(0, 6, 20)

    def __init__(self):
        super().__init__()

        grid = QVBoxLayout()
        self.setLayout(grid)

        coordinates_grid_x = QVBoxLayout()
        lbl_X: QLabel = QLabel('X coordinates: ')
        x_coordinates_table = QTextEdit()

        for item in self.test.getX():
            x_coordinates_table.append(str(round(item, 2)))  # import all X coordinates on respective QTextEdit

        coordinates_grid_y = QVBoxLayout()
        lbl_Y = QLabel('Y coordinates: ')
        y_coordinates_table = QTextEdit()

        for item in self.test.getY():
            y_coordinates_table.append(str(round(item, 2)))  # import all Y coordinates on respective QTextEdit

        coordinates_grid_x.addWidget(lbl_X)
        coordinates_grid_x.addWidget(x_coordinates_table)
        coordinates_grid_y.addWidget(lbl_Y)
        coordinates_grid_y.addWidget(y_coordinates_table)

        coordinates_grid = QHBoxLayout()
        coordinates_grid.addLayout(coordinates_grid_x)
        coordinates_grid.addLayout(coordinates_grid_y)

        grid.addLayout(coordinates_grid)

        self.init_ui()

    def init_ui(self):
        self.setGeometry(700, 400, 300, 400)
        self.setWindowTitle('test')
        self.show()


if __name__ == "__main__":
    application = QApplication(sys.argv)

    test = NewtonPolynomial(0, 6, 20)
    print(test.calculate(2.23))
    print(math.sin(2.23))

    wid = Widget()
    sys.exit(application.exec_())
