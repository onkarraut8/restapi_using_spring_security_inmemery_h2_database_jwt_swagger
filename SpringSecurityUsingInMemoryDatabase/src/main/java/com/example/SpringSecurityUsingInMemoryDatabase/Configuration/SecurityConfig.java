package com.example.SpringSecurityUsingInMemoryDatabase.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.SpringSecurityUsingInMemoryDatabase.JWTSecurity.JwtFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String[] PUBLIC_URLS = {
			"/h2-console/**", // Allow access to h2-console
			"/v3/api-docs/**","/v2/api-docs/**","/swagger-resources/**","/swagger-ui.html", "/swagger-ui/**", "/webjars/**", // Allow access to swagger
			"/auth/signin", // Allow access to signin
			"/auth/register"
	};
	
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable()
				/* .ignoringRequestMatchers("/h2-console/**") */) // Disable CSRF for H2 console
	        .authorizeHttpRequests(authorize -> authorize
	        		               .requestMatchers(PUBLIC_URLS).permitAll() // H2 console
	        		               //.requestMatchers(HttpMethod.GET).permitAll()
	                               .requestMatchers("/auth/**").hasRole("ADMIN")
	                               .requestMatchers("/students/**", "/subjects/**").hasRole("USER")
	                               .anyRequest().authenticated())
				
			.headers(headers -> headers .frameOptions(frameOptions ->
				  frameOptions.sameOrigin()))// Allow H2 console frame options
			/*.cors(cors -> cors.configurationSource(request -> {
			    var source = new UrlBasedCorsConfigurationSource();
			    var config = new CorsConfiguration();
			    config.setAllowedOrigins(List.of("http://localhost:3000")); // Adjust this to match your frontend URL
			    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			    source.registerCorsConfiguration("/**", config);
			    return source.getCorsConfiguration(request);
			}))*/
	        .sessionManagement(session -> session
	                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

	        return http.build();
	    }
	
	
	
}


