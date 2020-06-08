from OpenGL.raw.GLU import gluPerspective
from PyQt5.QtOpenGL import *
from OpenGL.GLUT import *
from OpenGL.GL import *

from PyQt5 import QtWidgets, QtCore


class FigureWidget(QGLWidget):
    """ Main OpenGL widget. """

    def __init__(self, parent):
        super(FigureWidget, self).__init__()

        self.setMinimumSize(1280, 720)

        self.setFocusPolicy(QtCore.Qt.StrongFocus)

        self.__timer = QtCore.QTimer()
        self.__timer.setInterval(30)
        self.__timer.timeout.connect(lambda: self.idle())
        self.__timer.start()

        self.__rotate = 0

        self.__fog_configuration: [] = [0, 0, 0, 1]

    def idle(self):
        self.__rotate += 1
        self.update()

    def paintGL(self) -> None:
        """ Draw scene. """

        glClearColor(0, 0, 0, 1.0)

        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        gluPerspective(45, self.width() / self.height(), 0.1, 200)
        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glTranslated(-0.5, 0, -8)
        glClearColor(0, 0, 0, 1.0)
        glViewport(0, 0, self.width(), self.height())
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        glPushMatrix()
        glColor4f(1, 0, 0, 1)
        glTranslated(-1, 0, 0)
        glRotatef(110, 20, 1, 0)
        glutWireCylinder(0.5, 0.5, 10, 10)
        glPopMatrix()

        glPushMatrix()
        glColor4f(0, 1, 0, 1)
        glTranslated(-1, 0, 0)
        glRotatef(self.__rotate, 0, 1, 1)
        glTranslatef(2, 0, 0)
        glutSolidTorus(0.15, 0.65, 16, 16)
        glPopMatrix()

        glPushMatrix()
        glColor4f(0, 0, 1, 0.5)
        glTranslated(-1, 0, 0)
        glRotatef(self.__rotate, 0, 1, 1)
        glTranslatef(2, 0, 0)
        glutSolidIcosahedron()
        glPopMatrix()

    def resizeGL(self, w, h) -> None:
        """ Resize event. """

        glViewport(50, 50, w - 100, h - 100)

    def initializeGL(self) -> None:
        """ Init OpenGL. """

        glutInit()

        #  Enable depth.
        glEnable(GL_DEPTH_TEST)

        glClearColor(0, 0, 0, 1.0)

        glEnable(GL_BLEND)  # For transparency.
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH)

        glEnable(GL_FOG)
        glFogi(GL_FOG_MODE, GL_LINEAR)  # Set fog mode.
        glFogfv(GL_FOG_COLOR, self.__fog_configuration)  # Set fog color.
        glHint(GL_FOG_HINT, GL_DONT_CARE)  # Fog Hint Value.
        glFogf(GL_FOG_DENSITY, 10)  # Fog dense.
        glFogf(GL_FOG_START, 5)  # Fog start depth.
        glFogf(GL_FOG_END, 9)  # Fog end depth.


class App(QtWidgets.QWidget):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.__glWidget = FigureWidget(self)

        self.__layout = QtWidgets.QVBoxLayout()
        self.setLayout(self.__layout)

        self.__layout.addWidget(self.__glWidget)


if __name__ == '__main__':
    app = QtWidgets.QApplication(['3D OpenGL'])

    window = App()
    window.show()

    app.exec_()

