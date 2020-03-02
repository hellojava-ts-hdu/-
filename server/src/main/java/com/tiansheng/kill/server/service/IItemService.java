package com.tiansheng.kill.server.service;

import com.tiansheng.kill.model.entity.ItemKill;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/1  23:39
 */
public interface IItemService {
   List<ItemKill> getKillItems() throws Exception;

   ItemKill getKilldetail(Integer id) throws Exception;


}
