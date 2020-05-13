package com.sunday.wkday.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunyangming
 * @date 2020/5/2
 */
public class DataUtil {

    public static boolean checkAdmin(String userId, String admin, String subAdmin) {
        if (StringUtils.isBlank(userId)) {
            return false;
        }
        if (userId.equals(admin)) {
            return true;
        }
        if (StringUtils.isNoneBlank(subAdmin) && Arrays.asList(subAdmin.split(",")).contains(userId)) {
            return true;
        }
        return false;
    }

    public static List<String> getAdminList(String admin, String subAdmin) {
        List<String> adminList = new ArrayList<>();
        adminList.add(admin);
        if (StringUtils.isNoneBlank(subAdmin)) {
            adminList.addAll(Arrays.asList(subAdmin.split(",")));
        }
        return adminList;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList("".split(",")));
        System.out.println(checkAdmin("123", "", "123,77123"));
    }
}
