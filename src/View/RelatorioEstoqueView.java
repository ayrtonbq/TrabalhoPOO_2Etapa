package View;

import javax.swing.*;

import DAO.ProdutoDAO;

import java.awt.*;
import java.util.List;

public class RelatorioEstoqueView extends JFrame {
    private JTextArea relatorioTextArea;

    public RelatorioEstoqueView(List<String> relatorio) {
        setTitle("Relatório de Estoque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        relatorioTextArea = new JTextArea(15, 50);
        relatorioTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(relatorioTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Popula a área de texto com os dados do relatório de estoque
        for (String linha : relatorio) {
            relatorioTextArea.append(linha + "\n");
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<String> relatorio = produtoDAO.listarRelatorioEstoque();
                new RelatorioEstoqueView(relatorio);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar o relatório de estoque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
