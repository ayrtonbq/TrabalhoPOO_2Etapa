package Model;

public class FormaPagamentoDinheiroModel extends FormaPagamentoModel {
    @Override
    public void efetuarPagamento(double valorTotal) {
        System.out.println("Pagamento em dinheiro no valor de R$" + valorTotal);
    }

    @Override
    public String getDescricao() {
        return "Pagamento em dinheiro";
    }
}