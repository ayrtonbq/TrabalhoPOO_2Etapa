package Model;

public class FormaPagamentoChequeModel extends FormaPagamentoModel {
    @Override
    public void efetuarPagamento(double valorTotal) {
        System.out.println("Pagamento com cheque no valor de R$" + valorTotal);
    }

    @Override
    public String getDescricao() {
        return "Pagamento com cheque";
    }
}
