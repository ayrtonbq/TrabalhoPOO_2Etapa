package DAO;

import Model.ProdutoAlimenticioModel;
import Model.ProdutoEletronicoModel;
import Model.ProdutoLimpezaModel;
import Model.ProdutoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controller.Conexao;

public class ProdutoDAO {

    public int salvarProduto(ProdutoModel produto) throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        int idProdutoSalvo = -1;

        try {
            conexao = Conexao.obterConexao(); 
            String sql = "INSERT INTO produto (codigo, nome, descricao, preco_custo, preco_venda, quantidade_estoque, categoria) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, produto.getCodigo());
            pstmt.setString(2, produto.getNome());
            pstmt.setString(3, produto.getDescricao());
            pstmt.setDouble(4, produto.getPrecoCusto());
            pstmt.setDouble(5, produto.getPrecoVenda());
            pstmt.setInt(6, produto.getQuantidadeEstoque());
            pstmt.setString(7, produto.getCategoria());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idProdutoSalvo = rs.getInt(1);
            }
        } finally {
            fecharRecursos(pstmt, conexao);
        }

        return idProdutoSalvo;
    }

    public ProdutoModel consultarProdutoPorCodigo(int codigo) throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = Conexao.obterConexao();
            String sql = "SELECT * FROM produto WHERE codigo = ?";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, codigo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String categoria = rs.getString("categoria").toLowerCase();
                    ProdutoModel produto;

                    switch (categoria.toLowerCase()) {
                        case "alimentício":
                            produto = new ProdutoAlimenticioModel(
                                rs.getInt("codigo"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getDouble("preco_custo"),
                                rs.getDouble("preco_venda"),
                                rs.getInt("quantidade_estoque"),
                                categoria
                            );
                            break;
                        case "eletrônico":
                            produto = new ProdutoEletronicoModel(
                                rs.getInt("codigo"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getDouble("preco_custo"),
                                rs.getDouble("preco_venda"),
                                rs.getInt("quantidade_estoque"),
                                categoria
                            );
                            break;
                        case "limpeza":
                            produto = new ProdutoLimpezaModel(
                                rs.getInt("codigo"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getDouble("preco_custo"),
                                rs.getDouble("preco_venda"),
                                rs.getInt("quantidade_estoque"),
                                categoria
                            );
                            break;
                        default:
                            throw new SQLException("Categoria desconhecida: " + categoria);
                    }
                    return produto;
                } else {
                    return null;
                }
            }
        } finally {
            fecharRecursos(pstmt, conexao);
        }
    }

    public boolean deletarProdutoPorCodigo(int codigo) throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = Conexao.obterConexao();

            String verificaVendasSql = "SELECT COUNT(*) AS total_vendas FROM venda_produto WHERE produto_codigo = ?";
            pstmt = conexao.prepareStatement(verificaVendasSql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt("total_vendas") > 0) {
                return false;
            }

            String deleteProdutoSql = "DELETE FROM produto WHERE codigo = ?";
            pstmt = conexao.prepareStatement(deleteProdutoSql);
            pstmt.setInt(1, codigo);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } finally {
            fecharRecursos(pstmt, conexao);
        }
    }

    public boolean alterarProduto(ProdutoModel produto) throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = Conexao.obterConexao();

            String sql = "UPDATE produto SET nome = ?, descricao = ?, preco_custo = ?, preco_venda = ?, quantidade_estoque = ?, categoria = ? WHERE codigo = ?";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPrecoCusto());
            pstmt.setDouble(4, produto.getPrecoVenda());
            pstmt.setInt(5, produto.getQuantidadeEstoque());
            pstmt.setString(6, produto.getCategoria());
            pstmt.setInt(7, produto.getCodigo());

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } finally {
            fecharRecursos(pstmt, conexao);
        }
    }

    public List<ProdutoModel> buscarTodosProdutos() throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProdutoModel> produtos = new ArrayList<>();
        try {
            conexao = Conexao.obterConexao();
            pstmt = conexao.prepareStatement("SELECT * FROM produto");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double precoCusto = rs.getDouble("preco_custo");
                double precoVenda = rs.getDouble("preco_venda");
                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                String categoria = rs.getString("categoria");

                ProdutoModel produto;
                switch (categoria.toLowerCase()) {
                    case "alimentício":
                        produto = new ProdutoAlimenticioModel(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                        break;
                    case "eletrônico":
                        produto = new ProdutoEletronicoModel(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                        break;
                    case "limpeza":
                        produto = new ProdutoLimpezaModel(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
                        break;
                    default:
                        throw new SQLException("Categoria desconhecida: " + categoria);
                }
                produtos.add(produto);
            }
        } finally {
            fecharRecursos(pstmt, conexao);
            if (rs != null) {
                rs.close();
            }
        }
        return produtos;
    }
    
    public List<String> listarRelatorioEstoque() throws SQLException {
        List<String> relatorio = new ArrayList<>();
        List<ProdutoModel> produtos = buscarTodosProdutos();

        for (ProdutoModel produto : produtos) {
            relatorio.add("Produto: " + produto.getNome() + " - Quantidade: " + produto.getQuantidadeEstoque());
        }

        return relatorio;
    }    

    private void fecharRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
