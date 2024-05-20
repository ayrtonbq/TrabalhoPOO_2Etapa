package View;

import javax.swing.*;

import Controller.Conexao;
import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import Model.ClienteModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;


public class ClienteView extends JFrame implements ActionListener {
    private JTextField codigoField, nomeField, cpfField, dataNascimentoField, emailField;
    private JTextField codigoField2, nomeField2, cpfField2, dataNascimentoField2, emailField2;
    private JButton cadastrarButton, consultarButton, alterarButton, deletarButton;
    private JTextArea resultadoArea;

    private ClienteDAO clienteDAO;

    public ClienteView(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;

        setTitle("Cadastro e Consulta de Clientes");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        criarPainelCadastro();
        criarPainelConsulta();

        setVisible(true);
    }

    private void criarPainelCadastro() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 410, 250);
        panel.setBorder(BorderFactory.createTitledBorder("Cadastro de Cliente"));
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
        nomeField.setBounds(150, 50, 250, 20);
        panel.add(nomeField);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 80, 100, 20);
        panel.add(lblCpf);
        cpfField = new JTextField();
        cpfField.setBounds(150, 80, 150, 20);
        panel.add(cpfField);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        lblDataNascimento.setBounds(10, 110, 140, 20);
        panel.add(lblDataNascimento);
        dataNascimentoField = new JTextField();
        dataNascimentoField.setBounds(150, 110, 150, 20);
        panel.add(dataNascimentoField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 140, 100, 20);
        panel.add(lblEmail);
        emailField = new JTextField();
        emailField.setBounds(150, 140, 250, 20);
        panel.add(emailField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(140, 170, 150, 30);
        cadastrarButton.addActionListener(this);
        panel.add(cadastrarButton);

        getContentPane().add(panel);
    }

    private void criarPainelConsulta() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 260, 410, 280);
        panel.setBorder(BorderFactory.createTitledBorder("Consulta de Cliente"));
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

        JLabel lblDescricao = new JLabel("CPF:");
        lblDescricao.setBounds(10, 80, 100, 20);
        panel.add(lblDescricao);
        cpfField2 = new JTextField();
        cpfField2.setBounds(150, 80, 250, 20);
        //descricaoField2.setEditable(false);
        panel.add(cpfField2);

        JLabel lblPrecoCusto = new JLabel("Data de Nascimento:");
        lblPrecoCusto.setBounds(10, 110, 120, 20);
        panel.add(lblPrecoCusto);
        dataNascimentoField2 = new JTextField();
        dataNascimentoField2.setBounds(150, 110, 150, 20);
        //precoCustoField2.setEditable(false);
        panel.add(dataNascimentoField2);

        JLabel lblPrecoVenda = new JLabel("Email");
        lblPrecoVenda.setBounds(10, 140, 120, 20);
        panel.add(lblPrecoVenda);
        emailField2 = new JTextField();
        emailField2.setBounds(150, 140, 150, 20);
        //precoVendaField2.setEditable(false);
        panel.add(emailField2);
        
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
            cadastrarCliente();
        } else if (e.getSource() == consultarButton) {
            consultarCliente();
        } else if (e.getSource() == deletarButton) {
            deletarCliente();
        } else if (e.getSource() == alterarButton) {
            alterarCliente();
        }
    }

    private void cadastrarCliente() {
        // Verificar se os campos obrigatórios não estão vazios
        if (codigoField.getText().isEmpty() || nomeField.getText().isEmpty() || cpfField.getText().isEmpty() ||
            dataNascimentoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoField.getText());
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String dataNascimentoStr = dataNascimentoField.getText();
            String email = emailField.getText();

            // Converter a string de data de nascimento para um objeto Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = dateFormat.parse(dataNascimentoStr);

            // Crie um novo objeto ClienteModel
            ClienteModel cliente = new ClienteModel(codigo, nome, cpf, dataNascimento, email);

            // Chame o método para cadastrar o cliente no banco de dados
            clienteDAO.cadastrarCliente(cliente);

            // Exiba uma mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O código do cliente deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarCliente() {
        try {
            int codigo = Integer.parseInt(codigoField2.getText());
            ClienteModel cliente = clienteDAO.consultarCliente(codigo);
            if (cliente != null) {
                // Se o cliente for encontrado, preencha os campos de texto com suas informações
                nomeField2.setText(cliente.getNome());
                cpfField2.setText(cliente.getCpf());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimentoField2.setText(dateFormat.format(cliente.getDataNascimento()));
                emailField2.setText(cliente.getEmail());
            } else {
                // Se o cliente não for encontrado, limpe os campos de texto
                nomeField2.setText("");
                cpfField2.setText("");
                dataNascimentoField2.setText("");
                emailField2.setText("");
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um código de cliente válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void deletarCliente() {
        int codigo = Integer.parseInt(codigoField2.getText());
        try {
            boolean sucesso = clienteDAO.excluirCliente(codigo);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso.");
                // Limpar os campos após a exclusão
                nomeField2.setText("");
                cpfField2.setText("");
                dataNascimentoField2.setText("");
                emailField2.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível deletar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void alterarCliente() {
        int codigo = Integer.parseInt(codigoField2.getText());
        try {
            String nome = nomeField2.getText();
            String cpf = cpfField2.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = dateFormat.parse(dataNascimentoField2.getText());
            String email = emailField2.getText();

            ClienteModel cliente = new ClienteModel(codigo, nome, cpf, dataNascimento, email);
            
            // Chamar o método para alterar o cliente
            boolean sucesso = clienteDAO.atualizarCliente(cliente);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível alterar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteDAO clienteDAO = new ClienteDAO(); 
            new ClienteView(clienteDAO);
        });
    }

}
