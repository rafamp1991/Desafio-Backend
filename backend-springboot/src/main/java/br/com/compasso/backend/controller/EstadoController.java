package br.com.compasso.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.EstadoRepository;

@RestController
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@CrossOrigin
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
    public List<EstadoModel> getEstadosModels() {
        return estadoRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value = "/estadoNome/{nome}", method = RequestMethod.GET)
	public EstadoModel GetByEstado(@PathVariable(value = "nome") String nome) {
		return estadoRepository.findByNome(nome);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estadoUf/{uf}", method = RequestMethod.GET)
	public EstadoModel GetByUf(@PathVariable(value = "uf") String uf) {
		return estadoRepository.findByUf(uf);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado", method = RequestMethod.POST)
	public EstadoModel estadoCreate(@RequestBody EstadoModel estado) {
		EstadoModel estadoModel = new EstadoModel();
		estadoModel.setNome(estado.getNome());
		estadoModel.setUf(estado.getUf());
		
		return estadoRepository.save(estadoModel);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.PUT)
	public EstadoModel estadoUpdate(@PathVariable(value = "id_estado") long estadoId, @RequestBody EstadoModel estado) {
		EstadoModel estadoModel = estadoRepository.findById(estadoId);
		estadoModel.setNome(estado.getNome());
		estadoModel.setUf(estado.getUf());
		
		return estadoRepository.save(estadoModel);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.DELETE)
	public EstadoModel estadoDelete(@PathVariable(value = "id_estado") long estadoId) {
		return estadoRepository.deleteById(estadoId);
	}
}
