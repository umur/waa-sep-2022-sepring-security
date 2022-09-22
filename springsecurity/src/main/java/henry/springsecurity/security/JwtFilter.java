package henry.springsecurity.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("In");
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            log.info("Has Bearer token");
            String token = authorizationHeader.substring(7);
            // Validate
            boolean isTokenValid = jwtHelper.validateToken(token);
            var email = jwtHelper.getUsernameFromToken(token);
            log.info(String.valueOf(isTokenValid));
            log.info(email);

            if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Go to DB
                var userDetails = userDetailsService.loadUserByUsername(email);
                log.info(userDetails.toString());
                log.info(userDetails.getAuthorities().toString());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Store in the context
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        log.info("doFilter on filterChain");
        filterChain.doFilter(request, response);
    }
}
