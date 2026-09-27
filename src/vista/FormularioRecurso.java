package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioRecurso extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField campoId;
    private JTextField campoTitulo;
    private JTextField campoAno;

    private JComboBox<String> comboTipo;

    private JTextField campoEspecifico1;
    private JTextField campoEspecifico2;

    private JLabel etiquetaEspecifico1;
    private JLabel etiquetaEspecifico2;

    private JButton botonGuardar;
    private JButton botonCancelar;

    public FormularioRecurso() {

        setTitle("Añadir recurso");

        setSize(550, 500);

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
            "Añadir recurso"
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
                0, 2,
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

        // =========================
        // ID
        // =========================

        JLabel etiquetaId = new JLabel(
            "ID:"
        );

        campoId = new JTextField();

        panelFormulario.add(
            etiquetaId
        );

        panelFormulario.add(
            campoId
        );

        // =========================
        // TITULO
        // =========================

        JLabel etiquetaTituloRecurso =
            new JLabel("Título:");

        campoTitulo = new JTextField();

        panelFormulario.add(
            etiquetaTituloRecurso
        );

        panelFormulario.add(
            campoTitulo
        );

        // =========================
        // AÑO
        // =========================

        JLabel etiquetaAno = new JLabel(
            "Año:"
        );

        campoAno = new JTextField();

        panelFormulario.add(
            etiquetaAno
        );

        panelFormulario.add(
            campoAno
        );

        // =========================
        // TIPO
        // =========================

        JLabel etiquetaTipo = new JLabel(
            "Tipo:"
        );

        comboTipo = new JComboBox<>(
            new String[] {
                "Libro",
                "Película",
                "Videojuego"
            }
        );

        panelFormulario.add(
            etiquetaTipo
        );

        panelFormulario.add(
            comboTipo
        );

        // =========================
        // CAMPOS ESPECIFICOS
        // =========================

        etiquetaEspecifico1 = new JLabel();
        campoEspecifico1 = new JTextField();

        etiquetaEspecifico2 = new JLabel();
        campoEspecifico2 = new JTextField();

        panelFormulario.add(
            etiquetaEspecifico1
        );

        panelFormulario.add(
            campoEspecifico1
        );

        panelFormulario.add(
            etiquetaEspecifico2
        );

        panelFormulario.add(
            campoEspecifico2
        );

        actualizarCamposEspecificos();

        comboTipo.addActionListener(
            e -> actualizarCamposEspecificos()
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
    // CAMPOS SEGÚN TIPO
    // =========================

    private void actualizarCamposEspecificos() {

        String tipo =
            (String) comboTipo.getSelectedItem();

        if ("Libro".equals(tipo)) {

            etiquetaEspecifico1.setText(
                "Autor:"
            );

            etiquetaEspecifico2.setText(
                "Páginas:"
            );

        } else if ("Película".equals(tipo)) {

            etiquetaEspecifico1.setText(
                "Director:"
            );

            etiquetaEspecifico2.setText(
                "Duración:"
            );

        } else if ("Videojuego".equals(tipo)) {

            etiquetaEspecifico1.setText(
                "Plataforma:"
            );

            etiquetaEspecifico2.setText(
                "PEGI:"
            );
        }
    }

    // =========================
    // GETTERS
    // =========================

    public JTextField getCampoId() {
        return campoId;
    }

    public JTextField getCampoTitulo() {
        return campoTitulo;
    }

    public JTextField getCampoAno() {
        return campoAno;
    }

    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }

    public JTextField getCampoEspecifico1() {
        return campoEspecifico1;
    }

    public JTextField getCampoEspecifico2() {
        return campoEspecifico2;
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

    public void cargarRecurso(
            String id,
            String titulo,
            String ano,
            String tipo,
            String especifico1,
            String especifico2) {

        setTitle("Editar recurso");

        campoId.setText(id);
        campoTitulo.setText(titulo);
        campoAno.setText(ano);

        comboTipo.setSelectedItem(tipo);
        
        actualizarCamposEspecificos();

        campoEspecifico1.setText(
            especifico1
        );

        campoEspecifico2.setText(
            especifico2
        );

        campoId.setEnabled(false);
    }
}