package br.com.compasso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.EstadoRepository;

@RestController
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
    public List<EstadoModel> getEstadosModels() {
        return estadoRepository.findAll();
    }
	
	@RequestMapping(value = "/estados/{nome}", method = RequestMethod.GET)
	public EstadoModel GetByEstado(@PathVariable(value = "nome") String nome) {
		return estadoRepository.findByNome(nome);
	}
	
//	@RequestMapping(value = "/estados/{uf}", method = RequestMethod.GET)
//	public EstadoModel GetByEstadoUf(@PathVariable(value = "uf") String uf) {
//		
//		return estadoRepository.findByUf(uf);
//	}
}
