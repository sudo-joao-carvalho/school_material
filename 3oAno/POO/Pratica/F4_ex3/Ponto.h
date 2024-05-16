//
// Created by João Carvalho on 12/10/2023.
//

#ifndef FICHA4_EX1_PONTO_H
#define FICHA4_EX1_PONTO_H

#include <string>
#include <sstream>
#include <cmath>

using namespace std;

class Ponto {
private:
    int x, y;

public:
    //Ponto(int x = 0, int y = 0);
    Ponto(int x, int y);
    ~Ponto();

    int getX() const;

    void setX(int x);

    int getY() const;

    void setY(int y);

    double distanceBetweenPoints(int otherX, int otherY);

    double distanceBetweenPoints(Ponto otherPoint);

    string getAsString() const;
};

#endif //FICHA4_EX1_PONTO_H