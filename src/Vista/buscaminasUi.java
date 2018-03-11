package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Control.Principal;

import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class buscaminasUi extends JFrame {

	protected JPanel contentPane;
	protected JPanel panelBotones;
	protected JButton[][] botonera;
	protected JLabel lblTexto;
	protected int dificultad=1;
	protected JMenuItem mntmFacil;
	protected JMenuItem mntmMedia;
	protected JMenuItem mntmDificil;

	/**
	 * Create the frame.
	 */
	public buscaminasUi() {
		// ajusto un tamaño minimo en el panel princicpal
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Archivo");
		menuBar.add(mnMenu);

		JMenuItem mntmReiniciar = new JMenuItem("Reiniciar");
		mntmReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTexto.setText("Has reiniciado");
				Principal.main(null);
			}
		});

		JMenu mnNuevaPartida = new JMenu("Nueva Partida");
		mnMenu.add(mnNuevaPartida);

		mntmFacil = new JMenuItem("Facil");
		mntmFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dificultad=1;
				Principal.main(null);
			}
		});
		mntmFacil.setHorizontalAlignment(SwingConstants.CENTER);
		mnNuevaPartida.add(mntmFacil);

		mntmMedia = new JMenuItem("Media");
		mntmMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dificultad=2;
				Principal.main(null);
			}
		});
		mnNuevaPartida.add(mntmMedia);

		mntmDificil = new JMenuItem("Dificil");
		mntmDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dificultad=3;
				Principal.main(null);
			}
		});
		mnNuevaPartida.add(mntmDificil);
		mnMenu.add(mntmReiniciar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.CENTER);
		panelBotones.setLayout(new GridLayout(15, 15, 0, 0));

		JLabel lblNewLabel = new JLabel("BUSCAMINAS");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		lblTexto = new JLabel("Aqui pongo si ganas o pierdes");
		lblTexto.setBackground(Color.BLACK);
		lblTexto.setFont(new Font("Vivaldi", Font.BOLD, 30));
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTexto, BorderLayout.SOUTH);
	}

	/**
	 * Se crean los botones dentro del panel de botones
	 * 
	 * @param tamano
	 */
	protected void crearBotones(int tamano) {
		panelBotones.setLayout(new GridLayout(tamano, tamano));
		botonera = new JButton[tamano][tamano];
		for (int fila = 0; fila < botonera.length; fila++) {
			for (int columna = 0; columna < botonera[fila].length; columna++) {
				botonera[fila][columna] = new JButton();
				botonera[fila][columna].setName(String.valueOf(fila) + String.valueOf(columna));
				botonera[fila][columna].setBackground(new Color(10, 0, 90));
				botonera[fila][columna].setBorder(new LineBorder(new Color(0, 0, 0)));
				panelBotones.add(botonera[fila][columna]);
			}
		}
	}
}
