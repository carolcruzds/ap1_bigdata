package ap1.bigdata.gerenciamento.controller;

import ap1.bigdata.gerenciamento.model.Endereco;
import ap1.bigdata.gerenciamento.repository.EnderecoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Criar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    // Buscar todos os endereços
    @GetMapping
    public List<Endereco> listarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return ResponseEntity.ok(endereco.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Atualizar endereço por ID
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Endereco enderecoAtualizado) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setCliente(enderecoAtualizado.getCliente());

            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            return ResponseEntity.ok(enderecoSalvo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            enderecoRepository.delete(endereco.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
