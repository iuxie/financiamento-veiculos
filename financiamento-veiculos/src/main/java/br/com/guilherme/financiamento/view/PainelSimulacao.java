package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.service.FinanciamentoService;
import java.awt.*;
import javax.swing.*;

public class PainelSimulacao extends JPanel {
    private final FinanciamentoService service;
    private final PainelResultado resultado = new PainelResultado();
    private final PainelVeiculoUsado usado = new PainelVeiculoUsado(this::invalidarResultado);
    private final PainelVeiculo veiculo = new PainelVeiculo(usado, this::invalidarResultado);
    private final PainelFinanciamento financiamento = new PainelFinanciamento(this::invalidarResultado);
    public PainelSimulacao(FinanciamentoService service) {
        super(new BorderLayout(0, 16));
        this.service = service;
        setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        JLabel titulo = new JLabel("Financiamento de Carros", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 24f)); add(titulo, BorderLayout.NORTH);
        JPanel conteudo = new JPanel(); conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.add(veiculo); conteudo.add(usado); conteudo.add(financiamento);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        JButton calcular = new JButton("Calcular"); JButton limpar = new JButton("Limpar");
        botoes.add(calcular); botoes.add(limpar); conteudo.add(botoes); conteudo.add(resultado);
        JPanel corpo = new JPanel(new BorderLayout()); corpo.add(conteudo, BorderLayout.NORTH);
        add(corpo, BorderLayout.CENTER);
        calcular.addActionListener(e -> calcular()); limpar.addActionListener(e -> limpar());
    }
    private void invalidarResultado() { resultado.setVisible(false); revalidate(); repaint(); }
    private void calcular() {
        invalidarResultado();
        try {
            resultado.mostrar(service.calcular(veiculo.lerVeiculo(), financiamento.lerCondicoes()));
            revalidate(); repaint();
            SwingUtilities.invokeLater(() -> resultado.scrollRectToVisible(
                    new Rectangle(0, 0, resultado.getWidth(), resultado.getHeight())));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Verifique os dados", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void limpar() { veiculo.limpar(); financiamento.limpar(); resultado.limpar(); revalidate(); repaint(); }
}
