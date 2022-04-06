Práctica 2: Construcción de un analizador sintactico para adac

Jorge Martinez Gil, 801369
Hugo Lazaro Zapata, 801758

Politicas de recuperacion de errores sintacticos:
- A nivel de instruccion simple: Recuperacion en modo panico, saltando tokens hasta llegar a uno de sincronizacion o el punto y coma.
- A nivel de instruccion condicional e iteracion: Recuperacion en modo panico, saltando tokens hasta llegar a end.