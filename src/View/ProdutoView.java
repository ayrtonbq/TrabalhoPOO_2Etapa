package View;

import javax.swing.*;

import DAO.ProdutoDAO;
import Model.ProdutoAlimenticioModel;
import Model.ProdutoModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProdutoView extends JFrame implements ActionListener {
    private JTextField codigoField, nomeField, descricaoField, precoCustoField, precoVendaField, quantidadeField, categoriaField;
    private JTextField codigoField2, nomeField2, descricaoField2, precoCustoField2, precoVendaField2, quantidadeField2, categoriaField2;
    private JButton cadastrarButton, consultarButton, alterarButton, deletarButton;
    private JTextArea resultadoArea;
    private JComboBox<String> categoriaComboBox;
    private JComboBox<String> categoriaComboBox2;

    private ProdutoDAO produtoDAO;

    public ProdutoView(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;

        setTitle("Cadastro e Consulta de Produtos");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        criarPainelCadastro();
        criarPainelConsulta();

        setVisible(true);
    }

    private void criarPainelCadastro() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 410, 280);
        panel.setBorder(BorderFactory.createTitledBorder("Cadastro de Produto"));
        panel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(10, 20, 100, 20);
        panel.add(lblCodigo);
        codigoField = new JTextField();
        codigoField.setBounds(150, 20, 150, 20);
        panel.add(codigoField);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 50, 100, 20);
        panel.add(lblNome);
        nomeField = new JTextField();
        nomeField.setBounds(150, 50, 150, 20);
        panel.add(nomeField);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 80, 100, 20);
        panel.add(lblDescricao);
        descricaoField = new JTextField();
        descricaoField.setBounds(150, 80, 150, 20);
        panel.add(descricaoField);

        JLabel lblPrecoCusto = new JLabel("Preço de Custo:");
        lblPrecoCusto.setBounds(10, 110, 120, 20);
        panel.add(lblPrecoCusto);
        precoCustoField = new JTextField();
        precoCustoField.setBounds(150, 110, 150, 20);
        panel.add(precoCustoField);

        JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
        lblPrecoVenda.setBounds(10, 140, 120, 20);
        panel.add(lblPrecoVenda);
        precoVendaField = new JTextField();
        precoVendaField.setBounds(150, 140, 150, 20);
        panel.add(precoVendaField);

        JLabel lblQuantidade = new JLabel("Quantidade em Estoque:");
        lblQuantidade.setBounds(10, 170, 170, 20);
        panel.add(lblQuantidade);
        quantidadeField = new JTextField();
        quantidadeField.setBounds(150, 170, 150, 20);
        panel.add(quantidadeField);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(10, 200, 100, 20);
        panel.add(lblCategoria);
        
        String[] categorias = {"Alimentício", "Eletrônico", "Limpeza"};
        //JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);
        categoriaComboBox = new JComboBox<>(categorias);
        categoriaComboBox.setBounds(150, 200, 150, 20);
        panel.add(categoriaComboBox);
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(140, 230, 150, 30);
        cadastrarButton.addActionListener(this);
        panel.add(cadastrarButton);

        getContentPane().add(panel);
    }

    private void criarPainelConsulta() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 300, 410, 280);
        panel.setBorder(BorderFactory.createTitledBorder("Consulta de Produto"));
        panel.setLayout(null);

        JLabel lblConsultaCodigo = new JLabel("Código:");
        lblConsultaCodigo.setBounds(10, 20, 100, 20);
        panel.add(lblConsultaCodigo);
        codigoField2 = new JTextField();
        codigoField2.setBounds(150, 20, 150, 20);
        panel.add(codigoField2);

        consultarButton = new JButton("Consultar"); 
        consultarButton.setBounds(300, 20, 100, 20);
        consultarButton.addActionListener(this);
        panel.add(consultarButton); 

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 50, 100, 20);
        panel.add(lblNome);
        nomeField2 = new JTextField();
        nomeField2.setBounds(150, 50, 250, 20);
        //nomeField2.setEditable(false);
        panel.add(nomeField2);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 80, 100, 20);
        panel.add(lblDescricao);
        descricaoField2 = new JTextField();
        descricaoField2.setBounds(150, 80, 250, 20);
        //descricaoField2.setEditable(false);
        panel.add(descricaoField2);

        JLabel lblPrecoCusto = new JLabel("Preço de Custo:");
        lblPrecoCusto.setBounds(10, 110, 120, 20);
        panel.add(lblPrecoCusto);
        precoCustoField2 = new JTextField();
        precoCustoField2.setBounds(150, 110, 150, 20);
        //precoCustoField2.setEditable(false);
        panel.add(precoCustoField2);

        JLabel lblPrecoVenda = new JLabel("Preço de Venda:");
        lblPrecoVenda.setBounds(10, 140, 120, 20);
        panel.add(lblPrecoVenda);
        precoVendaField2 = new JTextField();
        precoVendaField2.setBounds(150, 140, 150, 20);
        //precoVendaField2.setEditable(false);
        panel.add(precoVendaField2);

        JLabel lblQuantidade = new JLabel("Quantidade em Estoque:");
        lblQuantidade.setBounds(10, 170, 170, 20);
        panel.add(lblQuantidade);
        quantidadeField2 = new JTextField();
        quantidadeField2.setBounds(150, 170, 150, 20);
        //quantidadeField2.setEditable(false);
        panel.add(quantidadeField2);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(10, 200, 100, 20);
        panel.add(lblCategoria);
        
        String[] categorias = {"Alimentício", "Eletrônico", "Limpeza"};
        categoriaComboBox2 = new JComboBox<>(categorias);
        categoriaComboBox2.setBounds(150, 200, 150, 20);
        panel.add(categoriaComboBox2);
        
        deletarButton = new JButton("Deletar");
        deletarButton.setBounds(120, 230, 100, 20);
        deletarButton.addActionListener(this);
        panel.add(deletarButton);

        alterarButton = new JButton("Alterar");
        alterarButton.setBounds(280, 230, 100, 20);
        alterarButton.addActionListener(this);
        panel.add(alterarButton);        

        getContentPane().add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrarButton) {
            cadastrarProduto();
        } else if (e.getSource() == consultarButton) {
            consultarProduto();
        } else if (e.getSource() == deletarButton) {
            deletarProduto();
        } else if (e.getSource() == alterarButton) {
            alterarProduto();
        }
    }

    private void cadastrarProduto() {
        // Verificar se os campos obrigatórios não estão vazios
        if (codigoField.getText().isEmpty() || nomeField.getText().isEmpty() || descricaoField.getText().isEmpty() ||
            precoCustoField.getText().isEmpty() || precoVendaField.getText().isEmpty() || quantidadeField.getText().isEmpty() ||
            categoriaComboBox.getSelectedItem() == null) { // Verifique se o item selecionado não é nulo
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            double precoCusto = Double.parseDouble(precoCustoField.getText());
            double precoVenda = Double.parseDouble(precoVendaField.getText());
            int quantidadeEstoque = Integer.parseInt(quantidadeField.getText());
            String categoria = (String) categoriaComboBox.getSelectedItem(); // Obtenha o item selecionado

            ProdutoAlimenticioModel produto = new ProdutoAlimenticioModel(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
            produtoDAO.salvarProduto(produto);
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void consultarProduto() {
        int codigo = Integer.parseInt(codigoField2.getText());
        try {
            ProdutoModel produto = produtoDAO.consultarProdutoPorCodigo(codigo);
            if (produto != null) {
                codigoField2.setText(Integer.toString(produto.getCodigo()));
                nomeField2.setText(produto.getNome());
                descricaoField2.setText(produto.getDescricao());
                precoCustoField2.setText(Double.toString(produto.getPrecoCusto()));
                precoVendaField2.setText(Double.toString(produto.getPrecoVenda()));
                quantidadeField2.setText(Integer.toString(produto.getQuantidadeEstoque()));

                // Recuperar a categoria do produto
                String categoria = produto.getCategoria();

                // Preencher o JComboBox apenas com a categoria do produto
                String[] categorias = {categoria};
                categoriaComboBox2.setModel(new DefaultComboBoxModel<>(categorias));

                // Selecionar automaticamente a categoria do produto no JComboBox
                categoriaComboBox2.setSelectedItem(categoria);
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um código válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void deletarProduto() {
        int codigo = Integer.parseInt(codigoField2.getText());
        try {
            boolean sucesso = produtoDAO.deletarProdutoPorCodigo(codigo);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto deletado com sucesso.");
                // Limpar os campos após a exclusão
                nomeField2.setText("");
                descricaoField2.setText("");
                precoCustoField2.setText("");
                precoVendaField2.setText("");
                quantidadeField2.setText("");
                categoriaField2.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível deletar o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

 
    private void alterarProduto() {
        int codigo = Integer.parseInt(codigoField2.getText());
        try {
            String nome = nomeField2.getText();
            String descricao = descricaoField2.getText();
            double precoCusto = Double.parseDouble(precoCustoField2.getText());
            double precoVenda = Double.parseDouble(precoVendaField2.getText());
            int quantidadeEstoque = Integer.parseInt(quantidadeField2.getText());
            String categoria = (String) categoriaComboBox2.getSelectedItem(); // Obter a categoria selecionada

            ProdutoAlimenticioModel produto = new ProdutoAlimenticioModel(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
            
            boolean sucesso = produtoDAO.alterarProduto(produto);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível alterar o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoDAO produtoDAO = new ProdutoDAO(); 
            new ProdutoView(produtoDAO);
        });
    }
}
