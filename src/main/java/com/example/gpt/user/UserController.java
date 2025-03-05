package com.example.gpt.user;

import cn.dev33.satoken.stp.StpUtil;
import com.example.gpt.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findUserByUsername(loginRequest.username());
        if (user != null && user.getPassword().equals(loginRequest.password())) {
            StpUtil.login(user);
            return ResponseResult.success(new LoginResponse(StpUtil.getTokenInfo(), user));
        }
        return ResponseResult.fail(null, "login fail");
    }

    @GetMapping("logout")
    public ResponseResult<String> logout() {
        StpUtil.logout();
        return ResponseResult.success("logout success");
    }

    @RequestMapping("hello")
    public ResponseResult<String> hello() {
        return ResponseResult.success("hello");
    }
}
