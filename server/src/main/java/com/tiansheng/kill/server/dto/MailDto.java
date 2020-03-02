package com.tiansheng.kill.server.dto;/**
 * Created by Administrator on 2019/6/22.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @作者 itsheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间  2020/3/2  22:17
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDto implements Serializable{
    //邮件主题
    private String subject;
    //邮件内容
    private String content;
    //接收人
    private String[] tos;
}