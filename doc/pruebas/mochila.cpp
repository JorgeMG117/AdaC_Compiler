//---------------------------------------------------------------------
// Traducción de adac a C++ generada automáticamente
// Procesadores de Lenguajes. Universidad de Zaragoza
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;


int peso[101];
int benef[101];
int cap;
int sol[101];
int resto;
int i;
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
cout  << "Mostrando vector solucion: " << endl;
aux = 0;
while ((aux < N) && (A[aux] != 0)){
cout  << A[aux] << "," << endl;
aux = aux + 1;
}
}


int main() {
cout  << "Introduzca el numero de elementos del vector: " << endl;
cin >> n;
cout  << "Introduzca la capacidad de la mochila: " << endl;
cin >> cap;
cout  << "Inicializando el vector de pesos" << endl;
inicilizar(peso,n);
cout  << "Inicializando el vector de beneficios" << endl;
inicilizar(benef,n);
while (i < n){
sol[i] = 0;
i = i + 1;
}
resto = cap;
i = 0;
while ((i <= n) && (peso[i] <= resto)){
sol[i] = 1;
resto = resto - peso[i];
i = i + 1;
}
if (i <= n) {
sol[i] = resto / peso[i];
}
escribir_vector(sol,n);

return 0;
}

