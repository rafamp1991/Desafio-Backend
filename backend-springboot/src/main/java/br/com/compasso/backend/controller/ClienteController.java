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
	
	@CrossOrigin
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<ClienteModel> getClientesModels() {
        return clienteRepository.findAll();
    }
	
	@CrossOrigin
	@RequestMapping(value = "/clienteNome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteModel>> GetByNome(@PathVariable(value = "nome") String nome) {
		List<ClienteModel> listaCliente = clienteRepository.findByNome(nome);
		if (listaCliente != null) {
			return ResponseEntity.ok(listaCliente);
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/clienteId/{clienteId}", method = RequestMethod.GET)
	public ResponseEntity<ClienteModel> GetById(@PathVariable(value = "clienteId") Long clienteId) {
		Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
		if (cliente != null) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<ClienteModel> clienteCreate(@RequestBody ClienteModel cliente) {
		ClienteModel buscaCliente = clienteRepository.findByNomeAndSobrenome(cliente.getNome(), cliente.getSobrenome());
		if (buscaCliente == null) {
			return ResponseEntity.ok(clienteRepository.save(cliente));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.PUT)
	public ClienteModel clienteUpdate(@PathVariable(value = "id_cliente") Long clienteId, @RequestBody ClienteModel cliente) {
		cliente.setCidadeModel(cidadeRepository.findById(cliente.getCidadeModel().getCidadeId()).get());
		return clienteRepository.save(cliente);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.DELETE)
	public ResponseEntity<ClienteModel> clienteDelete(@PathVariable(value = "id_cliente") Long clienteId) {
		Optional<ClienteModel> cliente = clienteRepository.findById(clienteId);
		if (cliente.isPresent()) {
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
