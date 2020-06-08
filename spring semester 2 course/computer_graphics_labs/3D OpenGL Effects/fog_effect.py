from PyQt5.QtOpenGL import *
from OpenGL.GL import *

from PyQt5 import QtWidgets, QtCore


class FigureWidget(QGLWidget):
    """ Main OpenGL widget. """

    def __init__(self, parent):
        super(FigureWidget, self).__init__()

        self.setMinimumSize(1280, 720)

        self.__rotate_angle_y = 70
        self.__rotate_angle_x = 15
        self.__rotate_angle_z = 0

        self.__zoom_coefficient = -5

        self.setFocusPolicy(QtCore.Qt.StrongFocus)

        self.__timer = QtCore.QTimer()
        self.__timer.setInterval(30)
        # self.__timer.timeout.connect(lambda: self.idle())
        self.__timer.start()

        self.__light_position_x: [] = [3., 0., 0., 0.]
        self.__light_position_y: [] = [0., 3., 0., 0.]
        self.__light_position_z: [] = [0., 0., 3., 0.]

        self.__light_diffuse: [] = [0.78, 0.57, 0.11, 1.0]
        self.__light_specular: [] = [0.99, 0.94, 0.80, 1.0]
        self.__light_ambient: [] = [0.33, 0.2, 0.80, 1.0]
        self.__shine: float = 27.8974

        self.__light_position: [] = self.__light_position_x

        self.__material = GL_AMBIENT

    def idle(self):
        self.__rotate_angle_y += 0.5
        self.update()

    def set_light_x(self):
        self.__light_position = self.__light_position_x
        self.update()

    def set_light_y(self):
        self.__light_position = self.__light_position_y
        self.update()

    def set_light_z(self):
        self.__light_position = self.__light_position_z
        self.update()

    def set_material(self, material_tag):
        self.__material = material_tag
        self.update()

    def paintGL(self) -> None:
        """ Draw scene. """

        glClearColor(0, 0, 0, 1.0)
        glColor3f(1.0, 1.0, 1.0)

        #  Enable light.
        glEnable(GL_NORMALIZE)

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

        if self.__material == GL_AMBIENT:
            glLightfv(GL_LIGHT0, GL_AMBIENT, self.__light_ambient)
            glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, (0, 1, 0))
            glEnable(GL_LIGHT0)
        elif self.__material == GL_DIFFUSE:
            glLightfv(GL_LIGHT0, GL_DIFFUSE, self.__light_diffuse)
            glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, (0, 1, 0))
            glEnable(GL_LIGHT0)
        elif self.__material == GL_SPECULAR:
            glLightfv(GL_LIGHT0, GL_SPECULAR, self.__light_specular)
            glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, (0, 1, 0))
            glEnable(GL_LIGHT0)
        elif self.__material == GL_EMISSION:
            glLightfv(GL_LIGHT0, GL_DIFFUSE, self.__light_diffuse)
            glMaterialfv(GL_FRONT_AND_BACK, GL_EMISSION, (0, 1, 0))
            glEnable(GL_LIGHT0)

        glLightfv(GL_LIGHT0, GL_POSITION, self.__light_position)

        glLightModelf(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE)
        glEnable(GL_LIGHTING)

        glEnable(GL_LIGHT1)
        glEnable(GL_LIGHT2)

        glBegin(GL_QUADS)

        glNormal3f(0.5, 1.0, 0.0)
        glVertex3f(2.0, 1.0, -1.0)
        glVertex3f(-2.0, 1.0, -1.0)
        glVertex3f(-2.0, 1.0, 1.0)
        glVertex3f(2.0, 1.0, 1.0)

        glNormal3f(0.5, -1.0, 0.0)
        glVertex3f(2.0, -1.0, 1.0)
        glVertex3f(-2.0, -1.0, 1.0)
        glVertex3f(-2.0, -1.0, -1.0)
        glVertex3f(2.0, -1.0, -1.0)

        glNormal3f(1.0, 0.5, 0.0)
        glVertex3f(2.0, 1.0, 1.0)
        glVertex3f(-2.0, 1.0, 1.0)
        glVertex3f(-2.0, -1.0, 1.0)
        glVertex3f(2.0, -1.0, 1.0)

        glNormal3f(-1.0, 0.5, 0.0)
        glVertex3f(2.0, -1.0, -1.0)
        glVertex3f(-2.0, -1.0, -1.0)
        glVertex3f(-2.0, 1.0, -1.0)
        glVertex3f(2.0, 1.0, -1.0)

        glNormal3f(0.0, 0.0, 1.0)
        glVertex3f(-2.0, 1.0, 1.0)
        glVertex3f(-2.0, 1.0, -1.0)
        glVertex3f(-2.0, -1.0, -1.0)
        glVertex3f(-2.0, -1.0, 1.0)

        glNormal3f(0.0, 0.0, -1.0)
        glVertex3f(2.0, 1.0, -1.0)
        glVertex3f(2.0, 1.0, 1.0)
        glVertex3f(2.0, -1.0, 1.0)
        glVertex3f(2.0, -1.0, -1.0)

        glEnd()

        glFlush()

    def resizeGL(self, w, h) -> None:
        """ Resize event. """

        glViewport(50, 50, w - 100, h - 100)

    def initializeGL(self) -> None:
        """ Init OpenGL. """

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


class App(QtWidgets.QWidget):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        self.__glWidget = FigureWidget(self)

        self.__layout = QtWidgets.QVBoxLayout()
        self.setLayout(self.__layout)

        self.__x_button = QtWidgets.QPushButton("X")
        self.__y_button = QtWidgets.QPushButton("Y")
        self.__z_button = QtWidgets.QPushButton("Z")

        self.__ambient_button = QtWidgets.QPushButton("Ambient")
        self.__diffuse_button = QtWidgets.QPushButton("Diffuse")
        self.__specular_button = QtWidgets.QPushButton("Specular")
        self.__emission_button = QtWidgets.QPushButton("Emission")

        self.__x_button.clicked.connect(lambda: self.__glWidget.set_light_x())
        self.__y_button.clicked.connect(lambda: self.__glWidget.set_light_y())
        self.__z_button.clicked.connect(lambda: self.__glWidget.set_light_z())
        self.__ambient_button.clicked.connect(lambda: self.__glWidget.set_material(GL_AMBIENT))
        self.__diffuse_button.clicked.connect(lambda: self.__glWidget.set_material(GL_DIFFUSE))
        self.__specular_button.clicked.connect(lambda: self.__glWidget.set_material(GL_SPECULAR))
        self.__emission_button.clicked.connect(lambda: self.__glWidget.set_material(GL_EMISSION))

        self.__coordinate_button_group = QtWidgets.QHBoxLayout()
        self.__coordinate_button_group.addWidget(self.__x_button)
        self.__coordinate_button_group.addWidget(self.__y_button)
        self.__coordinate_button_group.addWidget(self.__z_button)

        self.__material_button_group = QtWidgets.QHBoxLayout()
        self.__material_button_group.addWidget(self.__ambient_button)
        self.__material_button_group.addWidget(self.__diffuse_button)
        self.__material_button_group.addWidget(self.__specular_button)
        self.__material_button_group.addWidget(self.__emission_button)

        self.__layout.addWidget(self.__glWidget)
        self.__layout.addLayout(self.__coordinate_button_group)
        self.__layout.addLayout(self.__material_button_group)


if __name__ == '__main__':
    app = QtWidgets.QApplication(['3D OpenGL'])

    window = App()
    window.show()

    app.exec_()
