package com.mingguo.wu.infra.web.util;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String相关工具类
 * Created by mingguo.wu on 2015/9/15.
 */
public class StringUtil extends StringUtils{
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static final int MAX_CHAR_CODE = 255;

    private static final Pattern MOBILE_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9])|(14[5,7]))\\d{8}$");

    /**
     * 统计字符串中的字符数（中文算两个字符）
     * @param s 字符串
     * @return 字符数
     */
    public static int charCount(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i) > MAX_CHAR_CODE) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 对字符串进行md5加密
     * @param s 字符串
     * @return 加密后的结果
     */
    public static String md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            md.update(s.getBytes());
            byte[] encodedStr = md.digest();
            StringBuilder buf = new StringBuilder();

            for (byte anEncodedStr : encodedStr) {
                int n = ((int) anEncodedStr & 0xff);
                int d1 = n / 16;
                int d2 = n % 16;
                buf.append(HEX_DIGITS[d1]).append(HEX_DIGITS[d2]);
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断一个字符串是否可以转化为整数
     * @param s 字符串
     * @return 判断的结果
     */
    public static boolean isInteger(String s) {
        boolean isInteger = true;
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            isInteger = false;
        }
        return isInteger;
    }

    /**
     * 判断一个字符串是否可以转化长整型
     * @param s 字符串
     * @return 判断的结果
     */
    public static boolean isLong(String s) {
        boolean isLong = true;
        try {
            Long.parseLong(s);
        } catch (Exception e) {
            isLong = false;
        }
        return isLong;
    }

    /**
     * 空格过滤，包含全角空格和半角空格
     * @param s 字符串
     * @return 过滤空格（包含全角空格）后的字符串
     */
    public static String trim(String s) {
        if (s == null) {
            return s;
        }
        return s.replaceAll("[ 　]+", "");
    }

    /**
     * 判断字符串是否为空
     * @param s 字符串
     * @return true：null，""， 只包含空格（包括全角空格）
     */
    public static boolean isEmpty(String s) {
        return s == null || trim(s).length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param s 字符串
     * @return false:null, "", 字包含空格（包括全角空格）
     * @see com.mingguo.wu.infra.web.util.StringUtil#isEmpty(String)
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * 将一个集合列表拼接成字符串
     * @param list 所要拼接的字符串
     * @param delimiter 两个元素自建的分隔符默认是" "（半角空字符串）
     * @return 拼接后的结果
     */
    public static String join(List<?> list, String delimiter) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        if (delimiter == null || delimiter.isEmpty()) {
            delimiter = " ";
        }
        StringBuilder buffer = new StringBuilder();
        int count = 1;
        for (Object aList : list) {
            if (aList == null) {
                continue;
            }
            if (count != 1) {
                buffer.append(delimiter);
            }
            buffer.append(aList.toString());
            count++;
        }
        return buffer.toString();
    }

    /**
     * 将一个对象结合拼接成字符串
     * @param array 所要拼接的字符串
     * @param delimiter 两个元素自建的分隔符默认是" "（半角空字符串）
     * @return 拼接后的结果
     * @see com.mingguo.wu.infra.web.util.StringUtil#join(List, String)
     */
    public static String join(Object[] array, String delimiter) {
        List<Object> list;
        if (array == null || array.length == 0) {
            list = Collections.emptyList();
        } else {
            list = Arrays.asList(array);
        }
        return join(list, delimiter);
    }

    /**
     * 将字符串分割成List集合列表
     * @param toBeSplit 需要分割的字符串
     * @param delimiter 字符串的分隔符，首先将字符串根据分隔符分割
     * @param matchRegex 正则表达式，满足正则表达式的字符串填充到列表
     * @return 分割正则匹配的集合列表
     */
    public static List<String> splitStringByDelimiter(String toBeSplit, String delimiter, String matchRegex) {
        List<String> resultList = new ArrayList<String>();
        if (toBeSplit != null && !toBeSplit.isEmpty()) {
            if (delimiter == null) {
                delimiter = "";
            }
            String[] items = toBeSplit.split(delimiter);
            if (matchRegex == null) {
                Collections.addAll(resultList, items);
            } else {
                for (String item : items) {
                    if (item.matches(matchRegex)) {
                        resultList.add(item);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 判断字符串是否是邮箱地址
     * @param email 邮箱地址字符串
     * @return 判断结果
     */
    public static boolean isEmail(String email) {
        if (StringUtil.isEmpty(email)) {
            return false;
        }
        Pattern p = Pattern.compile("^[_a-z0-9\\.\\-]+@([\\._a-z0-9\\-]+\\.)+[a-z0-9]{2,4}$");
        Matcher m = p.matcher(email.toLowerCase());
        return m.matches();
    }

    /**
     * 判断是否符合手机号的条件
     * @param phone 电话号码字符串
     * @return 判断结果
     */
    public static boolean isPhoneNumber(String phone) {
        return isMobileNo(phone);
    }

    /**
     * 16进制字符串转化成字节数组
     * @param src 16进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String src) {
        int length = src.length() / 2;
        byte[] result = new byte[length];
        char[] array = src.toCharArray();
        for (int i = 0; i < length; i++) {
            result[i] = uniteBytes(array[2 * i], array[2 * i + 1]);
        }
        return result;
    }

    /**
     * 字节数组转化为16进制字符串
     * @param bytes 需要转化的字符串
     * @return 转化后的16进制字符串
     * @see com.mingguo.wu.infra.web.util.StringUtil#hexString2Bytes(String)
     */
    public static String byte2HexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 内部使用类，字符数组转化为16进制字符串使用方法
     * @see #hexString2Bytes(String)
     */
    private static byte uniteBytes(char mostChar, char secondChar) {
        byte b0 = Byte.decode("0x" + String.valueOf(mostChar));
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + String.valueOf(secondChar));
        return (byte) (b0 | b1);
    }

    /**
     * 判断字符串是否是数字，只是判断字符串只包含数字
     * @param s 需要判断的字符串
     * @return 判断结果
     */
    public static boolean isNumeric(String s) {
        if (s == null || s.equals("")) {
            return false;
        }
        for (int i = s.length(); --i >= 0; ) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串首字母大写，其余部分不变
     * @param srcString 字符串
     * @return 转化后字符串
     */
    public static String capitalize(String srcString) {
        if (srcString == null || srcString.isEmpty()) {
            return srcString;
        }
        String firstChar = srcString.substring(0, 1);
        return firstChar.toUpperCase() + (srcString.length() > 1 ? srcString.substring(1) : "");
    }


    /**
     * js特殊字符转义
     * ' ===> \'
     * " ===> \"
     * & ===> \&
     * \ ===> \\
     * \n ===> \\n
     * \r ===> \\r
     * \t ===> \\t
     * \b ===> \\b
     * \f ===> \\f
     * @param str 需要转化的字符串
     * @return string
     */
    public static String jsEscape(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer(str.length() + (str.length() / 2));
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (chr == '\'') {
                sb.append("\\'");
            } else if (chr == '"') {
                sb.append("\\\"");
            } else if (chr == '&') {
                sb.append("\\&");
            } else if (chr == '\\') {
                sb.append("\\\\");
            } else if (chr == '\n') {
                sb.append("\\n");
            } else if (chr == '\r') {
                sb.append("\\r");
            } else if (chr == '\t') {
                sb.append("\\t");
            } else if (chr == '\b') {
                sb.append("\\b");
            } else if (chr == '\f') {
                sb.append("\\f");
            } else {
                sb.append(chr);
            }
        }
        return sb.toString();
    }

    /**
     * 判断是否是国内手机号 ,不能做强制校验 ，不适合所有场景
     * @param mobile 手机号码
     * @return 判断结果
     */
    public static boolean isMobileNo(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(mobile);
        return m.matches();
    }

    /**
     * 判断是否是电话号码
     * @param phone 电话号码
     * @return 判断结果
     */
    public static boolean isPhoneNo(String phone) {
        if(isEmpty(phone)) {
            return false;
        }
        Matcher m = PHONE_NUMBER_PATTERN.matcher(phone);
        return m.matches();
    }

    /**
     * 判断字符串中是否包含中文字符
     * @param str 需要判断的字符串
     * @return 判断结果
     */
    public static boolean isChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\u3400-\\u4DB5\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
        return p.matcher(str).matches();
    }
}
