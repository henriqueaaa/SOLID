package ocp;

public class PagamentoPix implements FormaPagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagando R$ " + valor + " via pix.");
    }
}