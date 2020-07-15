package com.coco.Dao;

import com.coco.Entities.UserAgentTypeEnum;

import java.util.regex.Pattern;

/**
 * User-Agent 工具
 */
public class UserAgentTools {

    /**
     * 识别设备类型
     * @param userAgent 设备标识
     * @return 设备类型
     */
    public static Integer recognize(String userAgent){
        if(Pattern.compile("(Windows Phone|Android|iPhone|iPod)").matcher(userAgent).find()){
            return UserAgentTypeEnum.PHONE.getCode();
        }
        if(Pattern.compile("(iPad)").matcher(userAgent).find()){
            return UserAgentTypeEnum.TABLET.getCode();
        }
        return UserAgentTypeEnum.PC.getCode();
    }

}