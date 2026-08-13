package softaplic.exercicio1.negocio;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ImpostoAlimenticio implements CalculadoraImposto {

    @Override
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal.multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean isBebidaAlcoolica() {
        return false;
    }
}
