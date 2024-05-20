package Model;

public class CartaoModel extends FormaPagamentoModel {
    @Override
    public void efetuarPagamento(double valorTotal) {
        // Implementação específica para pagamento com cartão
    }

    @Override
    public String getDescricao() {
        return "Pagamento com cartão";
    }
}
