package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoVendasView extends JFrame {
    private JList<String> historicoList;
    private DefaultListModel<String> historicoListModel;

    public HistoricoVendasView(List<String> historicoVendas) {
        setTitle("Histórico de Vendas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        historicoListModel = new DefaultListModel<>();
        historicoList = new JList<>(historicoListModel);

        JScrollPane scrollPane = new JScrollPane(historicoList);
        add(scrollPane, BorderLayout.CENTER);

        // Popula a lista com os dados do histórico de vendas
        for (String venda : historicoVendas) {
            historicoListModel.addElement(venda);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        // Exemplo de uso:
        List<String> historico = new ArrayList<>();
        historico.add("Venda 1 - Cliente: João - Total: R$100");
        historico.add("Venda 2 - Cliente: Maria - Total: R$150");

        SwingUtilities.invokeLater(() -> new HistoricoVendasView(historico));
    }
}

