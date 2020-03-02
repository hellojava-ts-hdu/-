package com.tiansheng.kill.server.service;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/2  9:47
 */
public interface IKillService {

    Boolean killItem(Integer killId,Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

    Boolean killItemV5(Integer killId, Integer userId) throws Exception;
}
