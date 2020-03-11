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
import br.com.compasso.backend.model.EstadoModel;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EstadoRepositoryTest {
	
	@Autowired
    private EstadoRepository estadoRepository;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void findById() {
    	EstadoModel estado = estadoRepository.findById(13);
    	Assertions.assertThat(estado.getEstadoId()).isEqualTo(13);
    }
    
    @Test
    public void findByNome() {
    	EstadoModel estado = estadoRepository.findByNome("Rio de Janeiro");
    	Assertions.assertThat(estado.getNome()).isEqualTo("Rio de Janeiro");
    }
    
    @Test
    public void findByUf() {
    	EstadoModel estado = estadoRepository.findByUf("SC");
    	Assertions.assertThat(estado.getUf()).isEqualTo("SC");
    }
}
