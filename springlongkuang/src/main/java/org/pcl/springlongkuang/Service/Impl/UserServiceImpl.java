package org.pcl.springlongkuang.Service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.pcl.springlongkuang.Controller.Request.ReqBindViaCode;
import org.pcl.springlongkuang.Controller.Response.RespBindViaCode;
import org.pcl.springlongkuang.Mapper.PermissionMapper;
import org.pcl.springlongkuang.Mapper.RoleMapper;
import org.pcl.springlongkuang.Mapper.UserMapper;
import org.pcl.springlongkuang.Model.User;
import org.pcl.springlongkuang.Model.UserInfo;
import org.pcl.springlongkuang.VO.WxParam;
import org.pcl.springlongkuang.Service.UserService;
import org.pcl.springlongkuang.Utils.HttpModel;
import org.pcl.springlongkuang.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Value("${consts.AppID}")
    private String AppID;
    @Value("${consts.AppSecret}")
    private String AppSecret;
    @Override
    public Map<String,Object> Check(ReqBindViaCode req) {
        Map<String, Object> resultMap =new HashMap<>();
        RespBindViaCode resp = new RespBindViaCode();

        // 根据code获取信息
        log.info("call wechat api");
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=" +  AppID + "&secret=" + AppSecret + "&js_code=" + req.getCode() + "&grant_type=authorization_code";
        System.out.println(url);
        String response = HttpModel.sendGet(url);
        JSONObject respObject = JSONObject.fromObject(response);
        System.out.println(respObject);
        if(respObject.has("errcode")){
            resultMap.put("resp",resp);
            resultMap.put("err","网络错误");
            return resultMap;
        }
        WxParam param = new WxParam();
        param.setOpenID(respObject.getString("openid"));
        param.setSessionKey(respObject.getString("session_key"));
        param.setErrMsg(null);
        param.setErrCode(null);
//        resultMap.put(param,null);

        if(param.getErrMsg()!=null){
            resultMap.put("resp",resp);
            resultMap.put("err",param.getErrMsg());
            return resultMap;
        }

        // 是否存在并绑定
        User isCreate = userMapper.Check(param.getOpenID());
        if(isCreate!=null&&isCreate.getIsValid()==1){
            log.info("logined");
            //  查询拿到response
            // isCreate.Account = req.Account
            // isCreate.Password = req.Password
            isCreate.setNickname(req.getNickname());
            isCreate.setAvatarUrl(req.getAvatar_url());
            isCreate.setOpenId(param.getOpenID());
            userMapper.Bind(isCreate);

            resp.setUser(userMapper.GetUserInfo(param.getOpenID()));
            resp.setRole(roleMapper.GetByID(resp.getUser().getRoleId()));
            resp.setPermissions(permissionMapper.GetByID(resp.getUser().getRoleId()));
            //TODO:******************************************JWT
            resp.setToken(JWTUtils.GenerateToken(resp.getUser().getOpenId(),resp.getUser().getId()));
            resultMap.put("resp",resp);
            resultMap.put("err",null);
            return  resultMap;
        }
        log.info("bind");
        //   bind用户信息并 查询拿到response
        User user = new User();
        user.setAccount(req.getAccount());
        user.setPassword(req.getPassword());
        user.setNickname(req.getNickname());
        user.setAvatarUrl(req.getAvatar_url());
        user.setOpenId(param.getOpenID());
        user.setUnionId(param.getUnionID());
        userMapper.insertSelective(user);

        //生成token
        UserInfo userInfo = userMapper.GetUser(param.getOpenID());
        if(userInfo.getUser().getOpenId().length()== 0){
            resultMap.put("resp",resp);
            resultMap.put("err","创建失败openID更新不成功检查账号密码");
            return resultMap;
        }

        resp.setUser(userInfo.getUser());
        resp.setRole(userInfo.getRole());
        resp.setPermissions(userInfo.getPermission());
        //TODO:******************************************JWT
        resp.setToken(JWTUtils.GenerateToken(resp.getUser().getOpenId(),resp.getUser().getId()));
        resultMap.put("resp",resp);
        resultMap.put("err",null);
        return resultMap;
    }

    public RespBindViaCode GetByUserID(int userID){
        RespBindViaCode resp = new RespBindViaCode();
        UserInfo userInfo = userMapper.GetUserByID(userID);
        resp.setUser(userInfo.getUser());
        resp.setRole(userInfo.getRole());
        resp.setPermissions(userInfo.getPermission());
        return resp;
    }

}
