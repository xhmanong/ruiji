package com.ecust.controller;

import com.ecust.pojo.R;
import com.ecust.pojo.User;
import com.ecust.service.UserService;
import com.ecust.utils.IdGenerator16Bit;
import com.ecust.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @author solang
 * @date 2023-06-08 16:39
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R sendMsg(@RequestBody User user, HttpServletRequest request){
        if(user.getPhone()!=null){
            String validateCode= ValidateCodeUtils.generateValidateCode4String(6);
            request.getSession().setAttribute("phone",validateCode);
            log.info("验证码是:{}",validateCode);
            return R.success(validateCode);
        }
        return R.error("请重新获取验证码");
    }





    @PostMapping("/login")
    public R login(@RequestBody Map map, HttpServletRequest request){
        String phone=map.get("phone").toString();
        String code=map.get("code").toString();
        log.info("登陆的手机号为：{}，验证码为：{}","",code);
        if(request.getSession().getAttribute("phone").equals(code)){
            request.getSession().removeAttribute("phone");

            User user=userService.find(phone);
            log.info("查询出来的user的数据是:{}",user);

            if(user==null){
                user=new User();
                user.setId(IdGenerator16Bit.generateId());
                user.setName(phone);
                user.setPhone(phone);
                user.setStatus(1);
                userService.insert(user);
            }
            request.getSession().setAttribute("user",user.getId());
           return R.success(user);
        }
        return R.error("登陆失败");
    }
}
