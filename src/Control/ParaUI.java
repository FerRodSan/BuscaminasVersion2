package Control;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Vista.buscaminasUi;
import Modelo.Coordenada;
import Modelo.Abanderar;
import Modelo.Casilla;
import Modelo.Desvelar;
import Modelo.Tablero;

public class ParaUI extends buscaminasUi {
	int numeroMinas = getDificultad(dificultad);
	int tamano = 10;
	Tablero tablero = new Tablero(tamano, numeroMinas);
	Casilla casilla = new Casilla();
	Desvelar desvelar = new Desvelar(tablero);
	Abanderar abanderar = new Abanderar();

	public ParaUI() {
		crearBotones(tamano);
		Component[] componentes = panelBotones.getComponents();
		for (Component component : componentes) {
			component.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					/**
					 * si se pusa el boton izquierdo del raton para desvelar casilla
					 */
					if (SwingUtilities.isLeftMouseButton(e)) {
						desvelar.desvelarCasillas((JButton) e.getSource());
					}
					/**
					 * si se pulsa el boton derecho del raton para marcar bandera
					 */
					if (SwingUtilities.isRightMouseButton(e)) {
						abanderar.colocarBandera((JButton) e.getSource(), tablero);
					}
					/**
					 * Se comprueba siempre despues de pulsar un boton si se ha pisado una mina y se
					 * desvelan o si has ganado
					 * 
					 */
					if (tablero.isPerdedor()) {
						lblTexto.setForeground(Color.RED);
						lblTexto.setText("Pisaste una mina, Ooooohh!!!");
						lblTexto.setBackground(Color.BLACK);
						desvelar.explotarMinas(tablero, botonera);
						bloquearPanel(botonera);
					}
					if (Desvelar.comprobarGanador(tablero, botonera)) {
						tablero.setGanador(true);
						lblTexto.setText("Ganasteeeeee");
						lblTexto.setForeground(Color.GREEN);
						lblTexto.setBackground(Color.BLACK);
						bloquearPanel(botonera);
					}
					actualizarBotonera(tamano);
				}
			});
		}
	}

	/**
	 * Segun la opcion que seleccionemos en el panel nos pone un numero de minas
	 * mayor o menor en el tablero
	 * 
	 * @param dificultad
	 * @return
	 */
	private int getDificultad(int dificultad) {
		int numeroMinas = 0;
		switch (dificultad) {
		case 1:
			numeroMinas = 10;
			break;
		case 2:
			numeroMinas = 20;
			break;
		case 3:
			numeroMinas = 30;
			break;

		default:
			break;
		}
		return numeroMinas;
	}

	/**
	 * bloqueamos el panel al ganar o perder la partida
	 * 
	 * @param botonera
	 */
	protected void bloquearPanel(JButton[][] botonera) {
		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera.length; j++) {
				this.botonera[i][j].setEnabled(false);
				this.panelBotones.setEnabled(false);
			}
		}
	}

	/**
	 * Siempre despues de cada click en un boton vamos a actualizar la botonera para
	 * ir viendo como va quedando el juego
	 * 
	 * @param tamano
	 */
	private void actualizarBotonera(int tamano) {
		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera[i].length; j++) {
				Coordenada coordenada = new Coordenada(i, j);
				Casilla casilla = tablero.getCasilla(coordenada);
				if (!casilla.isVelada() && casilla.getMinasAlrededor() > 0) {
					botonera[i][j].setText(String.valueOf(casilla.getMinasAlrededor()));
				}
				desvelar.cambiarVistaCasilla(botonera[i][j], casilla);
			}
		}
	}
}