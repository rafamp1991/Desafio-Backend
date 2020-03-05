package br.com.compasso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.model.CidadeModel;
import br.com.compasso.backend.repository.CidadeRepository;

@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public List<CidadeModel> getCidadesModels() {
        return cidadeRepository.findAll();
    }
	
	@RequestMapping(value = "/cidades/{nome}", method = RequestMethod.GET)
	public CidadeModel GetByNome(@PathVariable(value = "nome") String nome) {
		return cidadeRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/cidades/estado/{id_estado}", method = RequestMethod.GET)
	public List<CidadeModel> getCidadesByEstado(@PathVariable(value = "id_estado") long estadoId) {
		return cidadeRepository.findByEstadoId(estadoId);
	}
	
//	@RequestMapping(value = "/cidades", method = RequestMethod.POST)
//	public CidadeModel cidadesCreate(@RequestBody CidadeModel cidade) {
//		CidadeModel cidadesModel = new CidadeModel();
//		cidadesModel.setNome(cidade.getNome());
//		//cidadesModel.setEstadoModel(cidade.getEstadoModel());
//		return cidadeRepository.save(cidadesModel);
//	}
}
