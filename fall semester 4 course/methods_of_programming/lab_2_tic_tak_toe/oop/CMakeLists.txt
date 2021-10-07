cmake_minimum_required(VERSION 3.19)
project(tik_tak_toe_oop)

set(CMAKE_CXX_STANDARD 20)

add_executable(tik_tak_toe_oop
        src/Player.h
        src/Board.h
        src/GameController.h
        main.cpp
)
