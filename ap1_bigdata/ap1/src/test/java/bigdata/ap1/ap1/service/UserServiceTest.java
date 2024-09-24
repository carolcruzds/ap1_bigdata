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

class UserServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private UserService userService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setEmail("joao@example.com");
    }

    @Test
    void criarConta_comSucesso() {
        // Mock para simular que o cliente ainda não existe no banco
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(null);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Executa o método
        Cliente clienteCriado = userService.criarConta(cliente);

        // Verificações
        assertNotNull(clienteCriado);
        assertEquals(cliente.getNome(), clienteCriado.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void criarConta_clienteJaExistente() {
        // Mock para simular que o cliente já existe no banco
        when(clienteRepository.findByEmail(cliente.getEmail())).thenReturn(cliente);

        // Executa e verifica se a exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.criarConta(cliente);
        });

        assertEquals("Cliente já existe com este email", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void associarCartaoCredito_comSucesso() {
        // Mock para simular que o cliente existe
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Executa o método
        Cliente clienteAtualizado = userService.associarCartaoCredito(cliente, "1234-5678-9876-5432");

        // Verificações
        assertEquals("1234-5678-9876-5432", clienteAtualizado.getCartaoCredito());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void associarCartaoCredito_clienteNaoEncontrado() {
        // Mock para simular que o cliente não existe
        when(clienteRepository.findById(cliente.getId())).thenReturn(java.util.Optional.empty());

        // Executa e verifica se a exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.associarCartaoCredito(cliente, "1234-5678-9876-5432");
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }
}
