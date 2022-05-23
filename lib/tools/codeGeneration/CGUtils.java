//*****************************************************************
// File:   CGUtils.java
// Author: Procesadores de Lenguajes-University of Zaragoza
// Date:   abril 2022
// Coms:   Librería con el método para crear etiquetas frescas y 
// 			una variable estática para llevar la traza de las 
// 			direcciones de memoria 
//*****************************************************************

package lib.tools.codeGeneration;

import lib.tools.codeGeneration.PCodeInstruction.OpCode;

public class CGUtils {
	
	private static int l=0; 
	
	public static String newLabel () {
		return "L"+(l++); 
	}
	//Cuenta BA
	//Campo requeridos para la generación de código
	public static int memorySpaces[] = new int[100];//Lleva la cuenta de param y var en ese bloque, ojo cuando insertas bloque dos primeras componentes
	//Cada bloque empieza en 3, hacer hueco 
	//cuidado los vectores
}
