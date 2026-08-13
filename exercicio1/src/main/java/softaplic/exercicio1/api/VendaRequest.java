package softaplic.exercicio1.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class VendaRequest {

    @NotNull
    private String usuarioId;

    @NotEmpty
    private Map<String, Integer> itens;
}
