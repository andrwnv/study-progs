from PyQt5.QtOpenGL import *
from OpenGL.GL import *

from PyQt5 import QtWidgets, QtCore


class FigureWidget(QGLWidget):
    """ Виджет для отрисовки фигуры. """

    def __init__(self, parent):
        super(FigureWidget, self).__init__()

        self.setMinimumSize(800, 500)

        # Init matrix using list comprehension.
        self.__matrix = [[0. for _ in range(15)] for _ in range(2)]
        self.__matrix_2 = [[0. for _ in range(15)] for _ in range(2)]

        self.clear_called = False

        # Init variables.
        self.n: int = 15

        self.__x: float = 0.2
        self.__y: float = 0.4
        self.__x1: float = 0.12
        self.__y1: float = 0.2

        self.__angle_a: float = 0.
        self.__angle_b: float = 0.

        self.__scale_a: float = 1.
        self.__scale_b: float = 1.

        self.__offset_aX: float = 0.
        self.__offset_bX: float = 0.
        self.__offset_aY: float = 0.
        self.__offset_bY: float = 0.

        self.__circle_rotation: bool = False
        self.__draw_around: bool = False

    def move_first_figure_x(self, offset: float):
        self.__offset_aX += offset
        self.update()

    def move_first_figure_y(self, offset: float):
        self.__offset_aY += offset
        self.update()

    def rotate_first_figure(self, angle: float):
        self.__angle_a += angle
        self.update()

    def scale_first_figure(self, k: float):
        self.__scale_a += k
        self.update()

    def move_second_figure_x(self, offset: float):
        self.__offset_bX += offset
        self.update()

    def move_second_figure_y(self, offset: float):
        self.__offset_bY += offset
        self.update()

    def rotate_second_figure(self, angle: float):
        self.__angle_b += angle
        self.update()

    def scale_second_figure(self, k: float):
        self.__scale_b += k
        self.update()

    def rotate_circle(self):
        if self.__angle_a == 360.:
            self.__angle_a = 0.

        while self.__angle_a < 360:
            self.__angle_a += 0.5
            self.update()

    def draw_first_figure(self):
        glPushMatrix()
        glTranslatef(0.5, 0.5, 0.)
        glRotatef(self.__angle_a, 0., 0., 1)
        glTranslatef(self.__offset_aX, self.__offset_aY, 0.)
        glScalef(self.__scale_a, self.__scale_a, 0)

        glColor3f(1., 0.5, 0.8)

        # Draw dashed lines.
        glLineStipple(1, 0x00FF)  # Define the stipple pattern.
        glEnable(GL_LINE_STIPPLE)  # Enable stipple lines.
        glLineWidth(1)
        glBegin(GL_LINES)

        glVertex2f(-0.5 / 3, -0.5 / 3)
        glVertex2f(0.5 / 3, -0.5 / 3)
        glVertex2f(0, -0.5 / 3)
        glVertex2f(0, -1 / 3)
        glVertex2f(-0.5 / 3, -0.5 / 3)
        glVertex2f(0, 1 / 3)
        glVertex2f(0.5 / 3, -0.5 / 3)
        glVertex2f(0, 1 / 3)
        glVertex2f(0.5 / 3, 0.5 / 3)
        glVertex2f(0.25 / 3, 0.25 / 3)
        glVertex2f(-0.5 / 3, 0.5 / 3)
        glVertex2f(-0.25 / 3, 0.25 / 3)
        glVertex2f(-0.25 / 3, 0.25 / 3)
        glVertex2f(0.25 / 3, 0.25 / 3)
        glVertex2f(0.25 / 3, 0.25 / 3)
        glVertex2f(0, -0.5 / 3)
        glVertex2f(-0.25 / 3, 0.25 / 3)
        glVertex2f(0, -0.5 / 3)

        glEnd()
        glDisable(GL_LINE_STIPPLE)  # Disable stipple lines.

        # Draw figure.
        glLineWidth(2)
        glBegin(GL_LINE_LOOP)

        glVertex2f(0., 1 / 3.)
        glVertex2f(0.5 / 3., 0.5 / 3.)
        glVertex2f(0.5 / 3., -0.5 / 3.)
        glVertex2f(0., -1 / 3.)
        glVertex2f(-0.5 / 3., -0.5 / 3.)
        glVertex2f(-0.5 / 3., 0.5 / 3.)
        glVertex2f(0., 1 / 3.)

        glEnd()
        glPopMatrix()

    def draw_house(self):
        glPushMatrix()
        glTranslatef(self.__offset_bX, self.__offset_bY, 0.)
        glRotatef(self.__angle_b, 0., 0., 1.)
        glScalef(self.__scale_b, self.__scale_b, 0.)

        glColor3f(1., 1., 1.)
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)
        glBegin(GL_QUADS)
        glVertex2f(-0.5 / 3, 0.8 / 3)
        glVertex2f(-0.5 / 3, -0.8 / 3)
        glVertex2f(0.5 / 3, -0.8 / 3)
        glVertex2f(0.5 / 3, 0.8 / 3)
        glEnd()

        glBegin(GL_TRIANGLES)
        glVertex2f(0.7 / 3, 0.8 / 3)
        glVertex2f(0., 1 / 2)
        glVertex2f(-0.7 / 3, 0.8 / 3)
        glEnd()

        glPopMatrix()

    def paintGL(self) -> None:
        """ Отрисовка фигуры. """

        if self.clear_called:
            self.clear_called = False
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
            return

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity()

        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST)

        # Draw first figure in loop.
        if self.__circle_rotation:
            self.__circle_rotation = False

            if self.__angle_a == 360.:
                self.__angle_a = 0.

            while self.__angle_a < 360.:
                self.__angle_a += 30.
                self.draw_first_figure()

        # Draw figure.
        self.draw_first_figure()

        # Draw house.
        self.draw_house()

        glFlush()

    def draw_around(self, angle: float):
        self.__angle_a += angle

        if angle < 0:
            self.move_first_figure_y(0.05)
            self.move_first_figure_x(-0.05)
        elif angle > 0:
            self.move_first_figure_y(-0.05)
            self.move_first_figure_x(0.05)

        self.update()

    def clear(self):
        """ Отчистка экрана. """

        self.clear_called = True
        self.update()

    def resizeGL(self, w, h) -> None:
        """ Изменение размера окна. """

        glViewport(50, 50, w - 100, h - 100)

    def initializeGL(self) -> None:
        """ Инициализация. """

        glClearColor(0.0, 0.0, 0.0, 1.0)
        glClearDepth(1.0)

    def circle_draw(self):
        self.__circle_rotation = True
        self.update()


