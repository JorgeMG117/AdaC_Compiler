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

	//Inst_asignacion
	public void inst_asignacion_1(Attributes at1, Attributes at2){
		if(at1.type != at1.type){
			//MIRAR ESTO

			System.out.println("Error inst_asignacion_1");
			//ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}
	}

	//Expresion
	public void expresion_1(Attributes at, Attributes at1){
		at.type = at1.type;
	}

	public void expresion_2(Attributes at, Attributes at1, Attributes at2){
		if(at1.type != at2.type){
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
		}
		at.type = Symbol.Types.BOOL;
	}

	//Asignable
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
	public void factor_1(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.BOOL){
			at.type = Symbol.Types.BOOL;
		}
		else{
			//Mensaje error ErrorSemantico
			ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}
	}

	public void factor_2(Attributes at, Attributes at1){
		at.type = at1.type;
	}

	public void factor_3(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.INT){
			at.type = Symbol.Types.CHAR;
		}
		else{
			//Mensaje error ErrorSemantico
			ErrorSemantico.deteccion("Parametro de la funcion int2char no es int", t);
		}
	}

	public void factor_4(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.CHAR){
			at.type = Symbol.Types.INT;
		}
		else{
			//Mensaje error ErrorSemantico
			ErrorSemantico.deteccion("Parametro de la funcion charToInt no es char", t);
		}
	}

	public void factor_5(Token t, Attributes at){

		Symbol s =  null;
		try{
			s = st.getSymbol(t.image);
			//comprobar que es tipo array
			if(s.type == Symbol.Types.FUNCTION){
				at.type = ((SymbolFunction)s).returnType;
			}
			else{
				//Mensaje error ErrorSemantico
				ErrorSemantico.deteccion("El identificador no es una funcion", t);
			}
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
	}

	public void factor_6(Token t, Attributes at, Attributes at1){
		Symbol s =  null;
		//Comprobar 
			//si existe
			//si es vector el id
			//si el tipo de at1 es INT
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

			if(at1.type != Symbol.Types.INT){
				ErrorSemantico.deteccion("Se esta indexando un vector con un valor no entero", t);
			}

		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
	}
	
	public void factor_7(Token t, Attributes at){
		//ID tiene que existir
		Symbol s =  null;
		//comprobar si eexiste
		try{
			s = st.getSymbol(t.image);
			//comprobar que es tipo array
			at.type = s.type;
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
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
