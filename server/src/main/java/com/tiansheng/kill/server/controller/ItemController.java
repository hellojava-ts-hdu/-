package com.tiansheng.kill.server.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tiansheng.kill.model.entity.ItemKill;
import com.tiansheng.kill.server.service.IItemService;
import com.tiansheng.kill.server.service.impl.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/1  23:31
 * 待秒杀商品列表
 */
@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix="item";
    @Autowired
    private IItemService itemService;

    @RequestMapping(value = {"/","/index",prefix+"/list",prefix+"/index.html"},method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        try{
            List<ItemKill> list = itemService.getKillItems();
            modelMap.put("list",list);
            logger.info("获取待秒杀商品:{}",list);
        }catch (Exception e){
            logger.error("获取待秒杀商品-发生异常",e.fillInStackTrace());
            return "redirect:/base/error";
        }
       return "list";
    }

    /**
     * 获取待秒杀商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = prefix+"/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable Integer id,ModelMap modelMap){
        if(id == null || id <= 0){
            return "redirect:/base/error";
        }
        try {
            ItemKill detail = itemService.getKilldetail(id);
            modelMap.put("detail",detail);
            logger.info("获取待秒杀商品详情id：{}",id);
        }catch (Exception e){
            logger.error("获取待秒杀商品详情-id:{}",id,e.fillInStackTrace());
            return "redirect:/base/error";
        }

        return "info";
    }
}
