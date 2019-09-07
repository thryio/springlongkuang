package org.pcl.springlongkuang.Configuration.JWTConfig;//package org.pcl.springlongkuang.Configuration.JWTConfig;
//
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.pcl.tms.Model.JWTParam;
//import org.pcl.tms.Utils.JWTUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.util.StringUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
//@Slf4j
//public class JWTInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JWTParam jwtParam;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
////        if (handler instanceof HandlerMethod) {
////            HandlerMethod handlerMethod = (HandlerMethod) handler;
////            JWTIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JWTIgnore.class);
////
////            if (jwtIgnore != null) {
////                return true;
////            }
////        }
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod=(HandlerMethod)handler;
//        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(JWTIgnore.class)) {
//            JWTIgnore jwtIgnore = method.getAnnotation(JWTIgnore.class);
//            if (jwtIgnore.required()) {
//                return true;
//            }
//        }
//
//        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
//
//        final String authHeader = request.getHeader(JWTConstant.AUTH_HEADER_KEY);
//
//        if (StringUtils.isEmpty(authHeader)) {
//            // TODO 这里自行抛出异常
//            log.info("===== 用户未登录, 请先登录 =====");
//            response.getOutputStream().write("token为空异常".getBytes());
//            return false;
//        }
//
//        // 校验头格式校验
//        if (!JWTUtils.validate(authHeader)) {
//            // TODO 这里自行抛出异常
//            log.info("===== token格式异常 =====");
//            response.getOutputStream().write("token格式异常".getBytes());
//            return false;
//        }
//
//        // token解析
//        final String authToken = JWTUtils.getRawToken(authHeader);
//        Claims claims = JWTUtils.parseToken(authToken, jwtParam.getBase64Secret());
//        if (claims == null) {
//            log.info("===== token解析异常 =====");
//            response.getOutputStream().write("token解析异常".getBytes());
//            return false;
//        }
//        // 传递所需信息
//        request.setAttribute("CLAIMS", claims);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
//                                Object handler, Exception ex) throws Exception {
//
//    }
//}
