package good.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavaToken {
    /**
     * 公用秘钥-保存在服务器端，客户端不会知道秘钥，以防被攻击
     */
    public static String SECRET = "GoodMans";

    /**
     * 根据用户名和密码生成token
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static String createToken(String username, String password) throws Exception{

        //签发时间
        Date iatDate = new Date();

        //过期时间10天
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();

        //生成token
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("username","username")
                .withClaim("password","password")
                .withExpiresAt(expiresDate)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 解密token
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

}
