package sisfinan;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        financiamentos.add(new Financiamento(200000, 220000));
        financiamentos.add(new Financiamento(300000, 380000));
        financiamentos.add(new Financiamento(150000, 155000));
        financiamentos.add(new Financiamento(250000, 275000));

        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;

        for (Financiamento financiamento : financiamentos) {
            totalValorImoveis += financiamento.getValorImovel();
            totalValorFinanciamentos += financiamento.getValorFinanciamento();
        }

        System.out.println("Total de todos os imóveis: R$ " + totalValorImoveis);
        System.out.println("Total de todos os financiamentos: R$ " + totalValorFinanciamentos);
    }
}