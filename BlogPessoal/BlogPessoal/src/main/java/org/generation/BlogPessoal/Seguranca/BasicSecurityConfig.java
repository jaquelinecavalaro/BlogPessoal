package org.generation.BlogPessoal.Seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//vamos habilitar a conf de websecurity
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService UserDetailsService; //esse userdatail esta vindo da classe websecurity, a qual eu extendi aqui nesse arquivo
	
	//vamos criar um metodo para sobrescrever
	@Override //sobre escrita de metodo
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailsService);
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//throws Exception tratativa de erros
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		http.authorizeRequests() //tanto para logar quanto para cadastrar o usuario é liberado sem uso do token
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated() //as demais necessitam de autenticação
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //n vai guardar sessao
		.and().cors()
		.and().csrf().disable(); //conf padrao
	}
}
