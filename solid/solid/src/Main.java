import dip.Email;
import dip.NotificacaoService;
import dip.Notificador;
import isp.*;
import lsp.*;
import ocp.FormaPagamento;
import ocp.PagamentoPix;
import ocp.ProcessadorPagamento;
import srp.Pedido;
import srp.PedidoRepository;
import srp.PedidoService;
import srp.PedidoView;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // SRP: criar, salvar e exibir o pedido
        PedidoService service = new PedidoService();
        PedidoRepository repository = new PedidoRepository();
        PedidoView view = new PedidoView();

        Pedido pedido = service.criar("Maria", List.of("Pizza", "Guaraná"));
        repository.salvar(pedido);
        view.exibir(pedido);

        // OCP: pagamento
        FormaPagamento forma = new PagamentoPix("maria@email.com");
        new ProcessadorPagamento().processar(forma, 50.0);

        // LSP: produtos tratados como Produto
        List<Produto> produtos = List.of(
                new Pizza("Calabresa", 40.0, true),
                new Hamburguer("X-Bacon", 25.0, 2),
                new Bebida("Guaraná", 10.0)
        );
        for (Produto p : produtos) {
            System.out.println(p.getNome() + ": R$ " + p.calcularPreco());
        }

        // ISP: entrega e relatório
        GerenciadorEntregas entregas = new GestaoEntregas();
        entregas.entregar(pedido, "João");

        GeradorRelatorio relatorio = new RelatorioPedidos(repository);
        relatorio.gerarRelatorio();

        // DIP: notificação
        Notificador notificador = new Email();
        new NotificacaoService(notificador).avisarPedido(pedido, "maria@email.com");
    }
}