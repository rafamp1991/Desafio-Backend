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
import br.com.compasso.backend.model.ClienteModel;
import br.com.compasso.backend.repository.CidadeRepository;
import br.com.compasso.backend.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	/**
	 * Endpoint para listar todos os clientes
	 * @author Rafael Martins de Padua
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public ResponseEntity getClientesModels() {
		try {
			List<ClienteModel> listaClientes = clienteRepository.findAll();
			
			if (!listaClientes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
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
	 * Endpoint para listar todos os clientes pelo nome
	 * @author Rafael Martins de Padua
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/clienteNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity GetByNome(@PathVariable(value = "nome") String nome) {
		
		try {
			List<ClienteModel> listaClientes = clienteRepository.findByNome(nome);
			
			if (!listaClientes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
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
	 * Endpoint para consultar um cliente pelo ID
	 * @author Rafael Martins de Padua
	 * @param clienteId
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/clienteId/{clienteId}", method = RequestMethod.GET)
	public ResponseEntity GetById(@PathVariable(value = "clienteId") Long clienteId) {
		
		try {
			Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
			
			if (cliente != null) {
				return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
						+ "\nerror: Bad Request."
						+ "\nmessage: Solicitação inválida.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("status: 404."
					+ "\nerror: Not Found."
					+ "\nmessage: Não foi possível encontrar o recurso especificado.");
		}
	}
	
	/**
	 * Endpoint para cadastrar um novo cliente
	 * @author Rafael Martins de Padua
	 * @param cliente
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity clienteCreate(@RequestBody ClienteModel cliente) {
		
		try {
			ClienteModel buscaCliente = clienteRepository.findByNomeAndSobrenome(cliente.getNome(), cliente.getSobrenome());
			
			if (buscaCliente == null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
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
	 * Endpoint para atualizar um cliente
	 * @author Rafael Martins de Padua
	 * @param clienteId
	 * @param cliente
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.PUT)
	public ResponseEntity clienteUpdate(@PathVariable(value = "id_cliente") Long clienteId, @RequestBody ClienteModel cliente) {
		
		try {
			cliente.setCidadeModel(cidadeRepository.findById(cliente.getCidadeModel().getCidadeId()).get());
			return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status: 400."
					+ "\nerror: Bad Request."
					+ "\nmessage: Solicitação inválida.");
		}
	}
	
	/**
	 * Endpoint para remover um cliente
	 * @author Rafael Martins de Padua
	 * @param clienteId
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.DELETE)
	public ResponseEntity clienteDelete(@PathVariable(value = "id_cliente") Long clienteId) {
		
		try {
			Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
			
			if (cliente.isPresent()) {
				clienteRepository.deleteById(clienteId);
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
