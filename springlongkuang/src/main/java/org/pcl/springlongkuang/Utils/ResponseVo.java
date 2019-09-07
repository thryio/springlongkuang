package org.pcl.springlongkuang.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {

    // 成功标记
    private boolean success;
    // 提示信息
    private String msg;

    // 具体数据
    private T data;

    public static ResponseVo success() {
        return ResponseVo.builder()
                .success(true)
//                .msg(CodeEnum.success.getMsg())
                .msg("")
                .build();
    }

    public static ResponseVo error() {
        return ResponseVo.builder()
                .success(false)
                .msg(CodeEnum.unknown_error.getMsg())
                .build();
    }

    public static ResponseVo success(Boolean success) {
        if(success){
            return ResponseVo.success();
        }else{
            return ResponseVo.error();
        }
    }

    public static ResponseVo success(Object data) {
        return ResponseVo.builder()
                .success(true)
                .msg(CodeEnum.success.getMsg())
                .data(data)
                .build();
    }

    public static ResponseVo success(Object data,String msg) {
        return ResponseVo.builder()
                .success(true)
                .msg(msg)
                .data(data)
                .build();
    }

    public static ResponseVo error(CodeEnum codeEnum) {
        return ResponseVo.builder()
                .success(false)
                .msg(codeEnum.getMsg())
                .build();
    }

    public static ResponseVo error(CodeEnum codeEnum, String msg) {
        return ResponseVo.builder()
                .success(false)
                .msg(msg)
                .build();
    }
    public static ResponseVo error( String msg) {
        return ResponseVo.builder()
                .success(false)
                .msg(msg)
                .build();
    }

}
