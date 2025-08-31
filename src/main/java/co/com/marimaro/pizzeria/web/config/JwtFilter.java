package co.com.marimaro.pizzeria.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    // Se llama a la abstracción/contrato/interface y no directamente a la implementación propia
    // que se tiene en la clase UserSecurityService ya que
    // es mas fácil y seguro partir de la base, que es lo que se requiere
    // (similar a lo que pasa con JDBC y cada motor de base de datos)
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. validar que sea un Header de authorization.
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
            // Si no tiene header de seguridad, se le indica que continúe, será una solicitud que no se resolverá en el contexto de seguridad.
            filterChain.doFilter(request, response);
            return;
        }
        // 2. Validar que el JWT sea válido.
        String jwt = authorizationHeader.replace("Bearer ", "").trim();
        if (!jwtUtils.isValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 3. Cargar el usuario de UserDetailsService
        String username = jwtUtils.getJwtSubject(jwt);
        User user = (User) userDetailsService.loadUserByUsername(username);
        // 4. Cargar el usuario en el contexto de seguridad.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // Finalmente carga el usuario en el contexto de seguridad, y continúa la ejecución de la cadena de filtros
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
