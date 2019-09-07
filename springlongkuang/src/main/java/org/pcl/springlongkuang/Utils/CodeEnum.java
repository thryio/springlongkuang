package org.pcl.springlongkuang.Utils;

import lombok.Getter;

@Getter
public enum CodeEnum {
    success(200, "success"),
    user_not_found(10001, "用户不存在"),
    validate_fail(10002, "请求参数错误（格式/长度等非法）"),
    database_error(10003, "数据库读写错误"),
    not_found_error(10004, "请求内容不存在"),
    insert_error(10005, "创建失败！请检查请求参数。"),
    frequently_error(10006, "操作频繁"),
    auth_error(10401, "认证失败"),
    permission_error(10403, "权限错误"),
    unknown_error(99999, "服务器未知错误");

    int code;
    String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
