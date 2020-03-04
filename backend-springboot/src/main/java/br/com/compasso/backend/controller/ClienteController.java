package br.com.compasso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.controller.dto.ClienteDto;
import br.com.compasso.backend.model.ClienteModel;
import br.com.compasso.backend.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<ClienteModel> getClienteModels() {
        return clienteRepository.findAll();
    }
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	public ClienteModel GetById(@PathVariable(value = "id") long id) {
		return clienteRepository.findById(id);
	}
	
	@RequestMapping(value = "/clientes/{nome}", method = RequestMethod.GET)
	public ClienteModel GetByNome(@PathVariable(value = "nome") String nome) {
		return clienteRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.POST)
	public ClienteModel clienteCreate(@RequestBody ClienteModel cliente) {
		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setNome(cliente.getNome());
		clienteModel.setSobrenome(cliente.getSobrenome());
		return clienteRepository.save(clienteModel);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.PUT)
	public ClienteModel clienteUpdate(@PathVariable(value = "id") long id, @RequestBody ClienteDto cliente) {
		ClienteModel clienteModel = clienteRepository.findById(id);
		clienteModel.setNome(cliente.getNome());
		clienteModel.setSobrenome(cliente.getSobrenome());
		return clienteRepository.save(clienteModel);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
	public ClienteModel clienteDelete(@PathVariable(value = "id") long id) {
		return clienteRepository.deleteById(id);
	}

}
