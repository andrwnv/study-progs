from PyQt5.QtOpenGL import *
from OpenGL.GL import *

from PyQt5 import QtWidgets


class FigureWidget(QGLWidget):
    """ Виджет для отрисовки фигуры. """

    def __init__(self, parent):
        super(FigureWidget, self).__init__()

        self.setMinimumSize(700, 700)
        self.__nowPath: bool = True

    def drawPath(self) -> None:
        self.__nowPath = True
        self.update()

    def drawFigure(self) -> None:
        self.__nowPath = False
        self.update()

    def paintGL(self) -> None:
        """ Отрисовка фигуры. """

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity()

        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST)

        if self.__nowPath:
            glBegin(GL_LINE_LOOP)

            glColor(0.5, 0, 1, 1)
            glVertex3f(-1, 1, 0)
            glVertex3f(0.5, 1, 0)
            glVertex3f(1, -0.5, 0)
            glColor(0.5, 0.5, 1, 1)
            glVertex3f(0.7, -0.4, 0)
            glVertex3f(0.5, -1, 0)

        else:
            glBegin(GL_POLYGON)

            glColor3f(0.7, 0.5, 0.5)
            glVertex3f(-1, 1, 0)
            glColor3f(0.1, 0, 0.3)
            glVertex3f(0.5, 1, 0)
            glColor3f(0.2, 0.5, 0.7)
            glVertex3f(1, -0.5, 0)
            glColor3f(0.7, 0.5, 0.5)
            glVertex3f(0.7, -0.4, 0)
            glColor3f(0.5, 0, 0.7)
            glVertex3f(0.5, -1, 0)

        glEnd()
        glFlush()

    def resizeGL(self, w, h) -> None:
        """ Изменение размера окна. """

        glViewport(50, 50, w - 100, h - 100)

    def initializeGL(self) -> None:
        """ Инициализация. """

        glClearColor(0.0, 0.0, 0.0, 1.0)
        glClearDepth(1.0)


class AppWidget(QtWidgets.QWidget):
    """ Виждет который содержит наше приложение """

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.__glWidget = FigureWidget(self)
        self.__drawPathButton = QtWidgets.QPushButton('Draw Path')
        self.__drawFillButton = QtWidgets.QPushButton('Fill Figure')

        self.__drawPathButton.clicked.connect(lambda: self.__glWidget.drawPath())
        self.__drawFillButton.clicked.connect(lambda: self.__glWidget.drawFigure())

        lay = QtWidgets.QVBoxLayout()

        lay.addWidget(self.__glWidget)
        lay.addWidget(self.__drawPathButton)
        lay.addWidget(self.__drawFillButton)

        self.setLayout(lay)


class App(QtWidgets.QMainWindow):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        widget = AppWidget()

        self.setCentralWidget(widget)


if __name__ == '__main__':
    app = QtWidgets.QApplication(['Draw figure'])

    window = App()
    window.show()

    app.exec_()
