package ocp;

public class ProcessadorPagamento {
    public void Processar (FormaPagamento forma, double valor){
        forma.pagar(valor);
    }
}
