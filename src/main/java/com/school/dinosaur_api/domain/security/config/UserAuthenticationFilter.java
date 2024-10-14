package com.school.dinosaur_api.domain.security.config;

import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.repository.UserRepository;
import com.school.dinosaur_api.domain.security.model.ModelUserDetails;
import com.school.dinosaur_api.domain.security.model.User;
import com.school.dinosaur_api.domain.security.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenService jwtTokenService;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (verificaEndpointsPublicos(request)) {
            String token = recuperaToken(request);
            if (token != null) {
                String subject = jwtTokenService.pegarToken(token);
                User user = userRepository.findByLogin(subject).orElseThrow(() -> new BusinessException("User not found"));
                ModelUserDetails modelUserDetails = new ModelUserDetails(user);
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                modelUserDetails.getUsername(),
                                null,
                                modelUserDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Token does not exist");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean verificaEndpointsPublicos(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
//        return !Arrays.asList("/auth/users/login", "/auth/users").contains(requestURI);
        String uriPublic = ("/auth/users/login");
        return !uriPublic.contains(requestURI); // Se a URI da requisição não contem na uriPublic, retorna True
    }

    private String recuperaToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
