package br.com.compasso.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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
	public ResponseEntity<EstadoModel> GetByEstado(@PathVariable(value = "nome") String nome) {
		EstadoModel estado = estadoRepository.findByNome(nome);
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estadoUf/{uf}", method = RequestMethod.GET)
	public ResponseEntity<EstadoModel> GetByUf(@PathVariable(value = "uf") String uf) {
		EstadoModel estado = estadoRepository.findByUf(uf);
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado", method = RequestMethod.POST)
	public ResponseEntity<EstadoModel> estadoCreate(@RequestBody EstadoModel estado) {
		EstadoModel buscaEstado = estadoRepository.findByNome(estado.getNome());
		if (buscaEstado != null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} else {
			return ResponseEntity.ok(estadoRepository.save(estado));
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.PUT)
	public ResponseEntity<EstadoModel> estadoUpdate(@PathVariable(value = "id_estado") Long estadoId, @RequestBody EstadoModel estado) {
		/*Optional<EstadoModel> buscaEstado = estadoRepository.findById(estadoId);
		buscaEstado.se
		if (buscaEstado.isPresent()) {	
			return ResponseEntity.ok(estadoRepository.save(estado));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}*/
		
	//	estado.setCidades(new Optional<CidadeModel>());
		
		EstadoModel buscaEstado = estadoRepository.findById(estadoId).get();

		if (buscaEstado != null) {	
			return ResponseEntity.ok(estadoRepository.save(estado));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.DELETE)
	public ResponseEntity<CidadeModel> cidadeDelete(@PathVariable(value = "id_estado") Long estadoId) {
		Optional<EstadoModel> cidade = estadoRepository.findById(estadoId);
		if (cidade.isPresent()) {
			estadoRepository.deleteById(estadoId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		return ResponseEntity.notFound().build();
	}
}
