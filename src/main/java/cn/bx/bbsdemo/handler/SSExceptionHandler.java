package cn.bx.bbsdemo.handler;

import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 异常处理类
 */
@ControllerAdvice
public class SSExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> handler(Exception e){
       e.printStackTrace();
       return ResultUtils.getResult(ResultInfo.FAILED,"登录失败："+e);
    }
}
