package com.deliveryservice.api.domain.token.helper;

import com.deliveryservice.api.common.error.TokenErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.token.ifs.TokenHelperIfs;
import com.deliveryservice.api.domain.token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class jwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour); //토큰 만료시간

        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());//현재시스템 시간

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());// 키생성

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact(); // 토큰생성

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour); //토큰 만료시간

        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());//현재시스템 시간

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());// 키생성

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact(); // 토큰생성

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes()); // 키생성

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            var result = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());

        } catch (Exception e) {

            if (e instanceof SignatureException) {
                // 토큰이 유효하지 않을때
                throw new ApiException(TokenErrorCode.INVALID_TOKEN,e);
            } else if (e instanceof ExpiredJwtException) {
                //만료된 토큰
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN,e);
            } else {
                //그외 에러
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION,e);
            }
        }

    }
}
