package sisfinan;

import java.util.ArrayList;

public class Financiamento {
    private double valorImovel;
    private double valorFinanciamento;

    public Financiamento(double valorImovel, double valorFinanciamento) {
        this.valorImovel = valorImovel;
        this.valorFinanciamento = valorFinanciamento;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public double getValorFinanciamento() {
        return valorFinanciamento;
    }
}