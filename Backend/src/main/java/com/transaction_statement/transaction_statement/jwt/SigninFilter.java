package com.transaction_statement.transaction_statement.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction_statement.transaction_statement.user.dto.CustomUserDetails;
import com.transaction_statement.transaction_statement.user.dto.SigninRequestDto;
import com.transaction_statement.transaction_statement.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class SigninFilter extends UsernamePasswordAuthenticationFilter {

    // user 정보 검증
    private final AuthenticationManager authenticationManager;

    // JWTUtil 주입
    private final JWTUtil jwtUtil;

    public SigninFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SigninRequestDto dto = objectMapper.readValue(request.getInputStream(), SigninRequestDto.class);
            String email = dto.getEmail();
            String password = dto.getPassword();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        User user = customUserDetails.getUser();

        String username = user.getUsername();
        String email = customUserDetails.getUsername();
        String role = authResult.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        String token = jwtUtil.createJwt(email, role, 60 * 60 * 1000L);

        // 헤더에 추가
        response.setHeader("Authorization", "Bearer " + token);

        // CORS를 위한 헤더 노출
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // Content-Type 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답 생성
        String json = String.format("{\"username\":\"%s\", \"email\":\"%s\"}", username, email);
        response.getWriter().write(json);
        response.getWriter().flush();
    }

    // 로그인 실패시
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
