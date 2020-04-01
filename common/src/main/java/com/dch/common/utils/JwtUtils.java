package com.dch.common.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类
 */
public final class JwtUtils {

    /**
     * 生成token
     *
     * @param claims    自定义荷载
     * @param expire    有效期限
     * @param timeUnit  期限单位
     * @param secretKey 自定义密钥
     * @return
     */
    public static String createToken(Map<String, Object> claims, int expire, TimeUnit timeUnit, String secretKey) {
        JwtBuilder builder = Jwts.builder()
                // 签名
                .signWith(SignatureAlgorithm.HS512, secretKey)
                // 公共属性 JWT的签发者
                .setIssuer("io.dch")
                // 公共属性 JWT签发时间
                .setIssuedAt(new Date())
                // 公共属性 JWT过期时间
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(expire, timeUnit)));

        // 自定义载荷
        if (null != claims && claims.size() > 0) {
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                builder.claim(entry.getKey(), entry.getValue());
            }
        }

        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token     被解密token
     * @param secretKey 自定义密钥
     * @return
     */
    public static Map<String, Object> decodeToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
