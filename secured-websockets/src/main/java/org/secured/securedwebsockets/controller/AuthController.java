package org.secured.securedwebsockets.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.secured.securedwebsockets.request.AuthReqDto;
import org.secured.securedwebsockets.response.AuthRespDto;
import org.secured.securedwebsockets.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    public final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager, @Qualifier("userDetailsService") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRespDto> login(@RequestBody AuthReqDto authReqDto, HttpServletResponse response) {
        log.info("login request received for user: {}", authReqDto.username());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReqDto.username(), authReqDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authReqDto.username());
        String token = jwtTokenUtil.generateToken(userDetails);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        log.info("login success for user: {}", authReqDto.username());

        return ResponseEntity.ok(AuthRespDto.builder().token(token).username(userDetails.getUsername()).build());
    }
}
