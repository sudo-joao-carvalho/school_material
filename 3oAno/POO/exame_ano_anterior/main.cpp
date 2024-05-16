#include <iostream>
#include <map>

using namespace std;
int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}

/*
 *  Pergunta 1
 *
 *  Na linha Ponto(int x, int y) { x = x; y = y; } {}
 *  -> as chavetas finais estao a mais, e x = x e y = y da erro pois assim nao se sabe qual x e qual y é o da classe ou se é o recebido por parametro, no cpp usar a lista de inicializaçao
 *
 *  Na linha 9 e 10 nao pode ser const porque vamos estar a alterar o valor de x dentro da funcao logo nao podemos estar a dizer que é constante mas estamos a alterar um valor
 *
 *  Na linha 13 deveria ser class e nao struct pois existe um construtor e um destrutor para esse objeto
 *
 *  Na linha 14 e 15 nao podem ser const visto que lhes vai ser atribuido um valor no construtor
 *
 *  Na linha 6 e 17 os argumentos recebidos no construtor podem ser const [tipo]& pois como nao vao ser alterados dentro do construtor podem ser recebidos como redferencia e constantes
 *
 *  Na linha 24 a & deveria estar junto ao ostream (????????)
 *
 *  Na linha 32 para fazer f = g deveria ser redefinido o operador = pois os campos de g nao tem valores nenhuns e de f tem e assim nao seriam atribuidos nenhuns valores ou nao saberiam quais valores atribuir a quais
 *
 *  A linha 33 seria impossivel de executar pois na linha 24 é recebido como parametro um objeto do tipo ostream e nao um objeto do tipo Figura, ou seja na linha 24 deveria ter um objeto do tipo Figura dentro dos ()
 *
 *  Linha 24 o operador tem que ser global
 * */

/*
 * Pergunta 2
 *
 * -- start --
 * A default constructor
 * B constructor v=1
 * -----------
 * A default constructor
 * B constructor v=2
 * -----------
 * A constructor v=5
 * B= v=3
 * -----------
 * A default constructor
 * -----------
 * A default constructor
 * B constructor v=4
 * v=4
 * ~B destructor v=4
 * ~A destructor v=4
 * --  end  --
 *
 * */

//Correcao
/*
 * -- start --
A defualt
B constructor v=1
-----------
A defualt
B constructor v=2
~B destructor v=2
~A destructor v=2
-----------
A constructor v=5
B default constructor
B= v=3
-----------
A defualt
-----------
A defualt
B constructor v=4
v=4
~B destructor v=4
~A destructor v=4
--  end  --
~A destructor v=0
~B destructor v=3
~A destructor v=3
~B destructor v=1
~A destructor v=1
 *
 * */

/*class A{
public:
    A(){cout << "A defualt\n";}
    A(int v){
        this->v = v;
        cout << "A constructor v=" << v << "\n";
    }

    virtual ~A() {cout << "~A destructor v=" << v << "\n";}
    void printValue(){cout << "v=" << v << "\n";}
    int v=0;
};

class B : public A {
public:
    B() : A(5) { cout << "B default constructor\n"; }

    B(int v) {
        this->v = v;
        cout << "B constructor v=" << v << "\n";
    }

    B &operator=(int v) {
        this->v = v;
        cout << "B= v=" << v << "\n";
        return *this;
    }

    ~B() { cout << "~B destructor v=" << v << "\n"; }

    void printValue() { cout << "v=" << v << "\n"; }
};

int main(){
    cout << "-- start --\n";
    B b1(1);
    cout << "-----------\n";
    { B b2 = 2; }
    cout << "-----------\n";
    B b3;
    b3 = 3;
    cout << "-----------\n";
    A a;
    cout << "-----------\n";
    A* p = new B(4);
    p->printValue();
    delete p;
    cout << "--  end  --\n";
    return 0;
}*/

//Pergunta 3 a)

class Transporte{
public:
    Transporte():isParado(false){};
    virtual ~Transporte();

    void estaACircular();
    virtual void paradoNaParagem() = 0;
    virtual void iniciaMovimento() = 0;
    void iniciaMovimento(){ isParado = true; }
    void trava(){isParado = false; }
    void admitePassageiros();
    void deixaSair(const int& bilhete){
        if(!isParado){
            for(map<int, string>::iterator it = passageiros.begin(); it != passageiros.end();){
                if(it->first == bilhete){
                    passageiros.erase(it);
                    return;
                }
                else ++it;
            }
        }

    }

    map<int, string> getPassageiros(){ return passageiros; }

private:
    bool isParado;
    map<int, string> passageiros;
};

class Autocarro: public Transporte{
public:
    Autocarro(const int& numLugares, vector<string> rotas): Transporte(), rotas(rotas){
        if(20 <= numLugares && numLugares <= 60){
            throw "#Deu xereca";
        }

    }

    void alteraRota(vector<string> rotas){
        if(getPassageiros().empty())
            this->rotas = rotas;
    }

    //Pergunta 3 b)
    void eliminaParagem(const string& nomeParagem){
        //vector<string>::iterator it = find(rotas.begin(), rotas.end(), nomeParagem);
        /*if(it != rotas.end()){
            remove(it);
        }*/

        vector<string>::iterator it = remove(rotas.begin(), rotas.end(), nomeParagem);
        if(it != rotas.end()) cout << "rota eliminada";
        else cout << "nao existe nenhuma rota com este nome";
        /*for(map<int, string>::iterator it = rotas.begin(); it != rotas.end();){
            if(it->second == nomeParagem){
                passageiros.erase(it);
                return;
            }
            else ++it;
        }*/
    }

private:
    int numLugares;

    vector<string> rotas;
};

//Pergunta 4
//a)
Venda(Imovel* imovel):imovel(imovel);

Venda& operator<<(Oferta& o){
    this->ofertas.push_back(new Oferta(o));
    return *this;
}

Venda& operator-=(const int& valor){
    for(vector<Oferta*>::iterator it = ofertas.begin(); it != ofertas.end();){
        if((*it)->getValor() < valor){
            it = ofertas.erase(it);
            delete *it;
        }else{
            ++it;
        }
    }

    return *this;
}

Venda& operator--(){
    int maisBaixa;

    for(vector<Oferta*>::iterator it = ofertas.begin(); it != ofertas.end(); it++){
        if(it == ofertas.begin()){
            maisBaixa = (*it)->getValor();
            continue;
        }
        if((*it)->getValor() < maisBaixa){
            maisBaixa = (*it)->getValor();
        }

        if(it == ofertas.end()){
            remove(ofertas.begin(), ofertas.end(), maisBaixa);
        }
    }

    return *this;
}

Oferta& operator[](const string& nome){
    for(vector<Oferta*>::iterator it = ofertas.begin(); it != ofertas.end(); ){
        if((*it)->getNome() == nome){
            return *it;
        }else ++it;
    }
}