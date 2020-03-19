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
import br.com.compasso.backend.model.EstadoModel;
import br.com.compasso.backend.repository.EstadoRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Rafael Martins de Padua
 * @Controller
 * @ResponseBody
 */
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
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Lista todos os estados")
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
    public ResponseEntity getEstadosModels() {
		
		try {
        	List<EstadoModel> listaEstados = estadoRepository.findAll();
			
			if (!listaEstados.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaEstados);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
						+ "\nerror: Not Found."
						+ "\nmessage: Não foi possível encontrar o recurso especificado.");
            }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
    }
	
	/**
	 * Endpoint para listar todos os estados pelo nome
	 * @author Rafael Martins de Padua
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Consulta um estado pelo nome")
	@RequestMapping(value = "/estadoNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity GetByEstado(@PathVariable(value = "nome") String nome) {
		
		try {
			EstadoModel estado = estadoRepository.findByNome(nome);
			
			if (estado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(estado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
						+ "\nerror: Not Found."
						+ "\nmessage: Não foi possível encontrar o recurso especificado.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
	}
	
	/**
	 * Endpoint para listar todos os estados pelo UF
	 * @author Rafael Martins de Padua
	 * @param uf
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Consulta um estado pelo UF")
	@RequestMapping(value = "/estadoUf/{uf}", method = RequestMethod.GET)
	public ResponseEntity GetByUf(@PathVariable(value = "uf") String uf) {
					
		try {
			EstadoModel estado = estadoRepository.findByUf(uf);
			
			if (estado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(estado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
						+ "\nerror: Not Found."
						+ "\nmessage: Não foi possível encontrar o recurso especificado.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
	}
	
	/**
	 * Endpoint para cadastrar um novo estado
	 * @author Rafael Martins de Padua
	 * @param estado
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Cadastra um novo estado")
	@RequestMapping(value = "/estado", method = RequestMethod.POST)
	public ResponseEntity estadoCreate(@RequestBody EstadoModel estado) {
		
		try {
			EstadoModel buscaEstado = estadoRepository.findByNome(estado.getNome());
			
			if (buscaEstado == null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(estadoRepository.save(estado));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("status: 409."
						+ "\nerror: Conflict."
						+ "\nmessage: A solicitação não pôde ser concluída devido a um conflito com o estado "
						+ "atual do recurso de destino.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
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
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Atualiza um estado")
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.PUT)
	public ResponseEntity estadoUpdate(@PathVariable(value = "id_estado") Long estadoId, @RequestBody EstadoModel estado) {
		
		try {
			Optional<EstadoModel> buscaEstado = estadoRepository.findById(estadoId);
			
			if (buscaEstado.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(estadoRepository.save(estado));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
						+ "\nerror: Not Found."
						+ "\nmessage: Não foi possível encontrar o recurso especificado.");
			}	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
	}
	
	/**
	 * Endpoint para remover um estado
	 * @author Rafael Martins de Padua
	 * @param estadoId
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Remove um estado")
	@RequestMapping(value = "/estado/{id_estado}", method = RequestMethod.DELETE)
	public ResponseEntity estadoDelete(@PathVariable(value = "id_estado") Long estadoId) {
		
		try {
			Optional<EstadoModel> estado = estadoRepository.findById(estadoId);
			
			if (estado.isPresent()) {
				estadoRepository.deleteById(estadoId);
				return ResponseEntity.status(HttpStatus.OK).body("status: 200."
						+ "\nmessage: Recurso removido com sucesso.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
						+ "\nerror: Not Found."
						+ "\nmessage: Não foi possível encontrar o recurso especificado.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
	}
}
