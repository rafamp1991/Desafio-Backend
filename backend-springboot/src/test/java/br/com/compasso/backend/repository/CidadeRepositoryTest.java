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
import br.com.compasso.backend.model.CidadeModel;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CidadeRepositoryTest {

	@Autowired
    private CidadeRepository cidadeRepository;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
//    @Test
//    public void findById() {
//    	CidadeModel cidade = cidadeRepository.findById(3100104);
//    	Assertions.assertThat(cidade.getCidadeId()).isEqualTo(3100104);
//    }
    
    @Test
    public void findByNome() {
    	CidadeModel cidade = cidadeRepository.findByNome("Abadia dos Dourados");
    	Assertions.assertThat(cidade.getNome()).isEqualTo("Abadia dos Dourados");
    }
}
