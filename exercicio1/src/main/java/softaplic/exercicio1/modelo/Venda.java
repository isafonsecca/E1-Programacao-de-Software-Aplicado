package softaplic.exercicio1.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    private BigDecimal subtotal;
    private BigDecimal valorImposto;
    private BigDecimal valorTotal;

    public Venda(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public BigDecimal calcularSubtotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : itens) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    public void finalizar(BigDecimal subtotal, BigDecimal valorImposto, BigDecimal valorTotal) {
        this.subtotal = subtotal;
        this.valorImposto = valorImposto;
        this.valorTotal = valorTotal;
    }
}
