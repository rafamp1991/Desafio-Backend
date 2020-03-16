package br.com.compasso.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.CidadeModel;

@SpringBootTest
public class CidadeRepositoryTest {
 
	@Autowired
    private CidadeRepository cidadeRepository;
	
	private CidadeModel cidadeModel;
	
	@Before
    public void setUP() {
 
        cidadeModel = new CidadeModel();
        cidadeModel.setCidadeId(2114007L);
        cidadeModel.setNome("Zé Doca");
        cidadeModel.setLatitude(-3.27014);
        cidadeModel.setLongitude(-45.6553);
        cidadeModel.setCapital(false);
    }

	@DisplayName("Teste para consultar a cidade pelo Id")
	@Test
	public void findById(){
		Optional<CidadeModel> cidade = cidadeRepository.findById(2114007L);
		
		assertTrue(cidade.isPresent());
		CidadeModel cidadeModel = cidade.get();
		assertEquals(cidadeModel.getNome(), "Zé Doca");
	}
	
	@DisplayName("Teste para consultar a cidade pelo nome")
	@Test
	public void findCidadeByNome(){
		Optional<CidadeModel> cidade = cidadeRepository.findCidadeByNome("Zé Doca");
		
		assertTrue(cidade.isPresent());
		CidadeModel cidadeModel = cidade.get();
		assertEquals(cidadeModel.getCidadeId(), Long.valueOf(2114007));
	}
	
	@DisplayName("Teste para consultar uma cidade inexistente pelo id")
	@Test
    public void cidadeIdInexistente() throws Exception {
        Optional<CidadeModel> cidade = cidadeRepository.findById(9999L);

        assertFalse(cidade.isPresent());
    }
	
	@DisplayName("Teste para comparar o tamanho da lista de cidades")
	@Test
    public void findByNome() throws Exception {
		List<CidadeModel> cidades = cidadeRepository.findByNome("Zé Doca");
		assertEquals(cidades.size(),1);
    }
}
