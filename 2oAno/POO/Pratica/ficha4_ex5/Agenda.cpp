//
// Created by João Carvalho on 11/11/2022.
//

#include "Agenda.h"

using namespace std;

Agenda::Agenda(const string &nome):nome(nome) {}

void Agenda::addContacto(string nome, int telefone) {
    Amigo novoContacto(nome, telefone);

    for(const Amigo& novoContacto: contactos){
        if(novoContacto.getNome() == nome){
            return;
        }
    }
    contactos.push_back(novoContacto);
}

int Agenda::findContacto(string nome) {
    for(int i = 0; i < contactos.size(); i++){
        if(contactos[i].getNome() == nome){
            return contactos[i].getTel();
        }
    }
}

void Agenda::eliminaContacto(string nome) {
    vector<Amigo>::iterator it;

    for(it = contactos.begin(); it != contactos.end(); ++it){
        if(it->getNome() == nome){
            contactos.erase(it);
        }else
            it++;
    }
}

void Agenda::eliminaContactoWord(string nome) {
    /*vector<Amigo>::iterator it;

    for(it = contactos.begin(); it != contactos.end(); ++it){
        if(it->getNome().find(nome) != string::npos){
            contactos.erase(it);
        }else
            it++;
    }*/

    //fazes com o FIND
}

//fazer void elimina com o remove


