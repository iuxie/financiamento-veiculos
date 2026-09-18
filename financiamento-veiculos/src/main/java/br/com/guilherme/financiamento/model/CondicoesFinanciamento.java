package br.com.guilherme.financiamento.model;
import java.math.BigDecimal;

public record CondicoesFinanciamento(boolean possuiEntrada, BigDecimal entrada, int parcelas) {
    public BigDecimal entradaEfetiva() {
        return possuiEntrada ? entrada : BigDecimal.ZERO;
    }
    public void validar(BigDecimal valorVeiculo) {
        if (possuiEntrada && (entrada == null || entrada.signum() <= 0 || entrada.compareTo(valorVeiculo) >= 0))
            throw new IllegalArgumentException("A entrada deve ser positiva e menor que o valor do veículo.");
        if (parcelas != 12 && parcelas != 24 && parcelas != 36 && parcelas != 48 && parcelas != 60)
            throw new IllegalArgumentException("Selecione uma quantidade de parcelas válida.");
    }
}
