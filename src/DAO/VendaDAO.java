package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.Conexao;
import Model.VendaModel;

public class VendaDAO {
    public void registrarVenda(VendaModel venda) {
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = Conexao.obterConexao();
            String sqlVenda = "INSERT INTO venda (cliente_codigo, forma_pagamento, valor_total) VALUES (?, ?, ?)";
            pstmt = conexao.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, venda.getCliente().getCodigo());
            pstmt.setInt(2, venda.getCodigoFormaPagamento());
            pstmt.setDouble(3, venda.calcularValorTotal());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int codigoVenda = -1;
            if (rs.next()) {
                codigoVenda = rs.getInt(1);
            }

            String sqlProduto = "INSERT INTO venda_produto (venda_codigo, produto_codigo, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            pstmt = conexao.prepareStatement(sqlProduto);
            for (VendaModel.ProdutoQuantidade item : venda.getProdutos()) {
                pstmt.setInt(1, codigoVenda);
                pstmt.setInt(2, item.getProduto().getCodigo()); 
                pstmt.setInt(3, item.getQuantidade()); 
                pstmt.setDouble(4, item.getProduto().getPrecoVenda());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratamento adequado da exceção
        } finally {
            fecharRecursos(pstmt, conexao);
        }
    }

    private void fecharRecursos(PreparedStatement pstmt, Connection conexao) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
