package amintabite.U5_W3_D5.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(formLogin -> formLogin.disable());
        //escludo log in perche' lo faremo su front end
        httpSecurity.csrf(csrf -> csrf.disable());

        //protezione da csrf al momento non serve

        httpSecurity.sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/**").permitAll());


        return  httpSecurity.build();

    }

}
