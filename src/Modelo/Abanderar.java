package Modelo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Control.Utiles;

public class Abanderar {

	/**
	 * coloco la bandera si la casilla esta velada
	 * 
	 * @param boton
	 * @param tablero
	 */
	public void colocarBandera(JButton boton, Tablero tablero) {
		Coordenada coordenada = new Utiles().obtenerCoordenada(boton);
		Casilla casilla = tablero.getCasilla(coordenada);
		if (casilla.isVelada()) {
			casilla.setMarcada(!casilla.isMarcada());
			if (casilla.isMarcada()) {
				colocarVistaBandera(boton);
			} else {
				quitarBandera(boton);
			}
		}
	}

	/**
	 * quito la bandera colocando en la casilla un texto sin nada y retornando el color inicial
	 * 
	 * @param boton
	 */
	public void quitarBandera(JButton boton) {
		boton.setText("");
		boton.setBackground(new Color(10, 0, 90));
		boton.setBorder(new LineBorder(new Color(0, 0, 0)));
	}

	/**
	 * colocamos una bandera en la vista cambiando color y texto
	 * 
	 * @param boton
	 */
	public void colocarVistaBandera(JButton boton) {
		boton.setText("B");
		boton.setBackground(Color.yellow);
		boton.setBorder(new LineBorder(new Color(0, 0, 0)));
	}
}
