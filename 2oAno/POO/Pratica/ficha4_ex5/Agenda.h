//
// Created by João Carvalho on 11/11/2022.
//

#ifndef FICHA4_EX5_AGENDA_H
#define FICHA4_EX5_AGENDA_H

#include <string>
#include <sstream>
#include <vector>
#include "Amigo.h"

using namespace std;

class Agenda{
public:
    Agenda(const string& nome);
    void addContacto(string nome, int telefone); //outra alternativa const Amigo& amigo
    int findContacto(string nome);
    void eliminaContacto(string nome);
    void eliminaContactoWord(string nome);
    string getContactosAsString() const noexcept;
    string listarNomesComTelefoneEntre(int min, int max) const;
    void atualizaContacto(string nome, int telefone);

private:
    string nome;
    vector<Amigo> contactos;
};

#endif //FICHA4_EX5_AGENDA_H
