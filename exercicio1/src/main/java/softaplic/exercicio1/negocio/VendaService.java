package softaplic.exercicio1.negocio;

import softaplic.exercicio1.modelo.ItemVenda;
import softaplic.exercicio1.modelo.Produto;
import softaplic.exercicio1.modelo.Usuario;
import softaplic.exercicio1.modelo.Venda;
import softaplic.exercicio1.persistencia.ProdutoRepository;
import softaplic.exercicio1.persistencia.UsuarioRepository;
import softaplic.exercicio1.persistencia.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class VendaService {

    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;
    private VendaRepository vendaRepository;
    private FabricaCalculadoraImposto fabricaCalculadoraImposto;

    public VendaService(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
                         VendaRepository vendaRepository, FabricaCalculadoraImposto fabricaCalculadoraImposto) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.vendaRepository = vendaRepository;
        this.fabricaCalculadoraImposto = fabricaCalculadoraImposto;
    }

    @Transactional
    public Venda realizarVenda(String usuarioId, Map<String, Integer> quantidadesPorCodigoProduto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado: " + usuarioId));

        Venda venda = new Venda(usuario);

        for (Map.Entry<String, Integer> entrada : quantidadesPorCodigoProduto.entrySet()) {
            Produto produto = produtoRepository.findById(entrada.getKey())
                    .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado: " + entrada.getKey()));
            venda.adicionarItem(new ItemVenda(produto, entrada.getValue()));
        }

        BigDecimal subtotal = venda.calcularSubtotal();
        BigDecimal impostoTotal = BigDecimal.ZERO;

        for (ItemVenda item : venda.getItens()) {
            impostoTotal = impostoTotal.add(calcularImpostoDoItem(item, usuario));
        }

        BigDecimal total = subtotal.add(impostoTotal).setScale(2, RoundingMode.HALF_UP);
        venda.finalizar(subtotal, impostoTotal, total);

        for (ItemVenda item : venda.getItens()) {
            item.getProduto().baixarEstoque(item.getQuantidade());
            produtoRepository.save(item.getProduto());
        }

        return vendaRepository.save(venda);
    }

    private BigDecimal calcularImpostoDoItem(ItemVenda item, Usuario usuario) {
        CalculadoraImposto calculadora = fabricaCalculadoraImposto.obterCalculadora(item.getProduto().getCategoria());
        BigDecimal imposto = calculadora.calcular(item.getSubtotal());

        if (calculadora.isBebidaAlcoolica()) {
            return imposto;
        }
        if (usuario.getIdade() > 60) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        if (usuario.getNumeroDependentes() > 3) {
            return imposto.multiply(BigDecimal.valueOf(0.5)).setScale(2, RoundingMode.HALF_UP);
        }
        return imposto;
    }
}
