package bigdata.ap1.ap1.controller;

import bigdata.ap1.ap1.model.Endereco;
import bigdata.ap1.ap1.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class ControllerEndereco {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Endpoint para criar ou adicionar um endereço
    @PostMapping("/criar")
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoRepository.save(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    // Endpoint para obter um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterEndereco(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
