//
// Created by João Carvalho on 11/11/2022.
//

#ifndef FICHA4_EX5_AMIGO_H
#define FICHA4_EX5_AMIGO_H

#include <string>
#include <sstream>

using namespace std;

class Amigo {
    string nome;
    int tel;
public:
    Amigo(string nome, int tel): nome(nome), tel(tel) {} string getNome() const noexcept { return nome; }
    int getTel() const noexcept { return tel; }
    string getDesc() const noexcept {
        ostringstream oss;
        oss << nome << " - " << tel; return oss.str();
    }

    bool operator==(Amigo& a){
        return nome == a.nome;
    }
};

#endif //FICHA4_EX5_AMIGO_H
