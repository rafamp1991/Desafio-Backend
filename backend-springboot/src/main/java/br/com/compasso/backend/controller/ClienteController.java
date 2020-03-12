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
	public ResponseEntity<ClienteModel> GetByNome(@PathVariable(value = "nome") String nome) {
		Optional<ClienteModel> cliente = clienteRepository.findByNome(nome);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
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
			ClienteModel clienteModel = new ClienteModel();
			clienteModel.setNome(cliente.getNome());
			clienteModel.setSobrenome(cliente.getSobrenome());
			clienteModel.setSexo(cliente.getSexo());
			clienteModel.setDataNascimento(cliente.getDataNascimento());
			clienteModel.setIdade(cliente.getIdade());
			clienteModel.setCidadeModel(cliente.getCidadeModel());
			return ResponseEntity.ok(clienteRepository.save(clienteModel));
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
	public ClienteModel clienteDelete(@PathVariable(value = "id_cliente") Long clienteId) {
		//return clienteRepository.deleteById(clienteId);
		return new ClienteModel();
	}
}
