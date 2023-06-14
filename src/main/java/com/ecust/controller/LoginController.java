package com.ecust.controller;

import com.ecust.pojo.Employee;
import com.ecust.pojo.R;
import com.ecust.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author solang
 * @date 2023-06-02 16:48
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 登陆操作
     *
     * @param employee
     */
    @PostMapping("/login")
    public R login(HttpServletRequest request,@RequestBody Employee employee) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Employee emp = employeeService.login(employee.getUsername());
        log.info("密码是:{}",emp.getPassword());
        log.info("输入的密码是:{}",employee.getPassword());
        log.info("解析输入的密码是:{}",DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        if (checkpassword(employee.getPassword(), emp.getPassword())) {

            request.getSession().setAttribute("employee",emp.getId());
            log.info("存入的seesion是：{}",request.getSession().getAttribute("employee"));
            return R.success(emp);
        }else{
            return R.error("登陆失败");
        }

    }

    /**
     * 利用MD5进行加密 　　* @param str 待加密的字符串 　　* @return 加密后的字符串 　　* @throws
     * NoSuchAlgorithmException 没有这种产生消息摘要的算法 　　 * @throws
     * UnsupportedEncodingException
     */


    /**
     * 判断用户密码是否正确
     * * @param newpasswd 用户输入的密码
     * * @param oldpasswd
     * 数据库中存储的密码－－用户密码的摘要
     * * @return 　　　　* @throws NoSuchAlgorithmException
     * 　* @throws UnsupportedEncodingException
     */
    public static boolean checkpassword(String newpasswd, String oldpasswd)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (DigestUtils.md5DigestAsHex(newpasswd.getBytes()).equals(oldpasswd))
            return true;
        else
            return false;
    }
    @PostMapping("/logout")
    public R logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
