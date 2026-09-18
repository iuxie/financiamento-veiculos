package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.model.CondicoesFinanciamento;
import br.com.guilherme.financiamento.service.FinanciamentoService;
import br.com.guilherme.financiamento.util.ConversorNumerico;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import javax.swing.*;

public class PainelFinanciamento extends PainelFormulario {
    private final JCheckBox possuiEntrada = new JCheckBox("Possui entrada?");
    private final JTextField entrada = new JTextField(20);
    private final JPanel linhaEntrada = new JPanel(new BorderLayout(12, 0));
    private final JComboBox<Integer> parcelas = new JComboBox<>(new Integer[] {12, 24, 36, 48, 60});
    public PainelFinanciamento(Runnable alteracao) {
        super("Financiamento");
        JLabel rotulo = new JLabel("Entrada (R$)"); rotulo.setLabelFor(entrada);
        linhaEntrada.add(rotulo, BorderLayout.WEST); linhaEntrada.add(entrada, BorderLayout.CENTER);
        linhaCompleta(0, possuiEntrada); linhaCompleta(1, linhaEntrada);
        linha(2, "Parcelas", parcelas);
        linhaCompleta(3, new JLabel("Taxa mensal (parcelas fixas): "
                + FinanciamentoService.TAXA_MENSAL.movePointRight(2).stripTrailingZeros()
                        .toPlainString().replace('.', ',') + "% ao mês"));
        possuiEntrada.addActionListener(e -> { linhaEntrada.setVisible(possuiEntrada.isSelected()); alteracao.run(); });
        parcelas.addActionListener(e -> alteracao.run()); observar(entrada, alteracao);
        linhaEntrada.setVisible(false);
    }
    public CondicoesFinanciamento lerCondicoes() {
        BigDecimal v = possuiEntrada.isSelected()
                ? ConversorNumerico.lerDecimal(entrada.getText(), "Entrada") : BigDecimal.ZERO;
        return new CondicoesFinanciamento(possuiEntrada.isSelected(), v, (Integer) parcelas.getSelectedItem());
    }
    @Override public void limpar() {
        possuiEntrada.setSelected(false); entrada.setText(""); parcelas.setSelectedIndex(0);
        linhaEntrada.setVisible(false);
    }
}
