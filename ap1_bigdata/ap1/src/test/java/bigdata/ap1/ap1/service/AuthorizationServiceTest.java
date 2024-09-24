package bigdata.ap1.ap1.service;

import bigdata.ap1.ap1.model.Cliente;
import bigdata.ap1.ap1.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setEmail("joao@example.com");
        cliente.setCartaoCredito("1234-5678-9876-5432");
    }

    @Test
    void autorizarTransacao_comSucesso() {
        // Mock para simular que o cliente existe e tem cartão de crédito associado
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.of(cliente));

        // Executa o método com um valor de transação dentro do limite
        boolean resultado = authorizationService.autorizarTransacao(cliente.getId(), 500.0);

        // Verificações
        assertTrue(resultado);
    }

    @Test
    void autorizarTransacao_clienteSemCartao() {
        // Simula que o cliente não tem cartão de crédito
        cliente.setCartaoCredito(null);
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.of(cliente));

        // Executa e verifica se a exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authorizationService.autorizarTransacao(cliente.getId(), 500.0);
        });

        assertEquals("Cliente não tem cartão de crédito associado", exception.getMessage());
    }

    @Test
    void autorizarTransacao_valorAcimaDoLimite() {
        // Mock para simular que o cliente existe e tem cartão de crédito
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.of(cliente));

        // Executa o método com um valor de transação acima do limite
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authorizationService.autorizarTransacao(cliente.getId(), 1500.0);
        });

        assertEquals("Transação não autorizada: valor acima do limite permitido", exception.getMessage());
    }

    @Test
    void autorizarTransacao_clienteNaoEncontrado() {
        // Mock para simular que o cliente não existe
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.empty());

        // Executa e verifica se a exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authorizationService.autorizarTransacao(cliente.getId(), 500.0);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }
}
