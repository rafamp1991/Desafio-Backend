package br.com.compasso.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/cidade/{nome}", method = RequestMethod.GET)
	public CidadeModel GetByNome(@PathVariable(value = "nome") String nome) {
		return cidadeRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/cidade/estado/{id_estado}", method = RequestMethod.GET)
	public List<CidadeModel> getCidadesByEstado(@PathVariable(value = "id_estado") long estadoId) {
		return cidadeRepository.findByEstadoId(estadoId); 
	}
	
	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public CidadeModel cidadeCreate(@RequestBody CidadeModel cidade) {
		CidadeModel cidadeModel = new CidadeModel();
		cidadeModel.setNome(cidade.getNome());
		cidadeModel.setLatitude(cidade.getLatitude());
		cidadeModel.setLongitude(cidade.getLongitude());
		cidadeModel.setCapital(cidade.getCapital());
		cidadeModel.setEstadoId(cidade.getEstadoId());
		return cidadeRepository.save(cidadeModel);
	}
	
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.PUT)
	public CidadeModel cidadeUpdate(@PathVariable(value = "id_cidade") long cidadeId, @RequestBody CidadeModel cidade) {
		CidadeModel cidadeModel = cidadeRepository.findById(cidadeId);
		cidadeModel.setNome(cidade.getNome());
		cidadeModel.setLatitude(cidade.getLatitude());
		cidadeModel.setLongitude(cidade.getLongitude());
		cidadeModel.setCapital(cidade.getCapital());
		cidadeModel.setEstadoId(cidade.getEstadoId());
		return cidadeRepository.save(cidadeModel);
	}
	
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.DELETE)
	public CidadeModel cidadeDelete(@PathVariable(value = "id_cidade") long cidadeId) {
		return cidadeRepository.deleteById(cidadeId);
	}
}
