package sisfinan;

import java.io.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        financiamentos.add(new Financiamento(200000, 220000, 5.5, 30, "Apartamento, 10º andar"));
        financiamentos.add(new Financiamento(300000, 380000, 4.0, 20, "Casa, 200m²"));
        financiamentos.add(new Financiamento(150000, 155000, 6.0, 15, "Apartamento, 5º andar"));
        financiamentos.add(new Financiamento(250000, 275000, 3.5, 25, "Casa, 150m²"));

        // Salvar dados em um arquivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("financiamentos.txt"))) {
            for (Financiamento financiamento : financiamentos) {
                writer.write(financiamento.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ler dados do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader("financiamentos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Serializar o ArrayList
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("financiamentos.ser"))) {
            out.writeObject(financiamentos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Desserializar o ArrayList
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("financiamentos.ser"))) {
            ArrayList<Financiamento> financiamentosDesserializados = (ArrayList<Financiamento>) in.readObject();
            for (Financiamento financiamento : financiamentosDesserializados) {
                System.out.println(financiamento);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}