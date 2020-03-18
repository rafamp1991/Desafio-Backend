package br.com.compasso.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.ClienteModel;

/**
 * @author Rafael Martins de Padua
 * @Repository
 */
@SpringBootTest
public class ClienteRepositoryTest {
 
    @Autowired
    private ClienteRepository clienteRepository;
    
    /**
     * Teste para consultar o cliente pelo Id
     */
    @Test
    public void findById() {
    	Optional<ClienteModel> cliente = clienteRepository.findById(23L);
    	
    	assertTrue(cliente.isPresent());
    	ClienteModel clienteModel = cliente.get();
    	assertEquals(clienteModel.getNome(), "Vanessa");
    }
    
    /**
     * Teste para comparar o tamanho da lista de clientes
     * @throws Exception
     */
	@Test
    public void findByNome() throws Exception {
		List<ClienteModel> clientes = clienteRepository.findByNome("Vanessa");
		assertEquals(clientes.size(),3);
    }
    
}
