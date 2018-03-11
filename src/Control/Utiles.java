package Control;

import java.awt.Component;

import javax.swing.JButton;

import Modelo.Coordenada;

public class Utiles {
	
	public static final int OCHO = 8;
	
	public static Coordenada damePosicionAlrededor(int lugar) {
		int[][] posicion = { { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 }, { 0, +1 }, { +1, -1 }, { +1, 0 },
				{ +1, +1 } };
		return new Coordenada(posicion[lugar][0], posicion[lugar][1]);
	}

	/**
	 * obtenemos la coordenada de cada boton introduciendola en un objeto coordenada.
	 * Añadimos las posiciones a la coordenada
	 * @param boton
	 * @return
	 */
	public Coordenada obtenerCoordenada(JButton boton) {
		String coordenadaTemp = boton.getName();
		Coordenada coordenada = new Coordenada(0, 0);
		coordenada.setPosX(Integer.valueOf(coordenadaTemp.substring(0, 1)));
		coordenada.setPosY(Integer.valueOf(coordenadaTemp.substring(1, 2)));
		return coordenada;
	}


}
