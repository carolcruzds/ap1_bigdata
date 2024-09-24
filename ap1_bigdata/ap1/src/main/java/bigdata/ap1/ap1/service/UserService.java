package bigdata.ap1.ap1.service;

import bigdata.ap1.ap1.model.Cliente;
import bigdata.ap1.ap1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para criar uma nova conta de cliente
    public Cliente criarConta(Cliente cliente) {
        // Verificar se o cliente já existe (baseado no email, por exemplo)
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            throw new RuntimeException("Cliente já existe com este email");
        }

        // Salvar o cliente no banco de dados
        return clienteRepository.save(cliente);
    }

    // Método para associar um cartão de crédito ao cliente
    public Cliente associarCartaoCredito(Cliente cliente, String numeroCartao) {
        // Buscar o cliente pelo ID
        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Associar o número do cartão de crédito ao cliente
        clienteExistente.setCartaoCredito(numeroCartao);

        // Atualizar o cliente no banco de dados
        return clienteRepository.save(clienteExistente);
    }
}
