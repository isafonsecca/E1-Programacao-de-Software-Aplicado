package softaplic.exercicio1.negocio;

import java.math.BigDecimal;

public interface CalculadoraImposto {

    BigDecimal calcular(BigDecimal subtotal);

    boolean isBebidaAlcoolica();
}
