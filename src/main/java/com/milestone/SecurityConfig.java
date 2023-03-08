package com.milestone;
 
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
/**
 * // Spring security class
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
    
    @Autowired
	BCryptPasswordEncoder passwordEncoder;
    
	/**
	 * adjusts authentication to appropriate level
	 * @param auth - to authenticate users
	 * @throws Exception - Exception thrown if error while locating user
	 */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder) //hashed pass
            .dataSource(dataSource)		//database connection
            .usersByUsernameQuery("select username,password,enabled from user where username = ?")
            .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
            	;
        System.out.println(passwordEncoder.encode("pass"));
    }
    /**
	 * // Configure authentication access and roles
	 * @param http - to allow for http authentication
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.csrf().disable()
		.httpBasic()
        	.and()
		.authorizeRequests()	
			.antMatchers("/", "/register/", "/register/doRegister").permitAll()  //all can access register
			.antMatchers("/myProducts/**", "/myProducts/delProduct**", "/myProducts/purchaseProduct**", "/friends/**" ).access("hasRole('ROLE_USER')") //only users can access myProducts , or the "productsperuser" page
			.antMatchers("/products/addProduct","/products/delProduct","/products/editProduct", "/service/**").access("hasRole('ROLE_ADMIN')")  //only admin can add, delete, and edit products in the store page
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login/")
			.usernameParameter("username")	//TAKEN FROM INPUT FORM NAME
            .passwordParameter("password")	//TAKEN FROM INPUT FORM NAME
			.permitAll()
			.defaultSuccessUrl("/?login=true", true)  //shows login banner, index
			.and()
		.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.permitAll()
			.logoutSuccessUrl("/?logout=true") //shows logout banner, index
			.and()
    	.exceptionHandling().accessDeniedPage("/access-denied");

	}
	/**
	 * // Configure authentication access for anonymous users
	 * @param web - class object to imitate an anonymous user
	 * @throws Exception - Exception thrown if error while locating user
	 */
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()	  //allows anonymous users to see resources.
                .antMatchers("/resources/**", "/static/**", "/css/**", "/scripts/**", "/images/**");
    }
}