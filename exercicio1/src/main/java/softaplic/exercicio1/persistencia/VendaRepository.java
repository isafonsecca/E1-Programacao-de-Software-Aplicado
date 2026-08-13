package softaplic.exercicio1.persistencia;

import softaplic.exercicio1.modelo.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
