package org.generation.BlogPessoal.Service;

import java.nio.charset.Charset;

import java.util.Optional;
import org.apache.commons.codec.binary.Base64; //importei na mao

import org.generation.BlogPessoal.model.UserLogin;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> CadastrarUsuario (Usuario usuario) {
		
		if(repository.findByUsuario(usuario.getUsuario()).isPresent())
			return null;
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEnconder = encoder.encode (usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return Optional.of(repository.save(usuario)); //salvamos a senha ja incriptada
		
	}
	
	//vamos ditar tudo oq se refere a logar
	
	public Optional<UserLogin> Logar (Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		//aqui to fazendo a pesquisa q eu quero
		//agora vamos a condição
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				//vamos aplicar a regra de negocio para devolver a senha encriptada
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64 (auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodedAuth);
				
				//vamos preencher o token
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				
				return user;
			}
		}
		//se n entrar dentro do if
		
		return null;
		
	}
}
