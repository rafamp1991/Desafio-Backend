package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.model.CidadeModel;
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.CidadeRepository;
import br.com.compasso.backend.repository.EstadoRepository;

@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public List<CidadeModel> getCidadesModels() {
        return cidadeRepository.findAll();
    }
	
	@RequestMapping(value = "/cidade/{nome}", method = RequestMethod.GET)
	public CidadeModel GetByNome(@PathVariable(value = "nome") String nome) {
		return cidadeRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/cidade/estadoId/{estadoId}", method = RequestMethod.GET)
	public List<CidadeModel> getCidadesByEstadoId(@PathVariable(value = "estadoId") long estadoId) {
		EstadoModel estadoModel = estadoRepository.findById(estadoId);
		List<CidadeModel> listaCidadeById = new ArrayList<>(); 
		listaCidadeById.addAll(estadoModel.getCidades());
		return listaCidadeById;
	}
	
	@RequestMapping(value = "/cidade/estadoUf/{uf}", method = RequestMethod.GET)
	public List<CidadeModel> getCidadesByEstadoUf(@PathVariable(value = "uf") String uf) {
		EstadoModel estadoModel = estadoRepository.findByUf(uf);
		List<CidadeModel> listaCidadeByUf = new ArrayList<>(); 
		listaCidadeByUf.addAll(estadoModel.getCidades());
		return listaCidadeByUf;
	}
	
	@RequestMapping(value = "/cidade/estadoNome/{nome}", method = RequestMethod.GET)
	public List<CidadeModel> getCidadesByEstadoNome(@PathVariable(value = "nome") String nome) {
		EstadoModel estadoModel = estadoRepository.findByNome(nome);
		List<CidadeModel> listaCidadeByNome = new ArrayList<>(); 
		listaCidadeByNome.addAll(estadoModel.getCidades());
		return listaCidadeByNome;
	}
	
	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public CidadeModel cidadeCreate(@RequestBody CidadeModel cidade) {
		CidadeModel cidadeModel = new CidadeModel();
		cidadeModel.setNome(cidade.getNome());
		cidadeModel.setLatitude(cidade.getLatitude());
		cidadeModel.setLongitude(cidade.getLongitude());
		cidadeModel.setCapital(cidade.getCapital());
		cidadeModel.setEstadoModel(cidade.getEstadoModel());
		return cidadeRepository.save(cidadeModel);
	}
	
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.PUT)
	public CidadeModel cidadeUpdate(@PathVariable(value = "id_cidade") long cidadeId, @RequestBody CidadeModel cidade) {
		CidadeModel cidadeModel = cidadeRepository.findById(cidadeId);
		cidadeModel.setNome(cidade.getNome());
		cidadeModel.setLatitude(cidade.getLatitude());
		cidadeModel.setLongitude(cidade.getLongitude());
		cidadeModel.setCapital(cidade.getCapital());
		cidadeModel.setEstadoModel(cidade.getEstadoModel());
		return cidadeRepository.save(cidadeModel);
	}
	
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.DELETE)
	public CidadeModel cidadeDelete(@PathVariable(value = "id_cidade") long cidadeId) {
		return cidadeRepository.deleteById(cidadeId);
	}
}
