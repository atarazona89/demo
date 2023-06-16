package com.example.demo.Util;

import com.example.demo.Entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "ZVRxTWR5VVlRQ1hQZHBzZWhKT2J0MUUwSXdmOXprMGVESEJlQjdUdFU1YUd5UHoxeFFOQU9mbFBuSjFxY3BmZTBYRjVzME5hanBMZ0laTmdBTUhmaGJMV1h2TnNoeWJiUnZUb3NOd2FnSHFjWDlvbzdyazQ2aGxUSUtMTUtqYUFBUXdBMDMxSXAyV1Q0QWJxTlZvWXlVdW1DVTNxRDBmN2xkZnljUVc3VUdnOUVTQnJTS1Iwc0RzNlFvV24wUDVyUDVwZmJXbXdxN1hUdGcyOFBOZWVxTHZJNFdxeVZ0YlpCbkJHMUIyd2hhMmhOQ05meHl4MFk1a01jYkZlWjZIbGdsejNhNzJ3UWhVeVlCdHFVSm5VTzl2emZmaTdHUlc3VzlOaEcwZDdYeHNT";

    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    public static String generateToken(UserEntity userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
