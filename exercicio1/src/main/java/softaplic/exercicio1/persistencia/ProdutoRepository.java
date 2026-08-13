package softaplic.exercicio1.persistencia;

import softaplic.exercicio1.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
