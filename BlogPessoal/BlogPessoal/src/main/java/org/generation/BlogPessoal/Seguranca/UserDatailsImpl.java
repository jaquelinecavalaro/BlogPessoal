package org.generation.BlogPessoal.Seguranca;

import java.util.Collection;
import java.util.List;

import org.generation.BlogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDatailsImpl implements UserDetails{
	//2 CLASSE DE SECURITY A SER FEITA

	//vamos criar uma classe seralizable
	
	private static final long serialVersionUID = 1L; //PARA CONTROLE INTERNO
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserDatailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
		
	}
	
	public UserDatailsImpl() {} //criar construtor vazio
	
	
	//TEMOS Q ADD TODOS OS METODOS DA IMPLEMENTACAO
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password; //alterei de null para password
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName; //alterei de null para userName
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; //alterei para true
	}
//TOQUEI TODOS DE FALSE PARA TRUE
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
