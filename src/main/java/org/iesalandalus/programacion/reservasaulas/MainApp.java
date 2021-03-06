package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class MainApp {

	public static void main(String[] args) {
		IModelo modelo = new Modelo(procesarArgumentosFuenteDatos(args));
		IVista vista = procesarArgumentosVista(args);
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}
	
	private static IVista procesarArgumentosVista(String[] args) {
		IVista vista = FactoriaVista.IUGPESTANAS.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vpestanas")) {
				vista = FactoriaVista.IUGPESTANAS.crear();
			} else if (argumento.equalsIgnoreCase("-vventanas")) {
				vista = FactoriaVista.IUGVENTANAS.crear();
			} else if (argumento.equalsIgnoreCase("-vtexto")) {
				vista = FactoriaVista.TEXTO.crear();
			}
		}
		return vista;
	}
	
	private static IFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
		IFuenteDatos fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdficheros")) {
				fuenteDatos = FactoriaFuenteDatos.FICHEROS.crear();
			} else if (argumento.equalsIgnoreCase("-fdmongodb")) {
				fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
			} else if (argumento.equalsIgnoreCase("-fdmysql")) {
				fuenteDatos = FactoriaFuenteDatos.MYSQL.crear();
			} else if (argumento.equalsIgnoreCase("-fdmysqlxdevapi")) {
				fuenteDatos = FactoriaFuenteDatos.MYSQL_XDEVAPI.crear();
			}
		}
		return fuenteDatos;
	}

}
