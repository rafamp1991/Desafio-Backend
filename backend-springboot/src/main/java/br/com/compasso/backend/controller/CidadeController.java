package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public List<CidadeModel> getCidadesModels() {
        return cidadeRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value = "/cidade/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeModel>> getByNome(@PathVariable(value = "nome") String nome) {
		List<CidadeModel> listaCidade = cidadeRepository.findByNome(nome);
		if (listaCidade != null) {
			return ResponseEntity.ok(listaCidade);
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cidade/estadoUf/{uf}", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeModel>> getCidadesByEstadoUf(@PathVariable(value = "uf") String uf) {
		EstadoModel estado = estadoRepository.findByUf(uf);
		
		if (estado != null) {
			List<CidadeModel> filtraCidade = new ArrayList<>();
			List<CidadeModel> cidades = cidadeRepository.findAll();
			
			for (CidadeModel cidade : cidades) {
				if (cidade.getEstadoModel().getUf().equals(estado.getUf())) {
					filtraCidade.add(cidade);
				}
			}
			
			return ResponseEntity.ok(filtraCidade);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cidade/estadoNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeModel>> getCidadesByEstadoNome(@PathVariable(value = "nome") String nome) {
		EstadoModel estado = estadoRepository.findByNome(nome);
		
		if (estado != null) {
			List<CidadeModel> filtraCidade = new ArrayList<>();
			List<CidadeModel> cidades = cidadeRepository.findAll();
			
			for (CidadeModel cidade : cidades) {
				if (cidade.getEstadoModel().getNome().equals(estado.getNome())) {
					filtraCidade.add(cidade);
				}
			}
			
			return ResponseEntity.ok(filtraCidade);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public ResponseEntity<CidadeModel> cidadeCreate(@RequestBody CidadeModel cidade) {
		Optional<CidadeModel> buscaCidade = cidadeRepository.findCidadeByNome(cidade.getNome());
		if (buscaCidade.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} else {
			return ResponseEntity.ok(cidadeRepository.save(cidade));
		}
	}
	
	/**
	 * 
	 * @param cidadeId
	 * @param cidade
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.PUT)
	public ResponseEntity<CidadeModel> cidadeUpdate(@PathVariable(value = "id_cidade") Long cidadeId, @RequestBody CidadeModel cidade) {
		Optional<CidadeModel> buscaCidade = cidadeRepository.findById(cidadeId);
		
		if (buscaCidade.isPresent()) {
			return ResponseEntity.ok(cidadeRepository.save(cidade));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}	
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.DELETE)
	public ResponseEntity<CidadeModel> cidadeDelete(@PathVariable(value = "id_cidade") Long cidadeId) {
		Optional<CidadeModel> cidade = cidadeRepository.findById(cidadeId);
		if (cidade.isPresent()) {
			cidadeRepository.deleteById(cidadeId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		return ResponseEntity.notFound().build();
	}
}
