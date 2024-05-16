cmake_minimum_required(VERSION 3.23)
project(Ficha7 C)

set(CMAKE_C_STANDARD 11)

include_directories(CodigoTrepaColinas)

add_executable(Ficha7
        CodigoTrepaColinas/algoritmo.c
        CodigoTrepaColinas/algoritmo.h
        CodigoTrepaColinas/funcao.c
        CodigoTrepaColinas/funcao.h
        CodigoTrepaColinas/main.c
        CodigoTrepaColinas/utils.c
        CodigoTrepaColinas/utils.h)
