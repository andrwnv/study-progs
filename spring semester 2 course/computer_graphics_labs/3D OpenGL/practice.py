from PyQt5.QtOpenGL import *
from OpenGL.GL import *

from PyQt5 import QtWidgets, QtCore

import math


class FigureWidget(QGLWidget):
    """ Main OpenGL widget. """

    def __init__(self, parent):
        super(FigureWidget, self).__init__()

        self.setMinimumSize(1280, 720)

        self.__rotate_angle_y = 70
        self.__rotate_angle_x = 50
        self.__rotate_angle_z = 0

        self.__zoom_coefficient = -3

        self.setFocusPolicy(QtCore.Qt.StrongFocus)

        self.__timer = QtCore.QTimer()
        self.__timer.setInterval(250)
        self.__timer.timeout.connect(lambda: self.idle())
        self.__timer.start()

    def idle(self):
        self.__rotate_angle_y += 0.1
        self.update()

    def paintGL(self) -> None:
        """ Draw scene. """

        glClear(GL_COLOR_BUFFER_BIT)

        glClearColor(0, 0, 0, 1.0)
        glColor3f(1.0, 1.0, 1.0)

        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        glFrustum(-3, 3, -2, 2, 1.2, 40)

        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glTranslatef(0, 0, self.__zoom_coefficient)
        glRotatef(self.__rotate_angle_x, 1, 0, 0)
        glRotatef(self.__rotate_angle_y, 0, 1, 0)
        glRotatef(self.__rotate_angle_z, 0, 0, 1)

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        glColor3f(1.0, 1.0, 1.0)
        glBegin(GL_LINES)

        i: float = -2.5

        #  Draw coordinate grid.
        while i <= 2.5:
            glVertex3f(i, 0, 2.5)
            glVertex3f(i, 0, -2.5)
            glVertex3f(2.5, 0, i)
            glVertex3f(-2.5, 0, i)
            i += 0.25

        glEnd()

        glBegin(GL_TRIANGLE_STRIP)

        #  1st face.
        glColor3f(1, 0, 1)
        glVertex3f(0, 2, 0)
        glColor3f(0, 0, 1)
        glVertex3f(-1, 0, 0)
        glColor3f(0, 1, 0)
        glVertex3f(- 1. / 2., 0, math.pow(3, 0.5) / 2)

        #  2nd face.
        glColor3f(1, 1, 0)
        glVertex3f(0, 2, 0)
        glColor3f(0, 0, 1)
        glVertex3f(- 1. / 2., 0, math.pow(3, 0.5) / 2)
        glColor3f(1, 0, 1)
        glVertex3f(0.5, 0, math.pow(3, 0.5) / 2)

        #  3th face.
        glColor3f(0, 1, 1)
        glVertex3f(0, 2, 0)
        glColor3f(0, 0, 1)
        glVertex3f(0.5, 0, math.pow(3, 0.5) / 2)
        glColor3f(1, 0, 1)
        glVertex3f(1, 0, 0)

        #  4th face.
        glColor3f(0, 0, 1)
        glVertex3f(0, 2, 0)
        glColor3f(0, 1, 0)
        glVertex3f(1, 0, 0)
        glColor3f(1, 0, 1)
        glVertex3f(0.5, 0, -math.pow(3, 0.5) / 2)

        #  5th face.
        glColor3f(1, 0, 0)
        glVertex3f(0, 2, 0)
        glColor3f(0, 0, 1)
        glVertex3f(0.5, 0, -math.pow(3, 0.5) / 2)
        glColor3f(1, 0, 1)
        glVertex3f(-0.5, 0, -math.pow(3, 0.5) / 2)

        #  6th face.
        glColor3f(0, 1, 0)
        glVertex3f(0, 2, 0)
        glColor3f(1, 1, 0)
        glVertex3f(-0.5, 0, -math.pow(3, 0.5) / 2)
        glColor3f(0, 0, 1)
        glVertex3f(-1, 0, 0)

        glEnd()

        glFlush()

    def resizeGL(self, w, h) -> None:
        """ Resize event. """

        glViewport(50, 50, w - 100, h - 100)

    def initializeGL(self) -> None:
        """ Init OpenGL. """

        glEnable(GL_CULL_FACE)
        glCullFace(GL_FRONT)

        #  Enable depth.
        glEnable(GL_DEPTH_TEST)

        glClearColor(0.1, 0.39, 0.88, 1.0)
        glColor3f(1.0, 1.0, 1.0)

        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        glFrustum(-2, 2, -1.5, 1.5, 1, 40)

        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glTranslatef(0, 0, -3)
        glRotatef(70, 0, 1, 0)

        glDisable(GL_BLEND)

    def keyPressEvent(self, event):
        if event.key() == QtCore.Qt.Key_A:
            self.__rotate_angle_y -= 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_D:
            self.__rotate_angle_y += 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_W:
            self.__rotate_angle_x += 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_S:
            self.__rotate_angle_x -= 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_Q:
            self.__rotate_angle_z += 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_E:
            self.__rotate_angle_z -= 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_Plus:
            self.__zoom_coefficient += 0.5
            self.update()
        elif event.key() == QtCore.Qt.Key_Minus:
            self.__zoom_coefficient -= 0.5
            self.update()


class App(QtWidgets.QMainWindow):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.__glWidget = FigureWidget(self)
        self.setCentralWidget(self.__glWidget)


if __name__ == '__main__':
    app = QtWidgets.QApplication(['3D OpenGL'])

    window = App()
    window.show()

    app.exec_()
