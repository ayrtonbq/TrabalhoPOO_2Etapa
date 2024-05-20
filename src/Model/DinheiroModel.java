package Model;

public class DinheiroModel extends FormaPagamentoModel {
    @Override
    public void efetuarPagamento(double valorTotal) {
    }

    @Override
    public String getDescricao() {
        return "Pagamento em dinheiro";
    }
}