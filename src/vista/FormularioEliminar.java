package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ventana de confirmación. Sirve tanto para eliminar (usuarios, recursos) como
 * para devolver un préstamo.
 * 
 * Uso:
 * 
 * <pre>
 * FormularioEliminar dialogo = FormularioEliminar.paraEliminar(ventana, "usuario", "Ana (ID: U01)");
 * dialogo.setVisible(true); // se queda esperando hasta que se cierra
 * if (dialogo.isConfirmado()) {
 * 	// hacer la acción
 * }
 * </pre>
 */
public class FormularioEliminar extends JDialog {

	private static final long serialVersionUID = 1L;

	private boolean confirmado = false;

	// =========================================================
	// CONSTRUCCIÓN: DOS VARIANTES
	// =========================================================
	public static FormularioEliminar paraEliminar(Window propietario, String tipo, String descripcion) {

		return new FormularioEliminar(propietario, "Confirmar eliminación",
				"¿Seguro que quieres eliminar este " + tipo.toLowerCase() + "?", descripcion, "Eliminar");
	}

	public static FormularioEliminar paraDevolucion(Window propietario, String descripcion) {

		return new FormularioEliminar(propietario, "Confirmar devolución", "¿Seguro que quieres devolver este préstamo?",
				descripcion, "Devolver");
	}

	private FormularioEliminar(Window propietario, String titulo, String pregunta, String descripcion,
			String textoAccion) {

		// Con propietario, la ventana se centra sobre la aplicación y no se
		// puede quedar escondida detrás de ella
		super(propietario, titulo, Dialog.ModalityType.APPLICATION_MODAL);

		setSize(420, 220);
		setLocationRelativeTo(propietario);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));
		setContentPane(panelPrincipal);

		// ========================================
		// TÍTULO
		// ========================================
		JLabel etiquetaTitulo = new JLabel(titulo);
		etiquetaTitulo.setFont(UtilVista.FUENTE_SUBTITULO);
		panelPrincipal.add(etiquetaTitulo, BorderLayout.NORTH);

		// ========================================
		// MENSAJE
		// ========================================
		JPanel panelMensaje = new JPanel();
		panelMensaje.setBackground(Color.WHITE);
		panelMensaje.setLayout(new BoxLayout(panelMensaje, BoxLayout.Y_AXIS));

		JLabel etiquetaPregunta = new JLabel(pregunta);
		etiquetaPregunta.setFont(UtilVista.FUENTE_TEXTO);

		JLabel etiquetaElemento = new JLabel(descripcion);
		etiquetaElemento.setFont(UtilVista.FUENTE_TEXTO_NEGRITA);

		panelMensaje.add(etiquetaPregunta);
		panelMensaje.add(Box.createVerticalStrut(8));
		panelMensaje.add(etiquetaElemento);

		panelPrincipal.add(panelMensaje, BorderLayout.CENTER);

		// ========================================
		// BOTONES
		// ========================================
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		panelBotones.setBackground(Color.WHITE);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(100, 35));

		JButton botonAccion = UtilVista.crearBoton(textoAccion, UtilVista.COLOR_ROJO);
		botonAccion.setPreferredSize(new Dimension(100, 35));

		panelBotones.add(botonCancelar);
		panelBotones.add(botonAccion);

		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

		// ========================================
		// EVENTOS
		// ========================================
		botonCancelar.addActionListener(e -> dispose());

		botonAccion.addActionListener(e -> {
			confirmado = true;
			dispose();
		});
	}

	// =========================================================
	// RESULTADO
	// =========================================================
	/** true si la persona ha pulsado el botón de la acción (no Cancelar ni cerrar). */
	public boolean isConfirmado() {

		return confirmado;
	}
}