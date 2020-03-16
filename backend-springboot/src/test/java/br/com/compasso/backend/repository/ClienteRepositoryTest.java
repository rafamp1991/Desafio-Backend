package br.com.compasso.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.ClienteModel;

@SpringBootTest
public class ClienteRepositoryTest {
 
    @Autowired
    private ClienteRepository clienteRepository;
    
    private ClienteModel clienteModel;
    
    @Before
    public void setUP() {
    	
    	LocalDate localDate = LocalDate.of( 2011 , 4 , 21 );
    	
    	clienteModel = new ClienteModel();
    	clienteModel.setClienteId(23L);
    	clienteModel.setNome("Vanessa");
    	clienteModel.setSobrenome("Ramos");
    	clienteModel.setSexo("feminino");
    	clienteModel.setDataNascimento(localDate);
    	clienteModel.setIdade(9);
    }
    
    @DisplayName("Teste para consultar o cliente pelo Id")
    @Test
    public void findById() {
    	Optional<ClienteModel> cliente = clienteRepository.findById(23L);
    	
    	assertTrue(cliente.isPresent());
    	ClienteModel clienteModel = cliente.get();
    	assertEquals(clienteModel.getNome(), "Vanessa");
    }
    
    @DisplayName("Teste para comparar o tamanho da lista de clientes")
	@Test
    public void findByNome() throws Exception {
		List<ClienteModel> clientes = clienteRepository.findByNome("Vanessa");
		assertEquals(clientes.size(),3);
    }
    
}
