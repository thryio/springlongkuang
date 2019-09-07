package org.pcl.springlongkuang.Controller;

import com.auth0.jwt.algorithms.Algorithm;
import org.pcl.springlongkuang.Configuration.JWTConfig.JWTConstant;
import org.pcl.springlongkuang.Controller.Request.QiNiuToken;
import org.pcl.springlongkuang.Controller.Response.RespBindViaCode;
import org.pcl.springlongkuang.Service.UserService;
import org.pcl.springlongkuang.Utils.JWTUtils;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.pcl.springlongkuang.Utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/token")
    public ResponseVo<?> PostToken(HttpServletRequest request){
        Algorithm algorithm = Algorithm.HMAC256(JWTConstant.BASE64Secret);
        String token = request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length());
//        String s = token.substring(7, request.getHeader("Authorization").length());
//        System.out.println(s);
        System.out.println(token);
        Map<String, Object> claimMap = JWTUtils.GetInfo(token);
        Object user_id = claimMap.get("user_id");
        RespBindViaCode data = userService.GetByUserID((Integer) user_id);

        return ResponseVo.success(data);

    }

    @PostMapping("/qiniu")
    public ResponseVo<?> PostQiNiu(@RequestBody QiNiuToken qiNiuToken){
        String access_key = qiNiuToken.getAccess_key();
        String secret_key = qiNiuToken.getSecret_key();
        String bucket = qiNiuToken.getBucket();
        if (access_key == null || access_key.isEmpty() || secret_key == null || secret_key.isEmpty() ||
                bucket == null || bucket.isEmpty()){
            return ResponseVo.error("参数不能为空，参数：access_key,secret_key,bucket");
        }
        TokenHelper tokenHelper = TokenHelper.create(access_key, secret_key);
        String token = tokenHelper.getToken(bucket);
        System.out.println(token);
        return ResponseVo.success(token);
    }
}
