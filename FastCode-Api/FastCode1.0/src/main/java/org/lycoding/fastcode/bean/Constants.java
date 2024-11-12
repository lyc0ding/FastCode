package org.lycoding.fastcode.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.lycoding.fastcode.utils.PropertiesUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;

    static {
        IGNORE_TABLE_PREFIX= Boolean.valueOf(PropertiesUtils.getValue("ignore.table.prefix"));
    }
}
