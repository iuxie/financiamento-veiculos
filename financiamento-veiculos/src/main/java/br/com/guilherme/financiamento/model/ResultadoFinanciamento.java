package br.com.guilherme.financiamento.model;
import java.math.BigDecimal;

public record ResultadoFinanciamento(BigDecimal valorFinanciado, BigDecimal valorParcela, BigDecimal totalPagar) { }
