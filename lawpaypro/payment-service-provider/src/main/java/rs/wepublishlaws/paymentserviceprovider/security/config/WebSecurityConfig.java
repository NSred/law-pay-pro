package rs.wepublishlaws.paymentserviceprovider.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import rs.wepublishlaws.paymentserviceprovider.security.auth.RestAuthenticationEntryPoint;
import rs.wepublishlaws.paymentserviceprovider.security.auth.TokenAuthenticationFilter;
import rs.wepublishlaws.paymentserviceprovider.security.userdetailservice.CustomUserDetailsService;
import rs.wepublishlaws.paymentserviceprovider.security.util.TokenReader;
import rs.wepublishlaws.paymentserviceprovider.security.util.TokenValidator;

@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Autowired
    private TokenReader tokenReader;

    @Autowired
    private TokenValidator tokenValidator;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(restAuthenticationEntryPoint)
        );

        // Configure request authorization
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/users/login").permitAll()
                // Uncomment and configure other request matchers as needed
                // .requestMatchers(HttpMethod.POST, "/api/account/host").permitAll()
                // .requestMatchers(HttpMethod.PUT, "/api/account/**").hasAnyAuthority("Host", "Guest")
                // .requestMatchers(HttpMethod.DELETE, "/api/account/**").hasAnyAuthority("Host", "Guest")
                .anyRequest().permitAll()
        );

        http.cors(cors -> {});

        http.csrf(csrf -> csrf.disable());
        http.addFilterBefore(new TokenAuthenticationFilter(
                tokenReader,
                tokenValidator,
                userDetailsService()),
                BasicAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}
