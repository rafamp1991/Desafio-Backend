package br.com.compasso.backend.repository;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.compasso.backend.model.ClienteModel;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteRepositoryTest {
 
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void findById() {
    	ClienteModel cliente = clienteRepository.findById(3);
    	Assertions.assertThat(cliente.getClienteId()).isEqualTo(3);
    }
    
    @Test
    public void findByNome() {
    	ClienteModel cliente = clienteRepository.findByNome("Rafa");
    	Assertions.assertThat(cliente.getNome()).isEqualTo("Rafa");
    }
    
    @Test
    public void findByNomeAndSobrenome() {
    	ClienteModel cliente = clienteRepository.findByNomeAndSobrenome("Rafa", "Martins de Padua");
    	Assertions.assertThat(cliente.getNome()).isEqualTo("Rafa");
    	Assertions.assertThat(cliente.getSobrenome()).isEqualTo("Martins de Padua");
    }
}
