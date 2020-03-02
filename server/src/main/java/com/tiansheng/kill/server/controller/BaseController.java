package com.tiansheng.kill.server.controller;

import com.tiansheng.kill.api.enums.StatusCode;
import com.tiansheng.kill.api.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/1  22:34
 * 基础的controller，用来测试mvc
 */
@Controller
@RequestMapping("base")
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/welcome")
    public String welcome(String name , ModelMap modelMap){
        if(StringUtils.isEmpty(name)){
            name = "您好";
        }
        modelMap.put("name",name);
        return "welcome";
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public String data(String name){
        if(StringUtils.isEmpty(name)){
            name = "hello java!";
        }
        return name;
    }

    @RequestMapping(value = "/reponse",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse reponse(String name){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        if(StringUtils.isEmpty(name)){
            name = "hello java!";
        }
        baseResponse.setData(name);
        return baseResponse;
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }
}
