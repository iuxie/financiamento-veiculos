package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.model.*;
import br.com.guilherme.financiamento.util.ConversorNumerico;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import javax.swing.*;

public class PainelVeiculo extends PainelFormulario {
    private final JComboBox<String> marca = new JComboBox<>(new String[] {
        "Selecione", "Fiat", "Chevrolet", "Volkswagen", "Ford", "Toyota", "Honda", "Hyundai", "Renault", "Nissan", "Outra"
    });
    private final JTextField modelo = new JTextField(20);
    private final JComboBox<Integer> ano = new JComboBox<>();
    private final JTextField valor = new JTextField(20);
    private final JRadioButton novo = new JRadioButton("Novo", true);
    private final JRadioButton usado = new JRadioButton("Usado");
    private final PainelVeiculoUsado dadosUsado;
    public PainelVeiculo(PainelVeiculoUsado dadosUsado, Runnable alteracao) {
        super("Dados do veículo");
        this.dadosUsado = dadosUsado;
        for (int a = 2026; a >= 2000; a--) ano.addItem(a);
        linha(0, "Marca", marca); linha(1, "Modelo", modelo);
        linha(2, "Ano", ano); linha(3, "Valor (R$)", valor);
        ButtonGroup grupo = new ButtonGroup(); grupo.add(novo); grupo.add(usado);
        JPanel tipos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipos.add(novo); tipos.add(usado); linha(4, "Tipo", tipos);
        novo.addActionListener(e -> { atualizarTipo(); alteracao.run(); });
        usado.addActionListener(e -> { atualizarTipo(); alteracao.run(); });
        marca.addActionListener(e -> alteracao.run());
        ano.addActionListener(e -> alteracao.run());
        observar(modelo, alteracao); observar(valor, alteracao);
        atualizarTipo();
    }
    private void atualizarTipo() { dadosUsado.setVisible(usado.isSelected()); }
    public Veiculo lerVeiculo() {
        String m = (String) marca.getSelectedItem();
        String descricao = modelo.getText().trim();
        BigDecimal v = ConversorNumerico.lerDecimal(valor.getText(), "Valor do veículo");
        int a = (Integer) ano.getSelectedItem();
        return usado.isSelected() ? dadosUsado.lerVeiculo(m, descricao, a, v)
                : new VeiculoNovo(m, descricao, a, v);
    }
    @Override public void limpar() {
        marca.setSelectedIndex(0); ano.setSelectedIndex(0);
        modelo.setText(""); valor.setText(""); novo.setSelected(true);
        dadosUsado.limpar(); atualizarTipo();
    }
}
