cmake_minimum_required(VERSION 3.14)
project(unit_tests LANGUAGES CXX VERSION 0.0.1)

set(STUD ../)

find_package(Catch2 REQUIRED)
add_executable(unit_tests Tests.cpp ${STUD}/Student.cpp)
target_link_libraries(unit_tests Catch2::Catch2)

include(CTest)
include(ParseAndAddCatchTests)
ParseAndAddCatchTests(unit_tests)
