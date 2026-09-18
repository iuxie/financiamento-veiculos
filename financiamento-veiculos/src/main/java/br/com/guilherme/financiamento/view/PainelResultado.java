package br.com.guilherme.financiamento.view;
import br.com.guilherme.financiamento.model.ResultadoFinanciamento;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class PainelResultado extends JPanel {
    private final JLabel financiado = new JLabel();
    private final JLabel parcela = new JLabel();
    private final JLabel total = new JLabel();
    public PainelResultado() {
        super(new GridLayout(0, 1, 0, 8));
        setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(financiado); add(parcela); add(total); setVisible(false);
    }
    public void mostrar(ResultadoFinanciamento resultado) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        financiado.setText("Valor financiado: " + moeda.format(resultado.valorFinanciado()));
        parcela.setText("Valor da parcela: " + moeda.format(resultado.valorParcela()));
        total.setText("Total a pagar: " + moeda.format(resultado.totalPagar()));
        setVisible(true);
    }
    public void limpar() {
        financiado.setText(""); parcela.setText(""); total.setText(""); setVisible(false);
    }
}
