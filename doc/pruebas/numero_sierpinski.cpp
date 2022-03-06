//---------------------------------------------------------------------
// Traducción de adac a C++ generada automáticamente
// Procesadores de Lenguajes. Universidad de Zaragoza
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;


int numero,n,p;

int pedir_entero() {
int n;
n = 0;
while ((n < 0) || (n % 2 == 0)){
cout << "Dame un numero k natural IMPAR: ";
cin >> n;
}
return n;
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
bool es_primo(int k) {
int i;
bool primo;
i = 2;
primo = true;
while ((i <= (k / 2)) && (primo)){
primo = (k % i) != 0;
i = i + 1;
}
return primo;
}


int main() {
numero = pedir_entero();
n = 0;
p = numero * exp(2,n) + 1;
while ((! es_primo(p)) && (n < 16)){
n = n + 1;
p = numero * exp(2,n) + 1;
}
if (! es_primo(p)) {
cout  << "El numero: " << numero << " es un posible numero de Sierpinski." << endl;
}
else {
cout  << "El numero: " << p << " con la forma " << numero << "*2^n + 1 es un numero primo, el " << numero << " no es un numero de Sierpinski." << endl;
}

return 0;
}

