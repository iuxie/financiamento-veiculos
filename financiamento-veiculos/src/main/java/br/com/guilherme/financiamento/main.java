package br.com.guilherme.financiamento;

import br.com.guilherme.financiamento.service.FinanciamentoService;
import br.com.guilherme.financiamento.view.JanelaFinanciamento;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
            }
            new JanelaFinanciamento(new FinanciamentoService()).setVisible(true);
        });
    }
}
