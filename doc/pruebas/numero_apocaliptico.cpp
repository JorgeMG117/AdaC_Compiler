//---------------------------------------------------------------------
// Traducción de adac a C++ generada automáticamente
// Procesadores de Lenguajes. Universidad de Zaragoza
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;


int i;

int dato() {
int d;
d = 0;
while (d <= 0){
cout << "Escribe un numero (30>n>0): ";
cin >> d;
if ((d <= 0) || (d > 30)) {
cout  << "El numero debe ser 30>n>0." << endl;
}
}
return d;
}
int exp(int base,int exponente) {
int sol;
sol = 1;
while ((exponente > 0)){
sol = sol * base;
exponente = exponente - 1;
}
return sol;
}
bool apocaliptico(int n) {
int k,secuencia;
bool esta;
n = exp(2,n);
secuencia = 0;
while ((secuencia < 3) && (n != 0)){
if (n % 10 == 6) {
secuencia = secuencia + 1;
}
n = n / 10;
}
return secuencia == 3;
}


int main() {
cout  << "Este programa pide un numero natural y calcula si es un numero apocaliptico." << endl;
i = dato();
cout  << "" << endl;
if (apocaliptico(i)) {
cout  << i << " es un numero apocaliptico" << endl;
}
else {
cout  << i << " no es un numero apocaliptico" << endl;
}

return 0;
}

