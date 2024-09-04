package com.book.util;

import java.util.List;

public class CommonUtil {
    public static Object[][] toArray(List<Object[]> list){
        Object[][] result = new Object[list.size()][];
        for(int i = 0;i < list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public static boolean isNotEmpty(String text){
        if(text == null || text.trim().length() == 0){
            return false;
        }
        return true;
    }
}
