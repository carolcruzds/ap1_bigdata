package bigdata.ap1.ap1.controller;

import bigdata.ap1.ap1.model.Cliente;
import bigdata.ap1.ap1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ControllerCliente {

    @Autowired
    private UserService userService;

    // Endpoint para criar uma nova conta de cliente
    @PostMapping("/criar")
    public ResponseEntity<Cliente> criarConta(@RequestBody Cliente cliente) {
        Cliente novoCliente = userService.criarConta(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    // Endpoint para associar um cartão de crédito ao cliente
    @PostMapping("/{id}/associar-cartao")
    public ResponseEntity<Cliente> associarCartao(@PathVariable Long id, @RequestBody String numeroCartao) {
        Cliente clienteExistente = userService.associarCartaoCredito(new Cliente(id), numeroCartao);
        return ResponseEntity.ok(clienteExistente);
    }
}
