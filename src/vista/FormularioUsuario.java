package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioUsuario extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField campoId;
    private JTextField campoNombre;
    private JTextField campoEmail;

    private JButton botonGuardar;
    private JButton botonCancelar;

    public FormularioUsuario() {

        setTitle("Añadir usuario");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setModal(true);

        setDefaultCloseOperation(
            JDialog.DISPOSE_ON_CLOSE
        );

        // =========================
        // PANEL PRINCIPAL
        // =========================

        JPanel panelPrincipal = new JPanel(
            new BorderLayout()
        );

        panelPrincipal.setBackground(
            new Color(249, 247, 242)
        );

        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(
                20, 30, 20, 30
            )
        );

        setContentPane(panelPrincipal);

        // =========================
        // TITULO
        // =========================

        JLabel etiquetaTitulo = new JLabel(
            "Añadir usuario"
        );

        etiquetaTitulo.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                24
            )
        );

        panelPrincipal.add(
            etiquetaTitulo,
            BorderLayout.NORTH
        );

        // =========================
        // FORMULARIO
        // =========================

        JPanel panelFormulario = new JPanel(
            new GridLayout(
                3, 2,
                10, 15
            )
        );

        panelFormulario.setBackground(
            Color.WHITE
        );

        panelFormulario.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    20, 20, 20, 20
                )
            )
        );

        // ID

        JLabel etiquetaId = new JLabel(
            "ID:"
        );

        campoId = new JTextField();

        // NOMBRE

        JLabel etiquetaNombre = new JLabel(
            "Nombre:"
        );

        campoNombre = new JTextField();

        // EMAIL

        JLabel etiquetaEmail = new JLabel(
            "Email:"
        );

        campoEmail = new JTextField();

        panelFormulario.add(
            etiquetaId
        );

        panelFormulario.add(
            campoId
        );

        panelFormulario.add(
            etiquetaNombre
        );

        panelFormulario.add(
            campoNombre
        );

        panelFormulario.add(
            etiquetaEmail
        );

        panelFormulario.add(
            campoEmail
        );

        panelPrincipal.add(
            panelFormulario,
            BorderLayout.CENTER
        );

        // =========================
        // BOTONES
        // =========================

        JPanel panelBotones = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                10,
                0
            )
        );

        panelBotones.setOpaque(false);

        botonCancelar = new JButton(
            "Cancelar"
        );

        botonCancelar.setPreferredSize(
            new Dimension(100, 35)
        );

        botonGuardar = new JButton(
            "Guardar"
        );

        botonGuardar.setBackground(
            new Color(123, 220, 99)
        );

        botonGuardar.setPreferredSize(
            new Dimension(100, 35)
        );

        panelBotones.add(
            botonCancelar
        );

        panelBotones.add(
            botonGuardar
        );

        panelPrincipal.add(
            panelBotones,
            BorderLayout.SOUTH
        );

        // =========================
        // ACCIONES
        // =========================

        botonCancelar.addActionListener(
            e -> dispose()
        );
    }

    // =========================
    // GETTERS
    // =========================

    public JTextField getCampoId() {
        return campoId;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    // =========================
    // MODO EDICIÓN
    // =========================

    public void cargarUsuario(
            String id,
            String nombre,
            String email) {

        setTitle("Editar usuario");

        campoId.setText(id);
        campoNombre.setText(nombre);
        campoEmail.setText(email);

        campoId.setEnabled(true);
    }
}