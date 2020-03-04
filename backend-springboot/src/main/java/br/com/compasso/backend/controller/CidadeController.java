package br.com.compasso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.backend.controller.dto.CidadeDto;
import br.com.compasso.backend.desafio.repository.CidadeRepository;
import br.com.compasso.backend.model.CidadeModel;

@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadesRepository;
	
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public List<CidadeModel> getCidadesModels() {
        return cidadesRepository.findAll();
    }
	
	@RequestMapping(value = "/cidades/{nome}", method = RequestMethod.GET)
	public CidadeModel GetById(@PathVariable(value = "nome") String nome) {
		return cidadesRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/cidades", method = RequestMethod.POST)
	public CidadeModel cidadesCreate(@RequestBody CidadeModel cidade) {
		CidadeModel cidadesModel = new CidadeModel();
		cidadesModel.setNome(cidade.getNome());
		cidadesModel.setEstado(cidade.getEstado());
		return cidadesRepository.save(cidadesModel);
	}
	
	@RequestMapping(value = "/cidades/{id}", method = RequestMethod.PUT)
	public CidadeModel cidadesUpdate(@PathVariable(value = "id") long id, @RequestBody CidadeDto cidade) {
		CidadeModel cidadesModel = cidadesRepository.findById(id);
		cidadesModel.setNome(cidade.getNome());
		cidadesModel.setEstado(cidade.getEstado());
		return cidadesRepository.save(cidadesModel);
	}
	
	@RequestMapping(value = "/cidades/{id}", method = RequestMethod.DELETE)
	public CidadeModel userDelete(@PathVariable(value = "id") long id) {
		return cidadesRepository.deleteById(id);
	}
}
