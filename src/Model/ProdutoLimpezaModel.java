package Model;

public class ProdutoLimpezaModel extends ProdutoModel {
    public ProdutoLimpezaModel(int codigo, String nome, String descricao, double precoCusto, double precoVenda,
                               int quantidadeEstoque, String categoria) {
        super(codigo, nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria);
    }

    @Override
    public String getTipoProduto() {
        return "Limpeza";
    }
}
