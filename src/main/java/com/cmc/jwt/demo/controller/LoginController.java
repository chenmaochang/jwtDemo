package com.cmc.jwt.demo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.jwt.demo.common.AjaxResult;
import com.cmc.jwt.demo.entity.User;
import com.cmc.jwt.demo.service.IUserService;
import com.cmc.jwt.demo.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	@Resource
	IUserService userService;
	@PostMapping("login")
	public AjaxResult login(@RequestBody Map<String, String> map) {
		String loginName = map.get("loginName");
		String passWord = map.get("passWord");
		// 身份验证
		boolean isSuccess = userService.checkUser(loginName, passWord);
		if (isSuccess) {
			// 模拟数据库查询
			User user = userService.getUser(loginName);
			if (user != null) {
				// 返回token
				String token = JwtUtil.sign(loginName, passWord);
				if (token != null) {
					return AjaxResult.success("成功", token);
				}
			}
		}
		return AjaxResult.fail();
	}
	
	@GetMapping("getUser")
    public AjaxResult getUserInfo(HttpServletRequest request,@RequestParam("loginName")String loginName){
        String token = request.getHeader("token");
        boolean verity = JwtUtil.verity(token);
        if (verity) {
            User user = userService.getUser(loginName);
            if (user != null) {
                return AjaxResult.success("成功", user);
            }
        }
        return AjaxResult.fail();
    }

}
