package bigdata.ap1.ap1.repository;

import bigdata.ap1.ap1.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar consultas personalizadas, se necessário

    // Exemplo de método customizado: Buscar cliente pelo email
    Cliente findByEmail(String email);
}
