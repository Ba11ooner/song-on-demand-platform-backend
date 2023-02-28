package edu.hitwh.werunassignment.common;

public class ResultUtils {
    //成功信息
    public static<T> BaseResponse<T> success(T data){
        return new BaseResponse<>(200,data,"OK","");
    }

    //失败信息
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse(errorCode,null);
    }

    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code,null,message,description);
    }

}
