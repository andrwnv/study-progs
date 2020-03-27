QT += widgets

HEADERS       = \
    convas.h
SOURCES       = main.cpp \
    convas.cpp

unix:!mac:!vxworks:!integrity:!haiku:LIBS += -lm

target.path = $$[QT_INSTALL_EXAMPLES]/corelib/threads/julia
INSTALLS += target
