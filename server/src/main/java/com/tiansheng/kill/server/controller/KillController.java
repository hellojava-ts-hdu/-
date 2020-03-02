package com.tiansheng.kill.server.controller;

import com.tiansheng.kill.api.enums.StatusCode;
import com.tiansheng.kill.api.response.BaseResponse;
import com.tiansheng.kill.model.dto.KillSuccessUserInfo;
import com.tiansheng.kill.model.mapper.ItemKillSuccessMapper;
import com.tiansheng.kill.server.dto.KillDto;
import com.tiansheng.kill.server.service.IKillService;
import com.tiansheng.kill.server.service.impl.killService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/2  10:27
 * 待秒杀商品
 */
@Controller
public class KillController {

    private static final Logger logger = LoggerFactory.getLogger(KillController.class);

    private static final String prefix="kill";
    @Autowired
    private IKillService iKillService;
    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;


    @RequestMapping(value = prefix+"/execute",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto killDto, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors() || killDto.getKillId() <= 0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        Object uId=session.getAttribute("uid");
        if (uId==null){
            return new BaseResponse(StatusCode.UserNotLogin);
        }
        //Integer userId=dto.getUserId();
        Integer userId= (Integer)uId ;
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //Boolean res=killService.killItem(dto.getKillId(),userId);
            Boolean res= iKillService.killItem(killDto.getKillId(),userId);
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }





    /***
     * 商品秒杀核心业务逻辑-用于压力测试
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping(value = prefix+"/execute/lock",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse executeLock(@RequestBody @Validated KillDto dto, BindingResult result){
        if (result.hasErrors() || dto.getKillId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //不加分布式锁的前提
            /*Boolean res=killService.killItemV2(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"不加分布式锁-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于Redis的分布式锁进行控制
            /*Boolean res=killService.killItemV3(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redis的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于Redisson的分布式锁进行控制
            /*Boolean res=killService.killItemV4(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redisson的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于ZooKeeper的分布式锁进行控制
            Boolean res=iKillService.killItemV5(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于ZooKeeper的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }









    //http://localhost:8080/kill/kill/record/detail/343147116421722112

    /**
     * 查看订单详情
     * @return
     */
    @RequestMapping(value = prefix+"/record/detail/{orderNo}",method = RequestMethod.GET)
    public String killRecordDetail(@PathVariable String orderNo, ModelMap modelMap){
        if (StringUtils.isBlank(orderNo)){
            return "error";
        }
        KillSuccessUserInfo info=itemKillSuccessMapper.selectByCode(orderNo);
        if (info==null){
            return "error";
        }
        modelMap.put("info",info);
        return "killRecord";
    }


    //抢购成功跳转页面
    @RequestMapping(value = prefix+"/execute/success",method = RequestMethod.GET)
    public String executeSuccess(){
        return "executeSuccess";
    }

    //抢购失败跳转页面
    @RequestMapping(value = prefix+"/execute/fail",method = RequestMethod.GET)
    public String executeFail(){
        return "executeFail";
    }

}
