package rs.wepublishlaws.paymentserviceprovider.security.auth;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.wepublishlaws.paymentserviceprovider.security.util.TokenReader;
import rs.wepublishlaws.paymentserviceprovider.security.util.TokenValidator;

import java.io.IOException;

@AllArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenReader tokenReader;
    private final TokenValidator tokenValidator;

    private UserDetailsService userDetailsService;

//    protected final Log LOGGER = LogFactory.getLog(getClass());


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain
    ) throws ServletException, IOException {
        String username;
        String authToken = tokenReader.getToken(request);
        try {

            if (authToken != null) {
                username = tokenValidator.getUsernameFromToken(authToken);
                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (tokenValidator.validateToken(authToken, userDetails)) {
                        TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                        authentication.setToken(authToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

        } catch (ExpiredJwtException ex) {
//            LOGGER.debug("Token expired!");
        }

        chain.doFilter(request, response);
    }
}