//---------------------------------------------------------------------
// Traducci�n de adac a C++ generada autom�ticamente
// Procesadores de Lenguajes. Universidad de Zaragoza
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;


int A[101],B[101];
int N,k;

void inicilizarN(int & N) {
int d;
cin >> d;
while ((d < 0) || (d > 100)){
cout << "Escribe un numero (>=0) y (<= 100): ";
cin >> d;
if ((d < 0) || (d > 100)) {
cout  << "El numero debe ser >= 0 y <= 100." << endl;
}
}
N = d;
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
void inicilizarAk(int v[101],int N,int & k) {
int i,d,max;
max = 0;
i = 0;
while (i < N){
cin >> d;
while ((d < 0) || (d > 100)){
cout << "Escribe un numero (>=0) y (<= 100): ";
cin >> d;
if ((d < 0) || (d > 100)) {
cout  << "El numero debe ser >= 0 y <= 100." << endl;
}
}
if (d > max) {
max = d;
}
v[i] = d;
i = i + 1;
}
k = max;
}
void sort(int A[101],int B[101],int n,int k) {
int C[101];
int secuencia;
n = exp(2,n);
secuencia = 0;
while ((secuencia < 3) || (n != 0)){
if ((n % 10) == 6) {
secuencia = secuencia + 1;
}
n = n / 10;
}
}


int main() {
cout  << "Longitud de vector a ordenar: " << endl;
inicilizarN(N);
cout  << "Componentes del vector: " << endl;
inicilizarAk(A,N,k);
sort(A,B,N,k);

return 0;
}

