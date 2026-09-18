package br.com.guilherme.financiamento.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class PainelFormulario extends JPanel {
    protected PainelFormulario(String titulo) {
        super(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(titulo));
    }
    protected void linha(int numero, String texto, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = numero; c.gridx = 0; c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(6, 8, 6, 8);
        JLabel rotulo = new JLabel(texto);
        rotulo.setLabelFor(campo);
        add(rotulo, c);
        c.gridx = 1; c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        add(campo, c);
    }
    protected void linhaCompleta(int numero, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = numero; c.gridx = 0; c.gridwidth = 2; c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.insets = new Insets(6, 8, 6, 8);
        add(campo, c);
    }
    protected void observar(JTextField campo, Runnable acao) {
        campo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { acao.run(); }
            public void removeUpdate(DocumentEvent e) { acao.run(); }
            public void changedUpdate(DocumentEvent e) { acao.run(); }
        });
    }
    public abstract void limpar();
}
