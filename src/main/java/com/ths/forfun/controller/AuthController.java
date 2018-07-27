package com.ths.forfun.controller;

import com.ths.forfun.pojo.User;
import com.ths.forfun.pojo.vo.ResultVO;
import com.ths.forfun.pojo.vo.ResultVOFactory;
import com.ths.forfun.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * com.ths.forfun.controller
 *
 * @author: wangqd
 * @since: 2018/7/20
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultVO<Void> login(@RequestBody User user) {
        authService.login(user);
        return ResultVOFactory.buildSuccess();
    }
}
