package br.com.guilherme.financiamento.model;
import java.math.BigDecimal;

public abstract class Veiculo {
    private final String marca;
    private final String modelo;
    private final int ano;
    private final BigDecimal valor;

    protected Veiculo(String marca, String modelo, int ano, BigDecimal valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
    }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public BigDecimal getValor() { return valor; }

    public void validar() {
        if (marca == null || marca.isBlank() || marca.equals("Selecione"))
            throw new IllegalArgumentException("Selecione a marca do veículo.");
        if (modelo == null || modelo.isBlank())
            throw new IllegalArgumentException("Informe o modelo do veículo.");
        if (ano < 2000 || ano > 2026)
            throw new IllegalArgumentException("O ano deve estar entre 2000 e 2026.");
        if (valor == null || valor.signum() <= 0)
            throw new IllegalArgumentException("O valor do veículo deve ser maior que zero.");
    }
}
