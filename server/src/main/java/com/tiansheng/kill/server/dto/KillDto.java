package com.tiansheng.kill.server.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @作者 itsheng
 * @微信号 yan-mei-sheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间 2020/3/2  10:25
 */
@Data
@ToString
public class KillDto {
        @NotNull
        private Integer killId;
        private Integer userId;
}
