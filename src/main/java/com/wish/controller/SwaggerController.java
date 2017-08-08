package com.wish.controller;

import com.wish.model.ExecuteResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年8月8日 上午9:54:45
 */
@RestController
@RequestMapping("swagger")
public class SwaggerController {

	@RequestMapping(value = "testSawgger", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "测试swagger", httpMethod = "POST", notes = "testSawgger")
	public ExecuteResult<String> addUser(@ApiParam(value = "参数", required = true) Long id) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			result.setData("这是一个测试！！");
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

}
