package Model;

import DAO.VendaDAO;

import java.util.ArrayList;
import java.util.List;

public class VendaModel {
    private ClienteModel cliente;
    private FormaPagamentoModel formaPagamento;
    private double valorTotal;
    private List<ProdutoQuantidade> produtos;
    private int codigoFormaPagamento;

    public VendaModel(ClienteModel cliente, FormaPagamentoModel formaPagamento) {
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.valorTotal = 0.0;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(ProdutoModel produto, int quantidade) {
        produtos.add(new ProdutoQuantidade(produto, quantidade));
        valorTotal += produto.getPrecoVenda() * quantidade;
    }

    public double calcularValorTotal() {
        return valorTotal;
    }

    public void aplicarDesconto(double desconto) {
        valorTotal -= desconto;
    }

    public void finalizarVenda() {
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.registrarVenda(this);
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public FormaPagamentoModel getFormaPagamento() {
        return formaPagamento;
    }

    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }

    public void setCodigoFormaPagamento(int codigoFormaPagamento) {
        this.codigoFormaPagamento = codigoFormaPagamento;
    }

    public List<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public static class ProdutoQuantidade {
        private ProdutoModel produto;
        private int quantidade;

        public ProdutoQuantidade(ProdutoModel produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public ProdutoModel getProduto() {
            return produto;
        }

        public int getQuantidade() {
            return quantidade;
        }
    }
}
