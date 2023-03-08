package com.milestone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.milestone.business.ProductBusinessServiceInterface;
import com.milestone.business.RegistrationBusinessService;
import com.milestone.business.RegistrationBusinessServiceInterface;
import com.milestone.business.FriendsBusinessService;
import com.milestone.business.FriendsBusinessServiceInterface;
import com.milestone.business.ProductBusinessService;

/**
 * Configuration class for Springboot
 * @author Michal Uchmanowicz
 *
 */
@Configuration
public class SpringConfig {
	
	/**
	 *  returns ProductBusinessService as a bean
	 * @return ProductBusinessService to be used by productController
	 */
	@Bean(name="ProductBusinessService", initMethod="init", destroyMethod = "destroy")	//instantiate a beans
	public ProductBusinessServiceInterface getProductBusiness() 
	{
		return new ProductBusinessService();
	}
	/**
	 *  returns RegistrationBusinessService as a bean
	 * @return RegistrationBusinessService to be used by registertController
	 */
	@Bean(name="RegistrationBusinessService", initMethod="init", destroyMethod = "destroy")	//instantiate a bean
	public RegistrationBusinessServiceInterface getRegistrationBusiness()
	{
		return new RegistrationBusinessService();
	}
	
	/**
	 *  returns RegistrationBusinessService as a bean
	 * @return RegistrationBusinessService to be used by registertController
	 */
	@Bean(name="FriendsBusinessService", initMethod="init", destroyMethod = "destroy")	//instantiate a bean
	public FriendsBusinessServiceInterface getFriendsBusiness()
	{
		return new FriendsBusinessService();
	}

	/**
	 *  returns BCryptPasswordEncoder as a bean
	 * @return BCryptPasswordEncoder to be used by SecurityConfig and RegisterController
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
