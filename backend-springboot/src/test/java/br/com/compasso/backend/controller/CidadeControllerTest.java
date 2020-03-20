package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.compasso.backend.model.CidadeModel;
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.CidadeRepository;
import br.com.compasso.backend.repository.EstadoRepository;

/**
 * @author Rafael Martins de Padua
 */
@WebMvcTest(CidadeController.class)
@ActiveProfiles("test")
public class CidadeControllerTest {
 
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
    private CidadeRepository cidadeRepository;
	
	@MockBean
    private EstadoRepository estadoRepository;
	
	private CidadeModel cidadeModel;
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por consultar todas as cidades.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
	public void getCidadesModels() throws Exception {
		List<CidadeModel> listaCidades = new ArrayList<CidadeModel>();
		listaCidades.add(cidadeModel);
		
	    Mockito.doReturn(listaCidades)
		.when(cidadeRepository)
		.findAll();
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidades"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por consultar cidades pelo nome.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
	public void getByNome() throws Exception {
		List<CidadeModel> listaCidades = new ArrayList<CidadeModel>();
		listaCidades.add(cidadeModel);
		
	    Mockito.doReturn(listaCidades)
		.when(cidadeRepository)
		.findByNome("Zacarias");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/Zacarias"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por consultar cidades pelo UF do estado.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
	public void getCidadesByEstadoUf() throws Exception {
		EstadoModel estado = new EstadoModel();
		
	    Mockito.doReturn(estado)
		.when(estadoRepository)
		.findByUf("RJ");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoUf/RJ"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por consultar cidades pelo nome do estado.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
	public void getCidadesByEstadoNome() throws Exception {
		EstadoModel estado = new EstadoModel();
		
	    Mockito.doReturn(estado)
		.when(estadoRepository)
		.findByNome("Rio de Janeiro");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoNome/Rio de Janeiro"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por cadastrar uma nova cidade.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
    public void cidadeCreate() throws Exception {
		
		cidadeModel = new CidadeModel();
        cidadeModel.setCidadeId(1L);
        cidadeModel.setNome("CidadeTeste");
        cidadeModel.setLatitude(-27.0000);
        cidadeModel.setLongitude(-50.0000);
        cidadeModel.setCapital(true);
		
        mockMvc.perform(MockMvcRequestBuilders.post("/cidade")
        		.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(cidadeModel)))
	    		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por atualizar uma cidade.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
    public void cidadeUpdate() throws Exception {
		
		CidadeModel atualizaCidade = new CidadeModel();
		atualizaCidade.setCidadeId(3557154L);
		atualizaCidade.setNome("CidadeTeste");
		atualizaCidade.setLatitude(-35.0000);
		atualizaCidade.setLongitude(-20.0000);
		atualizaCidade.setCapital(false);
       
	    when(cidadeRepository.findById(3557154L)).thenReturn(Optional.of(atualizaCidade));
	    when(cidadeRepository.save(any(CidadeModel.class))).thenReturn(atualizaCidade);
        
	    mockMvc.perform(MockMvcRequestBuilders.put("/cidade/3557154")
	    		.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(atualizaCidade)))
	    		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
	            .andExpect(jsonPath("$.cidadeId", is(3557154)))
	            .andExpect(jsonPath("$.nome", is("CidadeTeste")))
	            .andExpect(jsonPath("$.latitude", is(-35.0000)))
	            .andExpect(jsonPath("$.longitude", is(-20.0000)))
	            .andExpect(jsonPath("$.capital", is(false)));
    }
	
	/**
	 * Método para testar a Endpoint, 
	 * responsável por remover uma cidade.
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção em caso de erro
	 */
	@Test
    public void cidadeDelete() throws Exception {
		
		cidadeModel = new CidadeModel();
        cidadeModel.setCidadeId(1L);
        cidadeModel.setNome("CidadeTeste");
        cidadeModel.setLatitude(-27.0000);
        cidadeModel.setLongitude(-50.0000);
        cidadeModel.setCapital(true);
		
        when(cidadeRepository.findById(3557154L)).thenReturn(Optional.of(cidadeModel));
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/cidade/3557154"))
	    		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
        
        verify(cidadeRepository, times(1)).deleteById(3557154L);
    }
	
	/**
	 * Consulta uma cidade por um nome inexistente
	 * @author Rafael Martins de Padua
	 * @throws Exception lança uma exceção informando 
	 * 					 que o registro não pode ser encontrado.
	 */
	@Test
	public void cidadeNaoExiste() throws Exception {
		List<CidadeModel> listaCidades = new ArrayList<CidadeModel>();
		
	    Mockito.doReturn(listaCidades)
		.when(cidadeRepository)
		.findByNome("Tangamandapio");		
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/Tangamandapio"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
}
