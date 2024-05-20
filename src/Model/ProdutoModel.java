package Model;

public abstract class ProdutoModel {
    protected int codigo;
    protected String nome;
    protected String descricao;
    protected double precoCusto;
    protected double precoVenda;
    protected int quantidadeEstoque;
    protected String categoria;
    
    public ProdutoModel(int codigo, String nome, String descricao, double precoCusto, double precoVenda,
            int quantidadeEstoque, String categoria) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
    this.precoCusto = precoCusto;
    this.precoVenda = precoVenda;
    this.quantidadeEstoque = quantidadeEstoque;
    this.categoria = categoria;
    }
    
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public abstract String getTipoProduto();
    
    @Override
    public String toString() {
        return this.nome;
    }   
}