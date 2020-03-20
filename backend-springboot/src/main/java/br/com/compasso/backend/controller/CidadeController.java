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
import io.swagger.annotations.ApiOperation;

/**
 * @author Rafael Martins de Padua
 * @Controller
 * @ResponseBody
 */
@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * Endpoint para listar todas as cidades
	 * @author Rafael Martins de Padua
	 * @return
	 */
	
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Lista todas as cidades")
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public ResponseEntity getCidadesModels() {
		
        try {
        	List<CidadeModel> listaCidades = cidadeRepository.findAll();
			
			if (!listaCidades.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaCidades);
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
	 * Endpoint para listar as cidades pelo nome
	 * @author Rafael Martins de Padua
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Lista as cidades pelo nome")
	@RequestMapping(value = "/cidade/{nome}", method = RequestMethod.GET)
	public ResponseEntity getByNome(@PathVariable(value = "nome") String nome) {
		
		try {
			List<CidadeModel> listaCidade = cidadeRepository.findByNome(nome);
			
			if (!listaCidade.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaCidade);
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
	 * Endpoint para listar as cidades pelo UF do estado
	 * @author Rafael Martins de Padua
	 * @param uf
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Lista as cidades pelo uf do estado")
	@RequestMapping(value = "/cidade/estadoUf/{uf}", method = RequestMethod.GET)
	public ResponseEntity getCidadesByEstadoUf(@PathVariable(value = "uf") String uf) {
		
		try {
			EstadoModel estado = estadoRepository.findByUf(uf);
			
			if (estado != null) {
				List<CidadeModel> filtraCidade = new ArrayList<>();
				List<CidadeModel> cidades = cidadeRepository.findAll();
				
				for (CidadeModel cidade : cidades) {
					if (cidade.getEstadoModel().getUf().equals(estado.getUf())) {
						filtraCidade.add(cidade);
					}
				}
				
				return ResponseEntity.status(HttpStatus.OK).body(filtraCidade);
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
	 * Endpoint para listar as cidades pelo nome do estado
	 * @author Rafael Martins de Padua
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Lista as cidades pelo nome do estado")
	@RequestMapping(value = "/cidade/estadoNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity getCidadesByEstadoNome(@PathVariable(value = "nome") String nome) {
		
		try {
			EstadoModel estado = estadoRepository.findByNome(nome);
			
			if (estado != null) {
				List<CidadeModel> filtraCidade = new ArrayList<>();
				List<CidadeModel> cidades = cidadeRepository.findAll();
				
				for (CidadeModel cidade : cidades) {
					if (cidade.getEstadoModel().getNome().equals(estado.getNome())) {
						filtraCidade.add(cidade);
					}
				}
				
				return ResponseEntity.status(HttpStatus.OK).body(filtraCidade);
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
	 * Endpoint para cadastrar uma nova cidade
	 * @author Rafael Martins de Padua
	 * @param cidade
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Cadastra uma nova cidade")
	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public ResponseEntity cidadeCreate(@RequestBody CidadeModel cidade) {
		
		try {
			Optional<CidadeModel> buscaCidade = cidadeRepository.findCidadeByNome(cidade.getNome());
			if (!buscaCidade.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(cidadeRepository.save(cidade));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("status: 409."
						+ "\nerror: Conflict."
						+ "\nmessage: A solicitação não pôde ser concluída devido a um conflito com o estado atual do recurso."
						+ "\nA cidade informada já existe!");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}	
	}
	
	/**
	 * Endpoint para atualizar uma cidade
	 * @author Rafael Martins de Padua
	 * @param cidadeId
	 * @param cidade
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Atualiza uma cidade")
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.PUT)
	public ResponseEntity cidadeUpdate(@PathVariable(value = "id_cidade") Long cidadeId, @RequestBody CidadeModel cidade) {
		
		try {
			Optional<CidadeModel> buscaCidade = cidadeRepository.findById(cidadeId);
			
			if (buscaCidade.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(cidadeRepository.save(cidade));
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
	 * Endpoint para remover uma cidade
	 * @author Rafael Martins de Padua
	 * @param cidadeId
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Remove uma cidade")
	@RequestMapping(value = "/cidade/{id_cidade}", method = RequestMethod.DELETE)
	public ResponseEntity cidadeDelete(@PathVariable(value = "id_cidade") Long cidadeId) {
		
		try {
			Optional<CidadeModel> cidade = cidadeRepository.findById(cidadeId);
			if (cidade.isPresent()) {
				cidadeRepository.deleteById(cidadeId);
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
