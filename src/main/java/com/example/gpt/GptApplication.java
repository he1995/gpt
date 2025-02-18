package com.example.gpt;

import cn.dev33.satoken.SaManager;
import com.example.gpt.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GptApplication {

	@Autowired
	private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GptApplication.class, args);
		System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
	}

}
