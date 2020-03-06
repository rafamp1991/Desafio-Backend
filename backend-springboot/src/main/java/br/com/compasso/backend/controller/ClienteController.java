package br.com.compasso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.compasso.backend.model.ClienteJsonModel;
import br.com.compasso.backend.model.ClienteModel;
import br.com.compasso.backend.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<ClienteModel> getClientesModels() {
        return clienteRepository.findAll();
    }
	
	@RequestMapping(value = "/cliente/{nome}", method = RequestMethod.GET)
	public ClienteModel GetByNome(@PathVariable(value = "nome") String nome) {
		return clienteRepository.findByNome(nome);
	}
	
	@RequestMapping(value = "/cliente/{clienteId}", method = RequestMethod.GET)
	public ClienteModel GetById(@PathVariable(value = "clienteId") long clienteId) {
		return clienteRepository.findById(clienteId);
	}
	
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ClienteModel cidadeCreate(@RequestBody ClienteModel cliente) {
		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setNome(cliente.getNome());
		clienteModel.setSobrenome(cliente.getSobrenome());
		clienteModel.setSexo(cliente.getSexo());
		clienteModel.setDataNascimento(cliente.getDataNascimento());
		clienteModel.setIdade(cliente.getIdade());
		clienteModel.setCidadeId(cliente.getCidadeId());
		return clienteRepository.save(clienteModel);
	}
	
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.PUT)
	public ClienteModel clienteUpdate(@PathVariable(value = "id_cliente") long clienteId, @RequestBody ClienteJsonModel cliente) {
		ClienteModel clienteModel = clienteRepository.findById(clienteId);
		clienteModel.setNome(cliente.getNome());
		clienteModel.setSobrenome(cliente.getSobrenome());
		clienteModel.setSexo(cliente.getSexo());
		clienteModel.setDataNascimento(cliente.getDataNascimento());
		clienteModel.setIdade(cliente.getIdade());
		clienteModel.setCidadeId(cliente.getCidadeId());
		return clienteRepository.save(clienteModel);
	}
	
	@RequestMapping(value = "/cliente/{id_cliente}", method = RequestMethod.DELETE)
	public ClienteModel clienteDelete(@PathVariable(value = "id_cliente") long clienteId) {
		return clienteRepository.deleteById(clienteId);
	}
}
