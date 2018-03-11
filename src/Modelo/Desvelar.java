package Modelo;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Control.Utiles;
import Modelo.Casilla;
import Modelo.Coordenada;
import Modelo.Tablero;

public class Desvelar {

	private Tablero tablero;

	public Desvelar(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	/**
	 * Desvelamos y comprobamos que tiene la casilla, si es mina o si esta velada
	 * 
	 * @param boton
	 */
	public void desvelarCasillas(JButton boton) {
		Coordenada coordenada = new Utiles().obtenerCoordenada(boton);
		Casilla casilla = tablero.getCasilla(coordenada);
		if (casilla.isMina()) {
			desvelarMina(boton);
			tablero.setPerdedor(true);
		} else if (casilla.isVelada() && !casilla.isMarcada()) {
			if (casilla.getMinasAlrededor() == 0) {
				desvelarContiguas(casilla, coordenada.getPosX(), coordenada.getPosY());
			} else {
				introducirNumeroMina(boton, casilla);
			}
			casilla.setVelada(false);
			cambiarVistaCasilla(boton, casilla);
		}
	}

	/**
	 * al ser mina la casilla la desvelamos y hacemos algunos cambios
	 * 
	 * @param boton
	 */
	private void desvelarMina(JButton boton) {
		boton.setBackground(Color.RED);
		boton.setBorder(new LineBorder(new Color(0, 0, 0)));
		boton.setForeground(Color.BLACK);
		boton.setEnabled(false);
		boton.setText("Mina");
	}

	/**
	 * Cada vez que desvelamos una casilla le hacemos unos cambios para ver cuales
	 * estan desveladas o no
	 * 
	 * @param boton
	 * @param casilla
	 */
	public void cambiarVistaCasilla(JButton boton, Casilla casilla) {
		if (!casilla.isVelada()) {
			boton.setEnabled(false);
			boton.setBackground(new Color(10, 10, 40));
			boton.setForeground(Color.BLACK);
			boton.setBorder(new LineBorder(new Color(50, 50, 125)));
		}
	}

	/**
	 * introducimos el numero de la casilla seleccionada dependiendo del numero de
	 * minas a su alrededor
	 * 
	 * @param boton
	 * @param casilla
	 */
	private void introducirNumeroMina(JButton boton, Casilla casilla) {
		int numeroMinas = casilla.getMinasAlrededor();
		boton.setText(String.valueOf(numeroMinas));
	}

	/**
	 * Usamos el metodo desvelarContiguas para ver si alrededor de la casilla
	 * seleccionada aparece un 0, si es asi hay que repetir el metodo recursivamente
	 * hasta que no haya casillas con 0
	 * 
	 * @param casilla
	 * @param posX
	 * @param posY
	 */
	private void desvelarContiguas(Casilla casilla, int posX, int posY) {
		int[][] posicion = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		for (int i = 0; i < Utiles.OCHO; i++) {
			int x = posX + posicion[i][0];
			int y = posY + posicion[i][1];
			if (validarCoordenada(x, y, tablero)) {
				Coordenada coordenada = new Coordenada(x, y);
				Casilla aux = tablero.getCasilla(coordenada);
				if (aux.isVelada() && !aux.isMarcada()) {
					aux.setVelada(false);
					if (aux.getMinasAlrededor() == 0) {
						desvelarContiguas(aux, x, y);
					}
				}
			}
		}
	}

	/**
	 * comprobamos si todos los componentes estan desvelados menos las minas
	 * 
	 * @param tablero
	 * @param botonera
	 * @return
	 */
	public static boolean comprobarGanador(Tablero tablero, JButton[][] botonera) {
		boolean ganador = true;
		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera.length; j++) {
				if (tablero.getCasilla(new Utiles().obtenerCoordenada(botonera[i][j])).isVelada()) {
					if (!tablero.getCasilla(new Utiles().obtenerCoordenada(botonera[i][j])).isMina()) {
						ganador = false;
					}
				}
			}
		}
		return ganador;
	}

	/**
	 * Miramos que la coordenada esta dentro de los limites del tablero
	 * 
	 * @param columna
	 * @param fila
	 * @param tablero
	 * @return
	 */
	private boolean validarCoordenada(int fila, int columna, Tablero tablero) {
		return columna >= 0 && columna < tablero.length() && fila >= 0 && fila < tablero.length();
	}

	/**
	 * mostramos todas las minas en el tablero
	 * 
	 * @param tablero
	 * @param botonera
	 */
	public void explotarMinas(Tablero tablero, JButton[][] botonera) {
		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera.length; j++) {
				if (tablero.getCasilla(new Utiles().obtenerCoordenada(botonera[i][j])).isMina()) {
					desvelarMina(botonera[i][j]);
				}
			}
		}
	}
}
