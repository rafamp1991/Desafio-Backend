package br.com.compasso.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.compasso.backend.model.CidadeModel;

/**
 * @author Rafael Martins de Padua
 * @Repository
 */
@SpringBootTest
public class CidadeRepositoryTest {
 
	@Autowired
    private CidadeRepository cidadeRepository;

	/**
	 * Teste para consultar a cidade pelo Id
	 */
	@Test
	public void findById(){
		Optional<CidadeModel> cidade = cidadeRepository.findById(2114007L);
		
		assertTrue(cidade.isPresent());
		CidadeModel cidadeModel = cidade.get();
		assertEquals(cidadeModel.getNome(), "Zé Doca");
	}
	
	/**
	 * Teste para consultar a cidade pelo nome
	 */
	@Test
	public void findCidadeByNome(){
		Optional<CidadeModel> cidade = cidadeRepository.findCidadeByNome("Zé Doca");
		
		assertTrue(cidade.isPresent());
		CidadeModel cidadeModel = cidade.get();
		assertEquals(cidadeModel.getCidadeId(), Long.valueOf(2114007));
	}
	
	
	/**
	 * Teste para consultar uma cidade inexistente pelo id
	 * @throws Exception
	 */
	@Test
    public void cidadeIdInexistente() throws Exception {
        Optional<CidadeModel> cidade = cidadeRepository.findById(9999L);

        assertFalse(cidade.isPresent());
    }
	
	/**
	 * Teste para comparar o tamanho da lista de cidades
	 * @throws Exception
	 */
	@Test
    public void findByNome() throws Exception {
		List<CidadeModel> cidades = cidadeRepository.findByNome("Zé Doca");
		assertEquals(cidades.size(),1);
    }
}
