package sisfinan;

import java.util.ArrayList;
import java.io.Serializable;

public class Financiamento implements Serializable {
    private double valorImovel;
    private double valorFinanciamento;
    private double taxaJuros;
    private int prazo;
    private String tipoImovel;

    public Financiamento(double valorImovel, double valorFinanciamento, double taxaJuros, int prazo, String tipoImovel) {
        this.valorImovel = valorImovel;
        this.valorFinanciamento = valorFinanciamento;
        this.taxaJuros = taxaJuros;
        this.prazo = prazo;
        this.tipoImovel = tipoImovel;
    }

    // Getters
    public double getValorImovel() {
        return valorImovel;
    }

    public double getValorFinanciamento() {
        return valorFinanciamento;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public int getPrazo() {
        return prazo;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    @Override
    public String toString() {
        return "Valor do Imóvel: " + valorImovel +
               ", Valor do Financiamento: " + valorFinanciamento +
               ", Taxa de Juros: " + taxaJuros +
               ", Prazo: " + prazo +
               ", Tipo de Imóvel: " + tipoImovel;
    }
}