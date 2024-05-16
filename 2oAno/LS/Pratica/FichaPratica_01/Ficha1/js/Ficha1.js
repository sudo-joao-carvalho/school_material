'use strict'; 

//EXERCICIO_1

/*//A)

console.log("A)");
let n1=3;
let n2=6;
let n3;
console.log(n1+n2);     //1) 9
console.log(n1+"n3");   //2) 3n3
console.log(n1+"5");    //3) 35
console.log(n1+"n2");   //4) 3n2
console.log(n1+n3);     //5) NaN
console.log(n3);        //6) Undifined
console.log(`Variavel n1*n2 = ${n1*n2} ( n1=${n1} e n2=${n2})`); //7) Variavel n1*n2 = 18 ( n1=3 e n2=6 ) --> string interpolation
n3=n1+n2;
console.log(n3);    //8) 9
n3=n1+"---"+n2; 
console.log(n3);    //9) 3---6
n3="6"; 
console.log(n1+n3);     //10) 36
console.log(n2===n3);   //11) True ou False? False pois, === verifica tambem se o tipo de dados é igual e como n2 é um numero e n3 uma string entao e false
console.log(n2==n3);    //12) True ou False? True, como tem o mm valor devolve true
console.log(n2!==n3);   //13) True ou False? True, === verifica o datatype tambem e como sao datatypes diferentes devolve true
console.log(n2!=n3);    //14) True ou False? False, como tem o mm valor é falso que sao diferentes
console.log(n1++);      //15) 3, pois primeiro mostra o n1 e so depois incrementa
console.log('n1='+n1);  //16) n1=4
n1=4; 
console.log(++n1);      //17) 5, primeiro incrementa e depois mostra o n1
console.log('n1='+n1);  //18) n1=5

//B)
console.log("B)");
let variavel;
variavel = '3' + 2; 
console.log(variavel); 
variavel = '3' + true; 
console.log(variavel); 
variavel = '3' + undefined; 
console.log(variavel); variavel = '3' + null; 
console.log(variavel);

//C)
console.log("C)");
const disciplina; //--> quando se declara uma constante tem que se atribuir o valor logo por isso e que da erro
disciplina="Linguagens Script"; 
console.log(disciplina);

//D) --> concatenaçao de constantes na mesma
//E) --> tambem concatenaçao de strings
//----------------------------------------------------------------------------------------------------------------------------------------------------------------

*/
//2.
//A)

//const num1=5;
//const num2=10;
//const num3=7;

const num1=prompt("Especifique o numero 1: "); 
const num2=prompt("Especifique o numero 2: "); 
const num3=prompt("Especifique o numero 3: ");

let n1 = parseInt(num1);
let n2 = parseInt(num2);
let n3 = parseInt(num3);

let maior;

if(n1 > n2){
    maior = n1;

    if(n3 > maior){
        maior = n3;
        console.log("Maior = " + maior);
    }else{
        console.log("Maior = " + n1);
    }

}else {
    maior = n2;

    if(n3 > maior){
        maior = n3;
        console.log("Maior = " + maior);
    }else{
        console.log("Maior = " + n2);
    }
}

//OU

//num1 > num2 ? maior = num1 : maior = num2; // operador ternario

/*function fazQualquerCoisa() {
    let str = 'Script';
    //isto n funciona pq com o let a variavel esta restrita ao seu scope que neste caso e a funcao
    //se usasse var ao inves de let a variavel ia ter scope global e ia funcionar
 }
 fazQualquerCoisa();
 console.log(str);*/

//k. Qual o resultado do seguinte trecho de código? E se mudar o var para let ? Alguma diferença?
function fazQualquerCoisa() { 
    console.log(str);
}
fazQualquerCoisa();
var str = 'Linguagens'; //--> hoisting, faz com que a sua declaracao esteja antes da funcao mas o valor dado n e transferido por isso e que da undifined pq é como se tivesse declarado antes mas o valor so e dado depois