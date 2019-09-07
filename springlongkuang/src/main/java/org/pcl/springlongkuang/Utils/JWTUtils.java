package org.pcl.springlongkuang.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Configuration.JWTConfig.JWTConstant;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {
    public static String GenerateToken(String OpenID,int user_id){

        Date date = new Date();
        Date tokenLife = new Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
//        System.out.println(tokenLife);
        Map<String, Object> map = new HashMap<String, Object>();
        Algorithm algorithm = Algorithm.HMAC256(JWTConstant.BASE64Secret);

        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String jwt = JWT.create()
                .withHeader(map)
               // .withIssuer("SERVICE")
                .withClaim("exp", tokenLife)
                .withClaim("open_id", OpenID)
                .withClaim("user_id", user_id)
                .withExpiresAt(tokenLife)
                .sign(algorithm);

        return jwt;
    }

    /**
     * 解析token
     * @param token token
     * @return claims的内容 包括open_id和use_id
     */
    public static Map<String,Object> GetInfo(String token ){
        try {
            Map<String,Object> map=new HashMap<>();
            Algorithm algorithm = Algorithm.HMAC256(JWTConstant.BASE64Secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//        String subject = jwt.getSubject(); //token签名的主题
//        List<String> audience = jwt.getAudience(); //token 的签名观众

            Map<String, Claim> claims = jwt.getClaims(); //token的claim
            map.put("open_id",claims.get("open_id").asString());
            map.put("user_id",claims.get("user_id").asInt());
            return map;

        }catch (SignatureException se) {
            // TODO 这里自行抛出异常
            log.error("===== 密钥不匹配 =====", se);
        } catch (ExpiredJwtException ejw) {
            // TODO 这里自行抛出异常
            log.error("===== token过期 =====", ejw);
        } catch (Exception e) {
            // TODO 这里自行抛出异常
            log.error("===== token解析异常 =====", e);
        }
        return null;

    }
    /**
     * 解析token
     *
     * @param authToken    授权头部信息
     * @param base64Secret base64加密密钥
     * @return
     */
    public static Claims parseToken(String authToken, String base64Secret) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(authToken).getBody();
            return claims;
        } catch (SignatureException se) {
            // TODO 这里自行抛出异常
            log.error("===== 密钥不匹配 =====", se);
        } catch (ExpiredJwtException ejw) {
            // TODO 这里自行抛出异常
            log.error("===== token过期 =====", ejw);
        } catch (Exception e) {
            // TODO 这里自行抛出异常
            log.error("===== token解析异常 =====", e);
        }
        return null;
    }
}

