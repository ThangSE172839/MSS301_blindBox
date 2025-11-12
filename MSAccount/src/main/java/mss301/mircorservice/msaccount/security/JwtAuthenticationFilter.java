package mss301.mircorservice.msaccount.security;

import mss301.mircorservice.msaccount.model.SystemAccount;
import mss301.mircorservice.msaccount.repostiory.AccountRepository;
import mss301.mircorservice.msaccount.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    public JwtAuthenticationFilter(JwtService jwtService, AccountRepository accountRepository) {
        this.jwtService = jwtService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);

            if (email != null) {
                Optional<SystemAccount> accountOpt = accountRepository.findByEmail(email);

                if (accountOpt.isPresent()) {
                    SystemAccount account = accountOpt.get();

                    // Kiểm tra token hợp lệ VÀ account còn active
                    if (jwtService.validateToken(token, email) && account.isActive()) {
                        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + account.getRole());
                        UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, null, List.of(authority));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        } catch (Exception e) {
            // Token không hợp lệ, bỏ qua
        }

        filterChain.doFilter(request, response);
    }
}
