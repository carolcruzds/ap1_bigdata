package ap1.bigdata.gerenciamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap1.bigdata.gerenciamento.model.Endereco;
import ap1.bigdata.gerenciamento.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }
}
