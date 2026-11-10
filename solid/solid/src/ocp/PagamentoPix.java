package ocp;

public class PagamentoPix implements FormaPagamento {
    public PagamentoPix(String mail) {
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagando R$ " + valor + " via pix.");
    }
}