package org.generation.BlogPessoal.Controller;


import java.util.List;

import org.generation.BlogPessoal.model.Postagem;
import org.generation.BlogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")//vai aceitar requisições de qq origem
public class PostagemController {

		@Autowired
	private PostagemRepository repository; //minha interface do repository
	//usar todos serviços da interface
		
		@GetMapping
		public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
			
		}
		
		@GetMapping ("/{id}")//espero o parametro id
		public ResponseEntity <Postagem> GetById (@PathVariable long id) //escolha opcional ()
		{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<Postagem>> GetByTitulo (@PathVariable String titulo)
		{
			return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		}
}






