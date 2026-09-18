package br.com.guilherme.financiamento.util;
import java.math.BigDecimal;

public final class ConversorNumerico {
    private ConversorNumerico() { }
    public static BigDecimal lerDecimal(String texto, String nome) {
        String s = texto.trim();
        if (!s.matches("(?:[0-9]+|[0-9]{1,3}(?:\\.[0-9]{3})+)(?:,[0-9]{1,2})?"))
            throw new IllegalArgumentException(nome + ": informe um número válido, como 50.000,00.");
        return new BigDecimal(s.replace(".", "").replace(',', '.'));
    }


    public static int lerInteiroPositivo(String texto, String nome) {
        try {
            if (!texto.trim().matches("[0-9]+")) throw new NumberFormatException();
            int valor = Integer.parseInt(texto.trim());
            if (valor <= 0) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nome + ": informe um número inteiro positivo válido.");
        }
    }
}
