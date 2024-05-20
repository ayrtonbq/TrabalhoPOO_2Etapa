package Model;

public abstract class FormaPagamentoModel {
    public abstract void efetuarPagamento(double valorTotal);
    public abstract String getDescricao();
}