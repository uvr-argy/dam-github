package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormularioEliminar extends JDialog {

	private static final long serialVersionUID = 1L;
	private boolean confirmado;

    public FormularioEliminar(String tipo, String identificador) {

        setTitle("Confirmar eliminación");
        setSize(450, 220);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        JLabel etiqueta = new JLabel(
            "<html><center>¿Seguro que quieres eliminar este "
            + tipo.toLowerCase()
            + "?<br><br>"
            + identificador
            + "</center></html>"
        );

        etiqueta.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton botonCancelar = new JButton("Cancelar");
        JButton botonEliminar = new JButton("Eliminar");

        botonEliminar.setBackground(new java.awt.Color(220, 80, 80));
        botonEliminar.setForeground(java.awt.Color.WHITE);

        botonCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        botonEliminar.addActionListener(e -> {
            confirmado = true;
            dispose();
        });

        panelBotones.add(botonCancelar);
        panelBotones.add(botonEliminar);

        panelPrincipal.add(etiqueta, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}