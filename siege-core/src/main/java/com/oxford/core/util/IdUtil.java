package com.oxford.core.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * ID工具类
 *
 * @author Chova
 * @date 2020/07/19
 */
public class IdUtil {

    /**
     * 使用基于时间的生成器生成ID
     *
     * @return String类型的UUID
     */
    public static String generateUuid() {
        TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        return timeBasedGenerator.generate().toString();
    }
}
