package softaplic.exercicio1.negocio;

import softaplic.exercicio1.modelo.CategoriaProduto;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class FabricaCalculadoraImposto {

    private Map<CategoriaProduto, CalculadoraImposto> calculadoras = new EnumMap<>(CategoriaProduto.class);

    public FabricaCalculadoraImposto(ImpostoAlimenticio impostoAlimenticio,
                                      ImpostoAutomotivo impostoAutomotivo,
                                      ImpostoBebidaAlcoolica impostoBebidaAlcoolica,
                                      ImpostoPadrao impostoPadrao) {
        calculadoras.put(CategoriaProduto.ALIMENTICIO, impostoAlimenticio);
        calculadoras.put(CategoriaProduto.AUTOMOTIVO, impostoAutomotivo);
        calculadoras.put(CategoriaProduto.BEBIDA_ALCOOLICA, impostoBebidaAlcoolica);
        calculadoras.put(CategoriaProduto.OUTROS, impostoPadrao);
    }

    public CalculadoraImposto obterCalculadora(CategoriaProduto categoria) {
        return calculadoras.get(categoria);
    }
}
