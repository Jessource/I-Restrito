package com.serasa.erestrito.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.serasa.erestrito.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

	@Value("${security.jwt.token.secret-key:secreto}")
	private String secretKey = "secreto";

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validade = 3600000;

	public String generateToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + validade);
		Claims claims = Jwts.claims();
		claims.put("role", usuario.getPerfil().toString());

		return Jwts.builder()
				.setClaims(claims)
				.setIssuer(usuario.getNome())
				.setSubject(usuario.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
