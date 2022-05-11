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
	public void inst_asignacion_1(Attributes at, Attributes at1){
		if(at.type != at1.type){
			//MIRAR ESTO

			System.out.println("Error, el tipo no coincide con el de la variable a asignar.");
			//ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}
	}

	//Inst_return
	public void inst_return_1(Attributes at, Attributes at1, Token t1){
		if(at.typeProcFunc == Symbol.Types.FUNCTION){
			if(at.type != at1.type){
				ErrorSemantico.deteccion("El tipo devuelto por la funcion no es correcto", t1);
			}
		}
		else{
			ErrorSemantico.deteccion("Instruccion return no en funcion", t1);
		}
		
	}

	//Inst_leer
	public void inst_leer_1(Token t, Attributes at1){
		if(at1.type != Symbol.Types.INT && at1.type != Symbol.Types.CHAR){
			ErrorSemantico.deteccion("Los argumentos deben ser de tipo INT o CHAR", t);
		}
	}

	//Expresion
	public void expresion_1(Attributes at, Attributes at1){
		if(at.type != at1.type){
			System.out.println("Error, expresion_1");
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
		}
		at.type = Symbol.Types.BOOL;
		at.canBeRef = false;
	}

	//Expresion_simple
	public void expresion_simple_1(Attributes at){
		at.type = Symbol.Types.INT;
		//como asar el canberef
	}
	
	public void expresion_simple_2(Attributes at, Attributes at1){
		if(at.type == Symbol.Types.INT && at1.type != Symbol.Types.INT){
			//Error Semantico
			System.out.println("Error expresion_simple_2");
		}
		else{
			if(at.type == Symbol.Types.UNDEFINED){
				at.canBeRef = at1.canBeRef;
			}
			else{
				at.canBeRef = false;
			}
			at.type = at1.type;
		}
	}
	
	public void expresion_simple_3(Attributes at1, Attributes at2, Attributes at3){
		if(at1.type != at2.type || at1.type != at3.type){
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
			System.out.println("Error expresion_simple_3");
		}
	}

	//lista_una_o_mas_exps//mismo numero de param//asignable y variable de tipo vector en referencia
	public void lista_una_o_mas_exps_1(Token t, int i, Attributes at1){
		if(t.image != "put" && t.image != "put_line"){
			Symbol s = null;
			SymbolFunction f = null;
			SymbolProcedure p = null;

			try{
				s = st.getSymbol(t.image);
			}
			catch(SymbolNotFoundException e){
				ErrorSemantico.deteccion(e, t);
			}
			if(s.type == Symbol.Types.FUNCTION){
				f = (SymbolFunction) s;
				s = f.parList.get(i);
			}
			else if(s.type == Symbol.Types.PROCEDURE){
				p = (SymbolProcedure) s;
				s = p.parList.get(i);
			}
			
			if(s.type != at1.type){
				System.out.println("Error lista_una_o_mas_exps_1");
			}
			if(s.parClass == Symbol.ParameterClass.REF && !at1.canBeRef){
				System.out.println("Argumento no puede ser una referencia");
			}
			else{
				at1.parClass = s.parClass;
			}
			i++;
		}
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

	public void asignable_2(Token t, Attributes at1){

		if(at1.type != Symbol.Types.INT){
			//Mensaje error ErrorSemantico
			ErrorSemantico.deteccion("La expresion no es entera", t);
		}
	}

	public void asignable_3(Token t, Attributes at){
		Symbol s =  null;
		try{
			s = st.getSymbol (t.image);
			if(s.type != Symbol.Types.ARRAY){//Se puede hace v[], v+1
				at.type = s.type;
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

	//Termino
	public void termino_2(Attributes at, Attributes at2, Attributes at3){
		if(at.type != at2.type || at.type != at3.type){
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
			System.out.println("Error termino");
		} 
		at.canBeRef = false;
	}

	//Factor
	public void factor_1(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.BOOL){
			at.type = Symbol.Types.BOOL;
			at.canBeRef = false;
		}
		else{
			//Mensaje error ErrorSemantico
			//ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}	
	}

	public void factor_2(Attributes at, Attributes at1){
		at.type = at1.type;
		at.canBeRef = false;
	}

	public void factor_3(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.INT){
			at.type = Symbol.Types.CHAR;
			at.canBeRef = false;
		}
		else{
			//Mensaje error ErrorSemantico
			//ErrorSemantico.deteccion("Parametro de la funcion int2char no es int", t);
		}
	}

	public void factor_4(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.CHAR){
			at.type = Symbol.Types.INT;
			at.canBeRef = false;
		}
		else{
			//Mensaje error ErrorSemantico
			//ErrorSemantico.deteccion("Parametro de la funcion charToInt no es char", t);
		}
	}

	public void factor_5(Token t, Attributes at){

		Symbol s =  null;
		try{
			s = st.getSymbol(t.image);
			//comprobar que es tipo array
			if(s.type == Symbol.Types.FUNCTION){
				at.type = ((SymbolFunction)s).returnType;
				at.canBeRef = false;
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
				at.canBeRef = false;
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
			at.canBeRef = true;
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
	}

	public void factor_8(Token t, Attributes at){
		try{
			at.type = Symbol.Types.INT;
            at.valInt = Integer.parseInt(t.image); //Pasar a entero
			at.canBeRef = false;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
	}

	public void factor_9(Token t, Attributes at){
		at.type = Symbol.Types.CHAR;
		at.valChar = t.image.charAt(0);
		at.canBeRef = false;
	}

	public void factor_10(Token t, Attributes at){
		at.type = Symbol.Types.STRING;
		at.valString = t.image;
		at.canBeRef = false;
	}
	
	public void factor_11(Attributes at){
		at.type = Symbol.Types.BOOL;
		at.valBool = true;
		at.canBeRef = false;
	}
	
	public void factor_12(Attributes at){
		at.type = Symbol.Types.BOOL;
		at.valBool = false;
		at.canBeRef = false;
	}
}

//no puedo escribir vector
//guardas de condiciones