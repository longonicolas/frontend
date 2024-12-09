package com.accenture.ecommerce.auth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "2f1f5a333e5d3b112edf21903abbacce378a5d5e6b33a6d953c31103a23ed82826cef75a787cc71f68495340630bc9f2370750a5564f22b5676bf375a93fd7c8ee7e9ff97562a2abaa5e7385709ca1746a1083609440e0b3430d4da85d6fe2b5f224ed6ce5979315c36fa624a74c738b8602f7ea1fbd9b57dcc2b447a25be7fe942cefccb56301b151150123a682b2f22895df5cb9a4259f9664525cc70574b4a4b5dc9f94510a56c1827b52d93f1422b162297ce841741cf39ad75f8994050a146eec5fdb0382d7720996b7f2cda190c5ecc8f0cb7ba90232ab2821772adbf1f16d74bd993e425c447e82ebd2cde0ede686e4d84ca9cb4e338e9cd31f481937";
    private static final Long EXPIRATION_TIME = 86400000L; // 24 horas (en ms)
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        return Jwts.builder().setSubject(authentication.getName()).claim("roles", roles).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extraerUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

}
