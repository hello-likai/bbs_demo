package cn.bx.bbsdemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>返回数据
 */
public class ResultUtils {
	
	public static Map<String, Object> getResult(ResultInfo result) {
		Map<String, Object> resultData = new HashMap<>();
		resultData.put("code", result.getCode());
		resultData.put("msg", result.getMsg());
		return resultData;
	}
	
	public static Map<String, Object> getResult(ResultInfo result, String msg) {
		Map<String, Object> resultData = new HashMap<>();
		resultData.put("code", result.getCode());
		if(StringUtils.isNotBlank(msg)) {
			resultData.put("msg", msg);
		}else {
			resultData.put("msg", result.getMsg());
		}
		return resultData;
	}
	
	public static Map<String, Object> getResult(ResultInfo result, String msg, Object pd) {
		Map<String, Object> resultData = new HashMap<>();
		resultData.put("code", result.getCode());
		if(StringUtils.isNotBlank(msg)) {
			resultData.put("msg", msg);
		}else {
			resultData.put("msg", result.getMsg());
		}
		resultData.put("data", pd);
		return resultData;
	}
	
	public static Map<String, Object> getResult(int code, String msg, Object pd) {
		Map<String, Object> resultData = new HashMap<>();
		resultData.put("code", code);
		resultData.put("msg", msg);
		resultData.put("data", pd);
		return resultData;
	}
	
}
