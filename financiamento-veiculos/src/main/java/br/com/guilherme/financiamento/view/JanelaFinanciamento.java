package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.service.FinanciamentoService;
import java.awt.Dimension;
import javax.swing.*;

public class JanelaFinanciamento extends JFrame {
    public JanelaFinanciamento(FinanciamentoService service) {
        super("Financiamento de Carros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane rolagem = new JScrollPane(new PainelSimulacao(service));
        rolagem.getVerticalScrollBar().setUnitIncrement(16);
        setContentPane(rolagem); setSize(660, 820); setMinimumSize(new Dimension(520, 480));
        setLocationRelativeTo(null);
    }
}
