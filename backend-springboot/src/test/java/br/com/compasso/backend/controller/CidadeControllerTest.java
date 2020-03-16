package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.compasso.backend.model.CidadeModel;
import br.com.compasso.backend.repository.CidadeRepository;

@WebMvcTest(CidadeController.class)
@ActiveProfiles("test")
public class CidadeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
    private CidadeRepository cidadeRepository;
	
	@Autowired
	ObjectMapper mapper;
	
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
	
	@Test
	public void getCidades() throws Exception {
		List<CidadeModel> cidadeList = new ArrayList<>();
		cidadeList.add(cidadeModel);
		cidadeList.add(cidadeModel2);
		
		Mockito.when(cidadeRepository.findAll()).thenReturn(cidadeList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cidades").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cidades").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.cidades[*].cidadeId").isNotEmpty());
	}
}
