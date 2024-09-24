package bigdata.ap1.ap1.service;

import bigdata.ap1.ap1.model.Cliente;
import bigdata.ap1.ap1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para autorizar uma transação
    public boolean autorizarTransacao(Long clienteId, double valorTransacao) {
        // Buscar o cliente pelo ID
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Verificar se o cliente tem um cartão de crédito associado
        if (cliente.getCartaoCredito() == null || cliente.getCartaoCredito().isEmpty()) {
            throw new RuntimeException("Cliente não tem cartão de crédito associado");
        }

        // Exemplo de regra de negócio: autorizar transação se valor for menor que R$ 1000
        if (valorTransacao > 1000) {
            throw new RuntimeException("Transação não autorizada: valor acima do limite permitido");
        }

        // Se passou nas verificações, a transação é autorizada
        return true;
    }
}
