package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.model.VeiculoUsado;
import br.com.guilherme.financiamento.util.ConversorNumerico;
import java.math.BigDecimal;
import javax.swing.JTextField;

public class PainelVeiculoUsado extends PainelFormulario {
    private final JTextField quilometragem = new JTextField(20);
    private final JTextField proprietarios = new JTextField(20);
    public PainelVeiculoUsado(Runnable alteracao) {
        super("Dados do veículo usado");
        linha(0, "Quilometragem (km)", quilometragem);
        linha(1, "Proprietários", proprietarios);
        observar(quilometragem, alteracao);
        observar(proprietarios, alteracao);
    }
    public VeiculoUsado lerVeiculo(String marca, String modelo, int ano, BigDecimal valor) {
        return new VeiculoUsado(marca, modelo, ano, valor,
            ConversorNumerico.lerDecimal(quilometragem.getText(), "Quilometragem"),
            ConversorNumerico.lerInteiroPositivo(proprietarios.getText(), "Proprietários"));
    }
    @Override public void limpar() {
        quilometragem.setText("");
        proprietarios.setText("");
    }
}
