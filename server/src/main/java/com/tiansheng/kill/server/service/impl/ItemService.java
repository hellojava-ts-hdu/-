package com.tiansheng.kill.server.service.impl;

import com.tiansheng.kill.model.entity.ItemKill;
import com.tiansheng.kill.model.mapper.ItemKillMapper;
import com.tiansheng.kill.model.mapper.ItemKillSuccessMapper;
import com.tiansheng.kill.server.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/1  23:40
 */
@Service
public class ItemService implements IItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemKillMapper itemKillMapper;

    /**
     * 获取待秒杀商品详情
     * @param id
     * @return
     * @throws Exception
     */
    public ItemKill getKilldetail(Integer id) throws Exception {
        ItemKill entity = itemKillMapper.selectById(id);
        if(entity == null){
            throw new Exception("获取待秒杀商品详情-待秒杀商品记录不存在");
        }
        return entity;
    }

    /**
     * 获取待秒杀商品列表
     * @return
     * @throws Exception
     */
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAll();
    }


}
