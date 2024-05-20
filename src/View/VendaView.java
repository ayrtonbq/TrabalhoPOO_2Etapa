package View;

import Controller.VendaController;
import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class VendaView extends JFrame {
    private JTextField txtProdutoNome, txtProdutoDescricao, txtPrecoVenda, txtQuantidade, txtDesconto, txtCategoria;
    private JButton btnAdicionarProduto, btnFinalizarVenda, btnAplicarDesconto;
    private JTextArea txtAreaVenda;
    private JComboBox<String> comboBoxTipoProduto;
    private JComboBox<ProdutoModel> comboBoxProdutos;
    private int codigoCliente;

    private VendaController vendaController;
    private VendaModel vendaModel;
    private FormaPagamentoModel formaPagamentoPadrao;

    public VendaView() {
        vendaController = new VendaController();
        vendaModel = new VendaModel(new ClienteModel(), new FormaPagamentoDinheiroModel());

        setTitle("Venda");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Adiciona JComboBox para exibir os clientes cadastrados
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<ClienteModel> clientes = clienteDAO.buscarTodosClientes();
            JComboBox<ClienteModel> comboBoxClientes = new JComboBox<>(clientes.toArray(new ClienteModel[0]));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(new JLabel("Clientes:"), gbc);
            gbc.gridx = 2;
            gbc.gridwidth = 4;
            add(comboBoxClientes, gbc);

            comboBoxClientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClienteModel clienteSelecionado = (ClienteModel) comboBoxClientes.getSelectedItem();
                    if (clienteSelecionado != null) {
                        vendaModel.getCliente().setCodigo(clienteSelecionado.getCodigo());
                    }
                }
            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Adiciona JComboBox para exibir os produtos cadastrados
        comboBoxProdutos = new JComboBox<>();
        carregarProdutos();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(new JLabel("Produtos:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 4;
        add(comboBoxProdutos, gbc);

        // Adiciona ação para preencher os detalhes do produto ao selecionar no comboBox
        comboBoxProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoModel produtoSelecionado = (ProdutoModel) comboBoxProdutos.getSelectedItem();
                if (produtoSelecionado != null) {
                    txtProdutoNome.setText(produtoSelecionado.getNome());
                    txtProdutoDescricao.setText(produtoSelecionado.getDescricao());
                    txtPrecoVenda.setText(String.valueOf(produtoSelecionado.getPrecoVenda()));
                    txtCategoria.setText(produtoSelecionado.getCategoria());
                    comboBoxTipoProduto.setSelectedItem(produtoSelecionado.getTipoProduto());
                }
            }
        });

        // Inicializa os campos de texto
        txtProdutoNome = new JTextField(15);
        txtProdutoDescricao = new JTextField(15);
        txtPrecoVenda = new JTextField(10);
        txtQuantidade = new JTextField(5);
        txtCategoria = new JTextField(10);
        txtDesconto = new JTextField(5);

        // Adiciona os componentes à interface gráfica
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(new JLabel("Nome do Produto:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 4;
        add(txtProdutoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(new JLabel("Descrição do Produto:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 4;
        add(txtProdutoDescricao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(new JLabel("Preço de Venda:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(txtPrecoVenda, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Quantidade:"), gbc);
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        add(txtQuantidade, gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 1;
        add(new JLabel("Categoria:"), gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        add(txtCategoria, gbc);

        comboBoxTipoProduto = new JComboBox<>(new String[]{"Eletrônico", "Alimentício", "Limpeza"});
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(new JLabel("Tipo de Produto:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 4;
        add(comboBoxTipoProduto, gbc);

        // Adiciona botão para adicionar produto
        btnAdicionarProduto = new JButton("Adicionar Produto");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        add(btnAdicionarProduto, gbc);
        btnAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoModel produtoSelecionado = (ProdutoModel) comboBoxProdutos.getSelectedItem();
                
                if (produtoSelecionado != null) {
                    try {
                        String nome = txtProdutoNome.getText().trim();
                        String descricao = txtProdutoDescricao.getText().trim();
                        String precoVendaStr = txtPrecoVenda.getText().trim();
                        String quantidadeStr = txtQuantidade.getText().trim();
                        String categoria = txtCategoria.getText().trim();

                        if (nome.isEmpty() || descricao.isEmpty() || precoVendaStr.isEmpty() || quantidadeStr.isEmpty() || categoria.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        double precoVenda = Double.parseDouble(precoVendaStr);
                        int quantidade = Integer.parseInt(quantidadeStr);

                        vendaModel.adicionarProduto(produtoSelecionado, quantidade);
                        atualizarTextoVenda();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Preço de venda e quantidade devem ser números válidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        add(new JLabel("Desconto:"), gbc);
        gbc.gridx = 4;
        gbc.gridwidth = 1;
        add(txtDesconto, gbc);

        // Adiciona botão para aplicar desconto
        btnAplicarDesconto = new JButton("Aplicar Desconto");
        gbc.gridx = 5;
        gbc.gridy = 6;
        add(btnAplicarDesconto, gbc);
        btnAplicarDesconto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double desconto = Double.parseDouble(txtDesconto.getText());
                vendaModel.aplicarDesconto(desconto);
                atualizarTextoVenda();
            }
        });

        // Adiciona JTextArea para exibir detalhes da venda
        txtAreaVenda = new JTextArea(15, 50);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 6;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(txtAreaVenda), gbc);

        // Adiciona JComboBox para selecionar a forma de pagamento
        JComboBox<String> comboBoxFormaPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cartão", "Cheque"});
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Forma de Pagamento:"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 4;
        add(comboBoxFormaPagamento, gbc);

        // Define a forma de pagamento de acordo com a seleção do usuário
        comboBoxFormaPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formaSelecionada = (String) comboBoxFormaPagamento.getSelectedItem();
                if (formaSelecionada.equals("Dinheiro")) {
                    formaPagamentoPadrao = new FormaPagamentoDinheiroModel();
                } else if (formaSelecionada.equals("Cartão")) {
                    formaPagamentoPadrao = new FormaPagamentoCartaoModel();
                } else if (formaSelecionada.equals("Cheque")) {
                    formaPagamentoPadrao = new FormaPagamentoChequeModel();
                }
            }
        });

        formaPagamentoPadrao = new FormaPagamentoDinheiroModel();
        
        // Adiciona botão para finalizar venda
        btnFinalizarVenda = new JButton("Finalizar Venda");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 6;
        add(btnFinalizarVenda, gbc);
        btnFinalizarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendaModel.finalizarVenda();
                JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
                limparCampos();
            }
        });        

        setVisible(true);
    }

    private void carregarProdutos() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<ProdutoModel> produtos = produtoDAO.buscarTodosProdutos();
            comboBoxProdutos.removeAllItems(); // Limpa os itens existentes antes de adicionar novos
            for (ProdutoModel produto : produtos) {
                comboBoxProdutos.addItem(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarTextoVenda() {
        txtAreaVenda.setText("");
        for (VendaModel.ProdutoQuantidade pq : vendaModel.getProdutos()) {
            txtAreaVenda.append(pq.getProduto().getNome() + " - Quantidade: " + pq.getQuantidade() + "\n");
        }
        txtAreaVenda.append("Valor Total: " + vendaModel.calcularValorTotal());
    }

    private void limparCampos() {
        txtProdutoNome.setText("");
        txtProdutoDescricao.setText("");
        txtPrecoVenda.setText("");
        txtQuantidade.setText("");
        txtCategoria.setText("");
        txtDesconto.setText("");
        txtAreaVenda.setText("");
        vendaModel = new VendaModel(new ClienteModel(), formaPagamentoPadrao);  // Reinicializa a venda com a forma de pagamento selecionada
    }

    public static void main(String[] args) {
        new VendaView();
    }
}
