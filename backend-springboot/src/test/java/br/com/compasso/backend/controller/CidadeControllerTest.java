package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import br.com.compasso.backend.model.CidadeModel;
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.CidadeRepository;
import br.com.compasso.backend.repository.EstadoRepository;

@WebMvcTest(CidadeController.class)
@ActiveProfiles("test")
public class CidadeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
    private CidadeRepository cidadeRepository;
	
	@MockBean
    private EstadoRepository estadoRepository;
	
	private CidadeModel cidadeModel;
	private CidadeModel cidadeModel2;
	
	@Before
    public void setUP() {
 
        cidadeModel = new CidadeModel();
        cidadeModel.setCidadeId(2114007L);
        cidadeModel.setNome("Zé Doca");
        cidadeModel.setLatitude(-3.27014);
        cidadeModel.setLongitude(-45.6553);
        cidadeModel.setCapital(false);
        
        cidadeModel2 = new CidadeModel();
        cidadeModel2.setCidadeId(3557154L);
        cidadeModel2.setNome("Zacarias");
        cidadeModel2.setLatitude(-21.0506);
        cidadeModel2.setLongitude(-50.0552);
        cidadeModel2.setCapital(false);
    }
	
//	@Test
//	public void getCidades() throws Exception {
//		List<CidadeModel> cidadeList = new ArrayList<>();
//		cidadeList.add(cidadeModel);
//		cidadeList.add(cidadeModel2);
//		
//		Mockito.when(cidadeRepository.findAll()).thenReturn(cidadeList);
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("/cidades").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.jsonPath("$.cidades").exists())
//		.andExpect(MockMvcResultMatchers.jsonPath("$.cidades[*].cidadeId").isNotEmpty());
//	}
	
//	@Test
//	public void cidadeCreate() throws Exception {
//	    Mockito.doReturn(Optional.empty())
//		.when(cidadeRepository)
//		.findCidadeByNome("Zacarias");
//	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/nome"))
//	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
//	}
	
	@Test
	public void getCidadesModels() throws Exception {
		List<CidadeModel> listaCidade = new ArrayList<CidadeModel>();
	    Mockito.doReturn(listaCidade)
		.when(estadoRepository)
		.findAll();
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidades"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void getCidadesByEstadoUf() throws Exception {
		EstadoModel estado = new EstadoModel();
	    Mockito.doReturn(estado)
		.when(estadoRepository)
		.findByUf("AC");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoUf/uf"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void getCidadesByEstadoNome() throws Exception {
		EstadoModel estado = new EstadoModel();
	    Mockito.doReturn(estado)
		.when(estadoRepository)
		.findByNome("Rio de Janeiro");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoNome/nome"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void getByNome() throws Exception {
		List<CidadeModel> listaCidade = new ArrayList<CidadeModel>();
	    Mockito.doReturn(listaCidade)
		.when(cidadeRepository)
		.findByNome("Zacarias");
	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/nome"))
	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}
}
