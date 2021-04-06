package org.generation.BlogPessoal.repository;

import java.util.Optional;

import org.generation.BlogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

	//vamos criar um metodo para pegar um usuario pelo username
	public Optional<Usuario> findByUsuario(String usuario);
	//esse ByUsuario é exatamente o nome da minha variável usuario, localizada na tabela usuario
}
