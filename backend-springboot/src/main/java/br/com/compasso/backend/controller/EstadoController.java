package br.com.compasso.backend.controller;

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
import br.com.compasso.backend.repository.EstadoRepository;

@RestController
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * Endpoint para listar todos os estados
	 * @author Rafael Martins de Padua
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
    public ResponseEntity<List<EstadoModel>> getEstadosModels() {
		
		try {
        	List<EstadoModel> listaEstados = estadoRepository.findAll();
			
			if (!listaEstados.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaEstados);
			} else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
    }
	
	/**
	 * Endpoint para listar todos os estados pelo nome
	 * @author Rafael Martins de Padua
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estadoNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<EstadoModel> GetByEstado(@PathVariable(value = "nome") String nome) {
		
		try {
			EstadoModel estado = estadoRepository.findByNome(nome);
			
			if (estado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(estado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Endpoint para listar todos os estados pelo UF
	 * @author Rafael Martins de Padua
	 * @param uf
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estadoUf/{uf}", method = RequestMethod.GET)
	public ResponseEntity<EstadoModel> GetByUf(@PathVariable(value = "uf") String uf) {
					
		try {
			EstadoModel estado = estadoRepository.findByUf(uf);
			
			if (estado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(estado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Endpoint para cadastrar um novo estado
	 * @author Rafael Martins de Padua
	 * @param estado
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estado", method = RequestMethod.POST)
	public ResponseEntity<EstadoModel> estadoCreate(@RequestBody EstadoModel estado) {
		
		try {
			EstadoModel buscaEstado = estadoRepository.findByNome(estado.getNome());
			
			if (buscaEstado == null) {
				return ResponseEntity.status(HttpStatus.OK).body(estadoRepository.save(estado));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Endpoint para atualizar um estado
	 * @author Rafael Martins de Padua
	 * @param estadoId
	 * @param estado
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.PUT)
	public ResponseEntity<EstadoModel> estadoUpdate(@PathVariable(value = "id_estado") Long estadoId, @RequestBody EstadoModel estado) {
		
		try {
			Optional<EstadoModel> buscaEstado = estadoRepository.findById(estadoId);
			
			if (buscaEstado.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(estadoRepository.save(estado));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * Endpoint para remover um estado
	 * @author Rafael Martins de Padua
	 * @param estadoId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.DELETE)
	public ResponseEntity<CidadeModel> cidadeDelete(@PathVariable(value = "id_estado") Long estadoId) {
		
		try {
			Optional<EstadoModel> cidade = estadoRepository.findById(estadoId);
			
			if (cidade.isPresent()) {
				estadoRepository.deleteById(estadoId);
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
