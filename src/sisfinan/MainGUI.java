package sisfinan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainGUI {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalImoveisLabel;
    private JLabel totalFinanciamentosLabel;

    public MainGUI() {
        frame = new JFrame("Sistema Financeiro - Imóveis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        String[] colunas = {"Descrição", "Valor", "Financiado?", "Tipo", "Taxa de Juros", "Prazo"};
        model = new DefaultTableModel(colunas, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        JPanel totalPanel = new JPanel(new GridLayout(2, 1));
        totalImoveisLabel = new JLabel("Total de todos os imóveis: R$ 0.00");
        totalFinanciamentosLabel = new JLabel("Total de todos os financiamentos: R$ 0.00");
        totalPanel.add(totalImoveisLabel);
        totalPanel.add(totalFinanciamentosLabel);

        JButton calcularBtn = new JButton("Calcular Totais");
        calcularBtn.addActionListener(e -> calcularTotais());

        JButton adicionarBtn = new JButton("Adicionar Imóvel");
        adicionarBtn.addActionListener(e -> adicionarImovel());

        JButton baixarSimulacoesBtn = new JButton("Baixar Simulações");
        baixarSimulacoesBtn.addActionListener(e -> baixarSimulacoes());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarBtn);
        buttonPanel.add(calcularBtn);
        buttonPanel.add(baixarSimulacoesBtn);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(totalPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void adicionarImovel() {
        JTextField descricao = new JTextField();
        JTextField valor = new JTextField();
        JCheckBox financiado = new JCheckBox("Financiado");
        JComboBox<String> tipoImovel = new JComboBox<>(new String[]{"Apartamento", "Casa"});
        JTextField taxaJuros = new JTextField();
        JTextField prazo = new JTextField();

        Object[] fields = {
            "Descrição:", descricao,
            "Valor:", valor,
            financiado,
            "Tipo de Imóvel:", tipoImovel,
            "Taxa de Juros (%):", taxaJuros,
            "Prazo (anos):", prazo
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Adicionar Imóvel", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String desc = descricao.getText();
                double val = Double.parseDouble(valor.getText());
                boolean isFinanciado = financiado.isSelected();
                String tipo = tipoImovel.getSelectedItem().toString();
                double taxa = Double.parseDouble(taxaJuros.getText());
                int prazoFinanciamento = Integer.parseInt(prazo.getText());

                model.addRow(new Object[]{desc, val, isFinanciado ? "Sim" : "Não", tipo, taxa, prazoFinanciamento});
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calcularTotais() {
        double totalImoveis = 0;
        double totalFinanciado = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            double valor = Double.parseDouble(model.getValueAt(i, 1).toString());
            String financiado = model.getValueAt(i, 2).toString();

            totalImoveis += valor;
            if (financiado.equalsIgnoreCase("Sim")) {
                totalFinanciado += valor;
            }
        }

        totalImoveisLabel.setText("Total de todos os imóveis: R$ " + totalImoveis);
        totalFinanciamentosLabel.setText("Total de todos os financiamentos: R$ " + totalFinanciado);
    }

    private void baixarSimulacoes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("simulacoes.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String descricao = model.getValueAt(i, 0).toString();
                String valor = model.getValueAt(i, 1).toString();
                String financiado = model.getValueAt(i, 2).toString();
                String tipo = model.getValueAt(i, 3).toString();
                String taxa = model.getValueAt(i, 4).toString();
                String prazo = model.getValueAt(i, 5).toString();

                writer.write("Descrição: " + descricao + ", Valor: " + valor + ", Financiado: " + financiado +
                             ", Tipo: " + tipo + ", Taxa de Juros: " + taxa + "%, Prazo: " + prazo + " anos");
                writer.newLine();
            }
            JOptionPane.showMessageDialog(frame, "Simulações salvas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar simulações.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}