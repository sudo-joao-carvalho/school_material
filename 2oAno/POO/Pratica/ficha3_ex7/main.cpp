#include "banco.h"
#include "RegistoCivil.h"

//nao esquecer de ir onde diz nomefich | Debug -> ir as configuracoes -> e adicionar a pasta onde estamos a trabalhar o working environment

int main() {
    Pessoa p1("Goncalo Costa Enes", 9252309458, 6548689);
    Pessoa p2("Bom dia gentxi", 39242925,23582023);
    Pessoa p3("Joao Goncalves", 35908938, 52348292);
    Pessoa p4("Pões", 98543848, 5911111839);

    Banco b1("BPI");
    Banco b2("CGD");

    b1.addConta(&p1);

    b1.addConta(&p2);

    b2.addConta(&p3);

    b2.addConta(&p4);

    cout << b2.obtemInfoConta(35908938);

    //Implementacao do stor
    RegistoCivil registoCivil = registoCivil();
    registoCivil.carregarPessoas("pessoas.txt");

    registoCivil.adicionarPessoa({"Carlos", 123});
    cout << registoCivil.obtemTodasPessoas() << endl;

    Banco ca("CA");
    ca.addConta(&registoCivil.obtemPessoa(123));
    cout << ca.obtemTodosClientes() << endl;

    registoCivil.exportarPessoas("novasPessoas.txt");
    //Implementacao do stor

    return 0;
}
