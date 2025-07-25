package it.uniroma3.romatremotors.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.romatremotors.model.Credentials.ADMIN_ROLE;
import static it.uniroma3.romatremotors.model.Credentials.CLIENTE_ROLE;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Provo l'autenticazione");
		auth.jdbcAuthentication().dataSource(dataSource)
			.authoritiesByUsernameQuery("SELECT username, ruolo from credentials WHERE username = ?")
			.usersByUsernameQuery("SELECT username, password_encode, 1 as enabled FROM credentials WHERE username = ?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain configure(final HttpSecurity http) throws Exception {
	    http
	        .cors().disable()
	        .csrf().disable()
	        .authorizeHttpRequests()
	            .requestMatchers(HttpMethod.GET, "/", "/index", "/punti-vendita/**", "/catalogo", "/catalogo/filtra", "/registrazione", "/formRegistrazione", "/css/**", "/auto/**", "/images/**", "favicon.ico", "/filtroCategoria/**", "/filtroCarburante/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/registrazione", "/formRegistrazione", "/login", "/catalogo/filtra").permitAll()
	            
	            .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
	            .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
	            
	            .requestMatchers(HttpMethod.GET, "/cliente/**", "/cliente/prenotatestdrive").hasAnyAuthority(CLIENTE_ROLE)
	            .requestMatchers(HttpMethod.POST, "/cliente/**", "/cliente/prenotatestdrive").hasAnyAuthority(CLIENTE_ROLE)
	            .anyRequest().authenticated()
	        .and()
	            .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .successHandler(loginSuccessHandler)
	        .and()
	            .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .clearAuthentication(true)
	            .permitAll();

	    return http.build();
	}
}
