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

	//programa
	public void programa_1(Token t, Attributes at1){
		Symbol s = null;
		try{
			s = new SymbolProcedure(t.image, null) ;
			st.insertSymbol(s);
			at1.simbolo = s;
		}
		catch (AlreadyDefinedSymbolException e) {
			ErrorSemantico.deteccion(e, t);
		}
	}

	//inst_invoc_proc
	public void inst_invoc_proc_1(Token t, Attributes at1){
		Symbol s = null;
		SymbolProcedure p = null;
		//System.out.println("Hemos entrado");
		try{
			s = st.getSymbol(t.image);
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
		//System.out.println("Hemos salido");
		if(s.type == Symbol.Types.PROCEDURE){
			p = (SymbolProcedure) s;
			at1.simbolo = p;
		}
		else{
			System.out.println("El identificador no es procedimiento");
		}
	}

	public void inst_invoc_proc_2(Attributes at1){
		//(SymbolProcedure)
		//((SymbolProcedure) s).parList
		
		
		try{		
			//System.out.println("Hemos entrado inst_invoc_proc_2");
			if(((SymbolProcedure) at1.simbolo).parList.size() > at1.indice){
				System.out.println("El numero de parametros es menor del esperado");
			}
			//System.out.println("Hemos salido inst_invoc_proc_2");
		}
		catch(Exception e){//O se ha utilizado una funcion como procedimiento
			System.out.println("No se puede invocar al procedimiento principal o se ha utilizado una funcion como procedimiento");
		}

	}

	//inst_seleccion
	public void inst_seleccion_1(Attributes at1){
		if(at1.type != Symbol.Types.BOOL){

			System.out.println("Error, el tipo no la guarda no es booleano");
			//ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}
	}

	//inst_iteracion
	public void inst_iteracion_1(Attributes at1){
		if(at1.type != Symbol.Types.BOOL){

			System.out.println("Error, el tipo no la guarda no es booleano");
			//ErrorSemantico.deteccion("Factor no es de tipo bool", t);
		}
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
		if(at.simbolo.type == Symbol.Types.FUNCTION){
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
		for(int i = 0; i < at1.listaTipo.size() - 1; i++){
			if(at1.listaTipo.get(i) != Symbol.Types.INT && at1.listaTipo.get(i) != Symbol.Types.CHAR){
				ErrorSemantico.deteccion("Los argumentos deben ser de tipo INT o CHAR", t);
			}
		}
	}

	//Inst_escribir, symbol es una clase abstracto por eso ponemos SymbolInt, put y putline no tienen simbolo en la tabla
	public void inst_escribir_1(Token t, Attributes at1){
		SymbolInt s = null;
		s = new SymbolInt(t.image);
		at1.simbolo = s;
	}

	//Inst_escribir_linea
	public void inst_escribir_linea_1(Token t, Attributes at1){
		SymbolInt s = null;
		s = new SymbolInt(t.image);
		at1.simbolo = s;
	}
	

	//Expresion
	public void expresion_1(Attributes at, Attributes at1){
		if(at.type != at1.type){
			System.out.println("Error, expresion_1 no coinciden los tipos");
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
		}
		at.type = Symbol.Types.BOOL;
		at.canBeRef = false;
	}

	//Expresion_simple
	public void expresion_simple_1(Attributes at1){
		at1.type = Symbol.Types.INT;
		//como asar el canberef
	}
	
	public void expresion_simple_2(Attributes at, Attributes at1){
		if(at1.type == Symbol.Types.INT && at.type != Symbol.Types.INT){
			//Error Semantico
		
			System.out.println("ERROR: Tipo de at: " + at.type);
			System.out.println("ERROR: Tipo de at1: " + at1.type);
			//System.out.println("Por probar: " + at.simbolo.name);
			System.out.println("ERROR: Error expresion_simple_2, el tipo no es entero");
			//ErrorSemantico.deteccion("Error expresion_simple_2, el tipo no es entero", matchedToken);
		}
		else{
			if(at1.type != Symbol.Types.UNDEFINED){
				at.canBeRef = false;
				
			}
		}
	}
	
	public void expresion_simple_3(Attributes at, Attributes at2, Attributes at3){
		if(at.type != at2.type || at.type != at3.type){
			//ErrorSemantico.deteccion("Ambas partes de la expresion no son del mismo tipo", t);
			System.out.println("Error expresion_simple_3");
		}
		at.canBeRef = false;
	}

	//lista_una_o_mas_exps//mismo numero de param//asignable y variable de tipo vector en referencia
	public void lista_una_o_mas_exps_1(Attributes at, Attributes at1){

		if(at.simbolo == null){
			return;
		}

		if(at.simbolo.name != "put" && at.simbolo.name != "put_line"){

			Symbol s = null;
			
			// SymbolFunction f = null;
			// SymbolProcedure p = null;

			// try{
			// 	s = st.getSymbol(t.image);
			// }
			// catch(SymbolNotFoundException e){
			// 	ErrorSemantico.deteccion(e, t);
			// }
			// if(s.type == Symbol.Types.FUNCTION){
			// 	f = (SymbolFunction) s;
			// 	s = f.parList.get(i);
			// }
			// else if(s.type == Symbol.Types.PROCEDURE){
			// 	p = (SymbolProcedure) s;
			// 	s = p.parList.get(i);
			// }
			
			//System.out.println(s);
			//((SymbolProcedure) at1.simbolo).parList.size()

			if(at.simbolo.type == Symbol.Types.FUNCTION){
				//System.out.println("ES un funcion");
				if(((SymbolFunction) at.simbolo).parList == null) {
					//System.out.println("No se puede invocar al procedimiento principal");
					return;
				}
				if(at.indice >= ((SymbolFunction) at.simbolo).parList.size()){
					System.out.println("Se ha llamado con mas parametros que los que tiene");
				}
				else{
					s = ((SymbolFunction) at.simbolo).parList.get(at.indice);
				}
			}
			else if(at.simbolo.type == Symbol.Types.PROCEDURE){
				//System.out.println("ES un procedure");
				if(((SymbolProcedure) at.simbolo).parList == null) {
					//System.out.println("No se puede invocar al procedimiento principal");
					return;
				}
				if(at.indice >= ((SymbolProcedure) at.simbolo).parList.size()){
					System.out.println("Se ha llamado con mas parametros que los que tiene");
				}
				else{
					s = ((SymbolProcedure) at.simbolo).parList.get(at.indice);
				}
			}
			else{
				System.out.println("No es ni procedimiento, ni función, esta mal");
			}
			

			//
			if(at1.type != Symbol.Types.ARRAY && at1.type != Symbol.Types.INT && at1.type != Symbol.Types.CHAR && at1.type != Symbol.Types.BOOL){

				System.out.println("Tipo de at1: " + at1.type);
				System.out.println("Argumento debe ser int, char o boolean");
			}

			if(s != null){	
				if(s.type != at1.type){
					System.out.println("El tipo del argumento no coincide con el del parametro");
				}

				//
				if(s.parClass == Symbol.ParameterClass.REF && !at1.canBeRef){
					System.out.println("Argumento no puede ser una referencia");
				}
				else{
					at1.parClass = s.parClass;
				} 
			}
			

			at.indice++;
			//System.out.println("Esto es la i: " + at.indice);

		
		}
		else{
			if(at1.type == Symbol.Types.ARRAY){
				System.out.println("Argumento put o put_line no puede ser un array");
			}
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
				at.simbolo = s;
				at.arraySize = ((SymbolArray)s).maxInd;
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
				at.simbolo = s;
			}
			else{
				//Mensaje error ErrorSemantico
				ErrorSemantico.deteccion("La variable es un array", t);
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
		//System.out.println("he pasado por el termino"); 
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

	public void factor_5(Attributes at1, Attributes at){
		at.type = ((SymbolFunction) at1.simbolo).returnType;
		at.canBeRef = false;
		
		if(((SymbolFunction) at1.simbolo).parList.size() > at1.indice){
			System.out.println("El numero de parametros es menor del esperado");
		}
	}

	public void factor_6(Token t, Attributes at, Attributes at1){
		Symbol s =  null;
		//System.out.println("he pasado por el factor");
		//Comprobar 
			//si existe
			//si es vector el id
			//si el tipo de at1 es INT
		try{
			s = st.getSymbol(t.image);
			//comprobar que es tipo array
			if(s.type == Symbol.Types.ARRAY){
				at.type = ((SymbolArray)s).baseType;
				at.canBeRef = true;
				at.simbolo = s;
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
			at.simbolo = s;
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

	public void factor_13(Token t, Attributes at1){
		Symbol s = null;
		SymbolFunction f = null;
		try{
			s = st.getSymbol(t.image);
		}
		catch(SymbolNotFoundException e){
			ErrorSemantico.deteccion(e, t);
		}
		if(s.type == Symbol.Types.FUNCTION){
			f = (SymbolFunction) s;
			at1.simbolo = f;
		}
		else{
			System.out.println("El identificador no es funcion");
		}
		
	}
}
