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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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
	private EstadoModel estadoModel;
	
	@Before
    public void setUP() {
        
        cidadeModel = new CidadeModel();
        cidadeModel.setCidadeId(3557154L);
        cidadeModel.setNome("Zacarias");
        cidadeModel.setLatitude(-21.0506);
        cidadeModel.setLongitude(-50.0552);
        cidadeModel.setCapital(false);
        
        estadoModel = new EstadoModel();
        estadoModel.setEstadoId(35L);
        estadoModel.setNome("São Paulo");
        estadoModel.setUf("SP");  
    }
	
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
	
//	@Test
//	public void getByNome() throws Exception {
//		List<CidadeModel> listaCidades = new ArrayList<CidadeModel>();
//		listaCidades.add(cidadeModel);
//		
//	    Mockito.doReturn(listaCidades)
//		.when(cidadeRepository)
//		.findByNome("Zacarias");
//	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/nome"))
//	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
//	}
	
//	@Test
//	public void getCidadesByEstadoUf() throws Exception {
//		EstadoModel estado = estadoRepository.findByUf("SP");
//		
//		List<CidadeModel> filtraCidade = new ArrayList<>();
//		List<CidadeModel> cidades = cidadeRepository.findAll();
//		
//		for (CidadeModel cidade : cidades) {
//			if (cidade.getEstadoModel().getUf().equals(estado.getUf())) {
//				filtraCidade.add(cidade);
//			}
//		}
//		
//	    Mockito.doReturn(estado)
//		.when(estadoRepository)
//		.findByUf("SP");
//	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoUf/uf"))
//	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
//	}
	
//	@Test
//	public void getCidadesByEstadoNome() throws Exception {
//		EstadoModel estado = new EstadoModel();
//		
//	    Mockito.doReturn(estado)
//		.when(estadoRepository)
//		.findByNome("Rio de Janeiro");
//	    mockMvc.perform(MockMvcRequestBuilders.get("/cidade/estadoNome/nome"))
//	            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
//	}
	
//	@Test
//    public void cidadeCreate() throws Exception {
//     
//        Mockito.doReturn(Optional.of(cidadeModel)).when(cidadeRepository).findById(3557154L);
//        mockMvc.perform(MockMvcRequestBuilders.post("/cidade"))
//        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
//    }
	
	
}
