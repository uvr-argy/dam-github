package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormularioEliminar extends JDialog {

	private static final long serialVersionUID = 1L;

	private final Color colorRojo = new Color(230, 100, 100);

	private String tipo;
	private String descripcion;
	private JButton botonAccion;

	public FormularioEliminar(String tipo, String descripcion) {
		
		this.tipo = tipo;
		this.descripcion = descripcion;

		configurarVentana();
		crearContenido(false);
	}

	public FormularioEliminar(String tipo, String descripcion, boolean esDevolucion) {
		
		this.tipo = tipo;
		this.descripcion = descripcion;
		
		configurarVentana();
		crearContenido(esDevolucion);
	}

	private void configurarVentana() {

		setTitle("Confirmar eliminación");
		setSize(420, 220);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
	}

	private void crearContenido(boolean esDevolucion) {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));

		// ========================================
		// TÍTULO
		// ========================================
		String textoTitulo;

		if (esDevolucion) {
			textoTitulo = "Confirmar devolución";
		} else {
			textoTitulo = "Confirmar eliminación";
		}

		JLabel etiquetaTitulo = new JLabel(textoTitulo);
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panelPrincipal.add(etiquetaTitulo, BorderLayout.NORTH);

		// ========================================
		// MENSAJE
		// ========================================
		JPanel panelMensaje = new JPanel();
		panelMensaje.setBackground(Color.WHITE);
		panelMensaje.setLayout(new javax.swing.BoxLayout(panelMensaje, javax.swing.BoxLayout.Y_AXIS));

		String textoPregunta;

		if (esDevolucion) {
			textoPregunta = "¿Seguro que quieres devolver este préstamo?";
		} else {
			textoPregunta = "¿Seguro que quieres eliminar este " + tipo.toLowerCase() + "?";
		}

		JLabel etiquetaPregunta = new JLabel(textoPregunta);
		etiquetaPregunta.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel etiquetaElemento = new JLabel(descripcion);
		etiquetaElemento.setFont(new Font("Segoe UI", Font.BOLD, 14));

		panelMensaje.add(etiquetaPregunta);
		panelMensaje.add(javax.swing.Box.createVerticalStrut(8));
		panelMensaje.add(etiquetaElemento);
		
		panelPrincipal.add(panelMensaje, BorderLayout.CENTER);

		// ========================================
		// BOTONES
		// ========================================
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		panelBotones.setBackground(Color.WHITE);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(100, 35));

		String textoBoton;

		if (esDevolucion) {
			textoBoton = "Devolver";
		} else {
			textoBoton = "Eliminar";
		}

		botonAccion = new JButton(textoBoton);
		botonAccion.setBackground(colorRojo);
		botonAccion.setPreferredSize(new Dimension(100, 35));

		panelBotones.add(botonCancelar);
		panelBotones.add(botonAccion);
		
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

		// ========================================
		// EVENTOS
		// ========================================
		botonCancelar.addActionListener(e -> dispose());
		
		botonAccion.addActionListener(e -> {

			if (esDevolucion) {

				// Aquí conectaremos posteriormente
				// con el controlador para devolver
				// el préstamo.

			} else {

				// Aquí conectaremos posteriormente
				// con el controlador para eliminar.

			}

			dispose();
		});

		add(panelPrincipal);
	}
	public JButton getBotonAccion() {
	    return botonAccion;
	}
}