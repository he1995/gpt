package com.example.gpt.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.gpt.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public ResponseResult<SaTokenInfo> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            StpUtil.login(user);
            return ResponseResult.success(StpUtil.getTokenInfo());
        }
        return ResponseResult.fail(null, "login fail");
    }

    @RequestMapping("hello")
    public ResponseResult<String> hello() {
        return ResponseResult.success("hello");
    }
}
