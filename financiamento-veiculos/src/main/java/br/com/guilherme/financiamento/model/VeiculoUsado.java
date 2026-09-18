package br.com.guilherme.financiamento.model;
import java.math.BigDecimal;

public class VeiculoUsado extends Veiculo {
    private final BigDecimal quilometragem;
    private final int proprietarios;

    public VeiculoUsado(String marca, String modelo, int ano, BigDecimal valor,
                        BigDecimal quilometragem, int proprietarios) {
        super(marca, modelo, ano, valor);
        this.quilometragem = quilometragem;
        this.proprietarios = proprietarios;
    }
    public BigDecimal getQuilometragem() { return quilometragem; }
    public int getProprietarios() { return proprietarios; }

    @Override
    public void validar() {
        super.validar();
        if (quilometragem == null || quilometragem.signum() < 0)
            throw new IllegalArgumentException("Informe uma quilometragem não negativa.");
        if (proprietarios <= 0)
            throw new IllegalArgumentException("Informe uma quantidade de proprietários maior que zero.");
    }
}
