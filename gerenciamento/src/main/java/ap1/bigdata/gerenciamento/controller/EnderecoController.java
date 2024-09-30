package ap1.bigdata.gerenciamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ap1.bigdata.gerenciamento.model.Endereco;
import ap1.bigdata.gerenciamento.services.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> create(@Valid @RequestBody Endereco endereco) {
        Endereco savedEndereco = enderecoService.save(endereco);
        return ResponseEntity.ok(savedEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> enderecos = enderecoService.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
