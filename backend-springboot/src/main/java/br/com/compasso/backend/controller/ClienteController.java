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
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteModel>> getClientesModels() {
		try {
			List<ClienteModel> listaClientes = clienteRepository.findAll();
			
			if (!listaClientes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
			} else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
    }
	
	/**
	 * @param nome
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/clienteNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteModel>> GetByNome(@PathVariable(value = "nome") String nome) {
		
		try {
			List<ClienteModel> listaClientes = clienteRepository.findByNome(nome);
			
			if (!listaClientes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * @param clienteId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/clienteId/{clienteId}", method = RequestMethod.GET)
	public ResponseEntity<ClienteModel> GetById(@PathVariable(value = "clienteId") Long clienteId) {
		
		try {
			Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
			
			if (cliente != null) {
				return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<ClienteModel> clienteCreate(@RequestBody ClienteModel cliente) {
		
		try {
			ClienteModel buscaCliente = clienteRepository.findByNomeAndSobrenome(cliente.getNome(), cliente.getSobrenome());
			
			if (buscaCliente == null) {
				return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * @param clienteId
	 * @param cliente
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.PUT)
	public ResponseEntity<ClienteModel> clienteUpdate(@PathVariable(value = "id_cliente") Long clienteId, @RequestBody ClienteModel cliente) {
		
		try {
			cliente.setCidadeModel(cidadeRepository.findById(cliente.getCidadeModel().getCidadeId()).get());
			return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * @param clienteId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.DELETE)
	public ResponseEntity<ClienteModel> clienteDelete(@PathVariable(value = "id_cliente") Long clienteId) {
		
		try {
			Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
			
			if (cliente.isPresent()) {
				clienteRepository.deleteById(clienteId);
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
