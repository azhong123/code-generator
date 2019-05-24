package com.code.generator.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 唯一标识工具
 *
 * @author azhong
 */
public class UniqueUtil {

    /**
     * 根据时间生产唯一标识
     *
     * @return
     */
    public static String byNewTime() {

        // 随机数
        int randomNum = (int) (Math.random() * 100);
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss" , Locale.UK);
        return time.format(new Date()) + randomNum;
    }

}
