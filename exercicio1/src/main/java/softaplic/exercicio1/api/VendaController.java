package softaplic.exercicio1.api;

import softaplic.exercicio1.modelo.Venda;
import softaplic.exercicio1.negocio.VendaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/vendas")
    public Venda registrarVenda(@Valid @RequestBody VendaRequest request) {
        return vendaService.realizarVenda(request.getUsuarioId(), request.getItens());
    }
}
