//
// Created by João Carvalho on 03/11/2022.
//

#include "RegistoCivil.h"
#include <fstream>

void RegistoCivil::carregarPessoas(const string& filename){
     //abrir um novo file descriptor para abrir o ficheiro
     ifstream ficheiro(filename);

     if(ficheiro.is_open() && ficheiro.good()){
         int contador = 0;
         string linha;
         string nome;
         int cc;
         int nif;

         while(getline(ficheiro, linha) && contador < limiteLeitura){
             //fazer parse dos comandos
             istringstream  iss(linha);
             iss >> nome >> cc >> nif;

             Pessoa p(nome, cc, nif);
             listOfPessoas.push_back(p);

             contador++;
         }
     }

     ficheiro.close();
}

void RegistoCivil::exportarPessoas(const string &filename) {

    ofstream ficheiro(filename);

    for(const Pessoa& p : listOfPessoas){
        ficheiro << p.getNome() << " " << p.getNBI() << " " << p.getContribuinte() << endl;
    }

    ficheiro.close();
}

void RegistoCivil::adicionarPessoa(const Pessoa &pessoa) {
    Pessoa p{pessoa};
    listOfPessoas.push_back(p);
}

Pessoa &RegistoCivil::obtemPessoa(const int &cc) {

    static Pessoa lixo = {"", -1, -1}; //para o caso de nao existir nenhuma pessoa no vetor ou uma pessoa com aquele BI

    for(Pessoa& p : listOfPessoas){ // como retorna uma referencia nao uso const
        /*if(p.obtemBI() == cc){
            return p;
        }*/
    }

    return lixo;
}

string RegistoCivil::obtemPessoa() const {

    ostringstream oss;

    for(const Pessoa& p : listOfPessoas){
        oss << "Nome: " <<  p.getNome()
            << "\tCC: " << p.getNBI()
            << "\tNIF: " << p.getContribuinte() << endl;
    }

}





