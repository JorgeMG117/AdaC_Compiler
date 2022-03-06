//---------------------------------------------------------------------
// Traducción de adac a C++ generada automáticamente
// Procesadores de Lenguajes. Universidad de Zaragoza
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;


int C[101],F[101];
int i,j;
int A[101];
int indice;
int n;

void inicilizar(int v[101],int N) {
int i,d;
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
v[i] = d;
i = i + 1;
}
}
void escribir_vector(int A[101],int N) {
int aux;
aux = 0;
while ((aux < N) && (A[aux] != 0)){
cout  << A[aux] << "," << endl;
aux = aux + 1;
}
}


int main() {
cout  << "Introduzca el numero de elementos del vector: " << endl;
cin >> n;
cout  << "Introduzca el vector de inicio: " << endl;
inicilizar(C,n);
cout  << "Introzuca el vector de final: " << endl;
inicilizar(F,n);
indice = 1;
A[0] = 1;
j = 1;
i = 2;
while (i < n){
if (C[i] >= F[j]) {
A[indice] = i;
indice = indice + 1;
j = i;
}
i = i + 1;
}
cout  << "La solucion optima para el problema de la seleccion de actividades es: " << endl;
escribir_vector(A,n);

return 0;
}

