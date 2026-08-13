package softaplic.exercicio1.persistencia;

import softaplic.exercicio1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
