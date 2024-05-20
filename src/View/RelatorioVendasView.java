package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RelatorioVendasView extends JFrame {
    private JTextArea relatorioTextArea;
    private JTextField dataInicioField;
    private JTextField dataFimField;

    public RelatorioVendasView() {
        setTitle("Relatório de Vendas por Período");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel filtroPanel = new JPanel();
        JLabel inicioLabel = new JLabel("Data de Início:");
        JLabel fimLabel = new JLabel("Data de Fim:");
        dataInicioField = new JTextField(10);
        dataFimField = new JTextField(10);
        JButton gerarRelatorioButton = new JButton("Gerar Relatório");

        filtroPanel.add(inicioLabel);
        filtroPanel.add(dataInicioField);
        filtroPanel.add(fimLabel);
        filtroPanel.add(dataFimField);
        filtroPanel.add(gerarRelatorioButton);

        relatorioTextArea = new JTextArea(15, 50);
        relatorioTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(relatorioTextArea);

        add(filtroPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você implementaria a lógica para gerar o relatório com base nas datas
                String dataInicio = dataInicioField.getText();
                String dataFim = dataFimField.getText();

                // Exemplo de uso: recuperação de relatório de vendas
                List<String> relatorio = gerarRelatorio(dataInicio, dataFim);

                // Popula a área de texto com os dados do relatório de vendas
                relatorioTextArea.setText("");
                for (String linha : relatorio) {
                    relatorioTextArea.append(linha + "\n");
                }
            }
        });

        setVisible(true);
    }

    // Método de exemplo para gerar relatório de vendas
    private List<String> gerarRelatorio(String dataInicio, String dataFim) {
        return List.of(
                "Venda 1 - Cliente: João - Data: 2024-05-01 - Total: R$100",
                "Venda 2 - Cliente: Maria - Data: 2024-05-05 - Total: R$150"
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RelatorioVendasView::new);
    }
}
