package es.urjc.code.practica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        
        

        // Private pages (all other pages)

        http.authorizeRequests().antMatchers("/home").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
       
        http.authorizeRequests().antMatchers("/adminadd").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin_add_product.html").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin_product.html").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/payment_creditcard").hasAnyRole("USER","ADMIN");
        
//        http.authorizeRequests().antMatchers("/").not().hasAnyRole("USER","ADMIN");
        
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/home");
        http.formLogin().failureUrl("/loginerror");
        
        
       

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(authenticationProvider);
    }
}
