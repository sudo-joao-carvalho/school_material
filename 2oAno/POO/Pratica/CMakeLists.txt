cmake_minimum_required(VERSION 3.23)
project(Prática)

set(CMAKE_CXX_STANDARD 14)

include_directories(Ficha2_ex9)

add_executable(Prática
        Ficha2_ex9/televisao.cpp
        Ficha2_ex9/televisao.h)
