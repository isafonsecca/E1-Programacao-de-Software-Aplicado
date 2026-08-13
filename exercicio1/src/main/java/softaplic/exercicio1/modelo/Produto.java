package softaplic.exercicio1.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class Produto {

    @Id
    private String codigo;

    @NotBlank
    private String descricao;

    @PositiveOrZero
    private int quantidadeEstoque;

    @NotNull
    private BigDecimal precoUnitario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public void baixarEstoque(int quantidade) {
        if (quantidade > quantidadeEstoque) {
            throw new IllegalStateException("Estoque insuficiente para o produto " + codigo);
        }
        quantidadeEstoque -= quantidade;
    }
}
