package Controller;

import Model.ClienteModel;
import Model.FormaPagamentoModel;
import Model.VendaModel;

public class VendaController {
    public void criarVenda(ClienteModel cliente, FormaPagamentoModel formaPagamento) {
        VendaModel venda = new VendaModel(cliente, formaPagamento);
    }
}