package cn.bx.bbsdemo.utils;

/**
 * 枚举类，列出了常量信息
 */
public enum ResultInfo {

	/** 操作成功 **/
    SUCCESS (200, "操作成功"),
    /** 操作失败 **/
    FAILED(500,"操作失败"),
    /** token不存在或者过期 **/
    TOKEN_ERROR(401, "token不存在或者过期"),
    /** 无此操作权限 **/
    FORBIDDEN_ERROR(403, "无此操作权限"),
    /** 参数错误 **/
    PARAME_EMPTY(10001, "参数错误"),
    ;
	
    private int code;	// 返回码
    private String msg;	// 返回信息
    
    ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}