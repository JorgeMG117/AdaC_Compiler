Práctica 4: Generacion de codigo intermedio para adac

Jorge Martinez Gil, 801369
Hugo Lazaro Zapata, 801758

Politicas de recuperacion de errores sintacticos:
- A nivel de instruccion simple: Recuperacion en modo panico, saltando tokens hasta llegar a uno de sincronizacion o el punto y coma.
- A nivel de instruccion condicional e iteracion: Recuperacion en modo panico, saltando tokens hasta llegar a end.

Caracteristicas del lenguaje aceptado:
- El compilador realiza el analisis semantico de nivel 4 del lenguaje
- Permite el uso de parametros escalares y vectores, tanto por valor como por referencia en procedimientos y funciones.
- Definicion de funciones anidadas dentro de funciones

Organizacion del proyecto
- En la clase attibutes se han introducido un array de simbolos para guardar los argumentos de la función para añadirlos a la tabla de simbolos despues de crear un bloque, 
un simbolo relaccionado con el atributo que contiene información sobre tipo, nombre, clase de parametro...
un booleano "canBeRef" que nos indique si el atributo puede ser pasado como referencia.
-En SemanticFunctions hemos introducido todas las funciones del analisis semántico divididas por funcion del traductor en las que se encuentran. 
El nombre de las funciones, para ayudar a diferenciarlas, es el nombre de la función en la que se encuentran y un numero. 
- La generacion del codigo se ha implementado en el fichero adac_4.jj
- Los ficheros .pcode se generan en el mismo directorio donde se encuentra el fuente en adac.