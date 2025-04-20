package sisfinan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MainGUI {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalImoveisLabel;
    private JLabel totalFinanciamentosLabel;

    public MainGUI() {
        frame = new JFrame("Sistema Financeiro - Imóveis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        String[] colunas = {"Descrição", "Valor", "Financiado?"};
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarBtn);
        buttonPanel.add(calcularBtn);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(totalPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void adicionarImovel() {
        JTextField descricao = new JTextField();
        JTextField valor = new JTextField();
        JCheckBox financiado = new JCheckBox("Financiado");

        Object[] fields = {
            "Descrição:", descricao,
            "Valor:", valor,
            financiado
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Adicionar Imóvel", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String desc = descricao.getText();
                double val = Double.parseDouble(valor.getText());
                boolean isFinanciado = financiado.isSelected();
                model.addRow(new Object[]{desc, val, isFinanciado ? "Sim" : "Não"});
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
