//*****************************************************************
// Tratamiento de errores sintácticos
//
// Fichero:    SemanticFunctions.java
// Fecha:      03/03/2022
// Versión:    v1.0
// Asignatura: Procesadores de Lenguajes, curso 2021-2022
//*****************************************************************

package lib.tools;

import java.util.*;
import traductor.Token;
import lib.attributes.*;
import lib.symbolTable.*;
import lib.symbolTable.exceptions.*;
import lib.errores.*;

public class SemanticFunctions {
	private ErrorSemantico errSem; //clase común de errores semánticos
	private SymbolTable st;

	public SemanticFunctions(SymbolTable _st) {
		errSem = new ErrorSemantico();
		st = _st;
	}

	//COMPLETAR
	public void asignable_1(Token t, Attributes at){
		Symbol s =  null;
		//comprobar si eexiste
		try{
			s = st.getSymbol(t.image);
			//comprobar que es tipo array
			if(s.type == Symbol.Types.ARRAY){
				at.type = ((SymbolArray)s).baseType;
			}
			else{
				//Mensaje error ErrorSemantico
				ErrorSemantico.deteccion("La variable no es un array", t);
			}
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
		
	}


	//Factor
	public void factor_8(Token t, Attributes at){
		//ID tiene que existir
		j = 0
		j = i
	}

	public void factor_8(Token t, Attributes at){
		at.type = Symbol.Types.INT;
		at.valInt = t.image; //Pasar a entero
	}

	public void factor_9(Token t, Attributes at){
		at.type = Symbol.Types.CHAR;
		at.valChar = t.image;
	}

	public void factor_10(Token t, Attributes at){
		at.type = Symbol.Types.STRING;
		at.valString = t.image;
	}
	
	public void factor_11(Attributes at){
		at.type = Symbol.Types.BOOLEAN
		at.valBool = true;
	}
	
	public void factor_12(Attributes at){
		at.type = Symbol.Types.BOOLEAN
		at.valBool = false;
	}
}