class AppWidget(QtWidgets.QWidget):
    """ Виждет который содержит наше приложение """

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.__glWidget = FigureWidget(self)

        self.__defaultButton = QtWidgets.QPushButton('Clear')

        self.__defaultButton.clicked.connect(lambda: self.__glWidget.clear())

        lay = QtWidgets.QVBoxLayout()

        lay.addWidget(self.__glWidget)
        lay.addWidget(self.__defaultButton)

        self.setLayout(lay)

    def keyPressEvent(self, event):
        # First figure.
        if event.key() == QtCore.Qt.Key_W:  # Push up first figure.
            self.__glWidget.move_first_figure_y(0.05)
        elif event.key() == QtCore.Qt.Key_S:  # Push down first figure.
            self.__glWidget.move_first_figure_y(-0.05)
        elif event.key() == QtCore.Qt.Key_A:  # Push left first figure.
            self.__glWidget.move_first_figure_x(-0.05)
        elif event.key() == QtCore.Qt.Key_D:  # Push right first figure.
            self.__glWidget.move_first_figure_x(0.05)
        elif event.key() == QtCore.Qt.Key_Q:  # Rotate left first figure.
            self.__glWidget.rotate_first_figure(-1)
        elif event.key() == QtCore.Qt.Key_E:  # Rotate right first figure.
            self.__glWidget.rotate_first_figure(1)
        elif event.key() == QtCore.Qt.Key_Z:  # Scale first figure.
            self.__glWidget.scale_first_figure(0.5)
        elif event.key() == QtCore.Qt.Key_X:  # Scale first figure.
            self.__glWidget.scale_first_figure(-0.5)

        # Second figure.
        if event.key() == QtCore.Qt.Key_U:  # Push up first figure.
            self.__glWidget.move_second_figure_y(0.05)
        elif event.key() == QtCore.Qt.Key_J:  # Push down first figure.
            self.__glWidget.move_second_figure_y(-0.05)
        elif event.key() == QtCore.Qt.Key_H:  # Push left first figure.
            self.__glWidget.move_second_figure_x(-0.05)
        elif event.key() == QtCore.Qt.Key_K:  # Push right first figure.
            self.__glWidget.move_second_figure_x(0.05)
        elif event.key() == QtCore.Qt.Key_Y:  # Rotate left first figure.
            self.__glWidget.rotate_second_figure(-1)
        elif event.key() == QtCore.Qt.Key_I:  # Rotate right first figure.
            self.__glWidget.rotate_second_figure(1)
        elif event.key() == QtCore.Qt.Key_Plus:  # Scale first figure.
            self.__glWidget.scale_second_figure(0.5)
        elif event.key() == QtCore.Qt.Key_Minus:  # Scale first figure.
            self.__glWidget.scale_second_figure(-0.5)
        elif event.key() == QtCore.Qt.Key_C:
            self.__glWidget.circle_draw()


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
