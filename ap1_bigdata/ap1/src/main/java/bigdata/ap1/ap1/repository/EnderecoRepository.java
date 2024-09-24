package bigdata.ap1.ap1.repository;

import bigdata.ap1.ap1.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário
}
