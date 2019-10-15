package com.lixin.bootjwtserver.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class GloableException {
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Object  HandlerException(Exception e)
	{
		String  messgae=e.getMessage();
		
		JSONObject   obj = new JSONObject();
		obj.put("errormsg", "对不起,出现异常，请检查!");
		
		return  obj;
	}

}
