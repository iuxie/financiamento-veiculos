package br.com.guilherme.financiamento.service;
import br.com.guilherme.financiamento.model.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanciamentoService {
    public static final BigDecimal TAXA_MENSAL = new BigDecimal("0.015");

    public ResultadoFinanciamento calcular(Veiculo veiculo, CondicoesFinanciamento condicoes) {
        if (veiculo == null || condicoes == null)
            throw new IllegalArgumentException("Informe os dados do veículo e do financiamento.");
        veiculo.validar();
        condicoes.validar(veiculo.getValor());
        BigDecimal financiado = veiculo.getValor().subtract(condicoes.entradaEfetiva());
        BigDecimal fator = BigDecimal.ONE.add(TAXA_MENSAL).pow(condicoes.parcelas());
        BigDecimal parcela = financiado.multiply(TAXA_MENSAL).multiply(fator)
                .divide(fator.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        return new ResultadoFinanciamento(financiado, parcela,
                parcela.multiply(BigDecimal.valueOf(condicoes.parcelas())));
    }
}
