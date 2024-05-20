package Model;

public class ChequeModel extends FormaPagamentoModel {
    @Override
    public void efetuarPagamento(double valorTotal) {
        // Implementação específica para pagamento com cheque
    }

    @Override
    public String getDescricao() {
        return "Pagamento com cheque";
    }
}