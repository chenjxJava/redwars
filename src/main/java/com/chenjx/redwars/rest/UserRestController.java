package com.chenjx.redwars.rest;

import com.alibaba.fastjson.JSON;
import com.chenjx.redwars.constant.Constants;
import com.chenjx.redwars.domain.User;
import com.chenjx.redwars.result.GlobalErrorInfoEnum;
import com.chenjx.redwars.result.GlobalErrorInfoException;
import com.chenjx.redwars.result.ResultBody;
import com.chenjx.redwars.result.UserErrorInfoEnum;
import com.chenjx.redwars.service.UserService;
import com.chenjx.redwars.utils.MD5;
import com.chenjx.redwars.utils.RedisOperationUtils;
import com.chenjx.redwars.utils.UUIDUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * create by chenjx 2018/11/26
 */

@Api(value = "UserRestController", tags = {"用户接口"})
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    public UserService userService;

    @Autowired
    public RedisOperationUtils redisOperationUtils;

    /**
     * 登入接口
     * @param user
     * @return
     * @throws GlobalErrorInfoException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBody register(@RequestBody User user) throws GlobalErrorInfoException {
        if (user==null || StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())) {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        if(userService.findByName(user.getUsername()) != null) {
            throw new GlobalErrorInfoException(UserErrorInfoEnum.USERNAME_REPEAT);
        }
        Boolean save = userService.saveUser(user) > 0;
        return new ResultBody(save);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBody login(@RequestBody User user, HttpServletRequest request) throws GlobalErrorInfoException {
        String username = user.getUsername();
        String password = user.getPassword();
        if(user==null || StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        User userDB = userService.findByName(username);
        if(userDB==null || StringUtils.isEmpty(userDB.getPassword())
                || !MD5.md5(password).equals(userDB.getPassword())) {
            throw new GlobalErrorInfoException(UserErrorInfoEnum.LOGIN_ERROR);
        }
        String uuid = UUIDUtil.getUUID();

        redisOperationUtils.set(Constants.TOKEN + uuid, JSON.toJSONString(userDB), Constants.EXP_TIMES);
        return new ResultBody(uuid);
    }

    @RequestMapping(value = "/getUserByToken",method = RequestMethod.GET)
    public ResultBody getUserByToken(HttpServletRequest request) throws GlobalErrorInfoException {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NO_TOKEN);
        }
        String userRedis = redisOperationUtils.get(Constants.TOKEN + token);
        if(StringUtils.isEmpty(userRedis)) {
            throw new GlobalErrorInfoException(UserErrorInfoEnum.TOKEN_WITHOUT_RELATIVEUSER);
        }
        return new ResultBody(userRedis);
    }

}

