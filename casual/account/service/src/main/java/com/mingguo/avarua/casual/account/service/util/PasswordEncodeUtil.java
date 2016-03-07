package com.mingguo.avarua.casual.account.service.util;

import com.mingguo.avarua.infra.web.util.AesCryptUtil;

/**
 * Created by mingguo.wu on 2015/9/17.
 */
public class PasswordEncodeUtil {

    public static final String PASSWORD_ENCODE_KEY = "7y-KDJnxu@_3fw*!4R~23";
    public static final String AES_ENCODE_PRE = "1";

    public static String encode(String password) {
        if (null == password) {
            return null;
        }
        return AES_ENCODE_PRE + AesCryptUtil.encrypt(password, PASSWORD_ENCODE_KEY);
    }

    public static String decode(String cipher) {
        if (null == cipher) {
            return null;
        }
        if (!cipher.startsWith(AES_ENCODE_PRE)) {
            return null;
        }
        String realCipher = cipher.substring(AES_ENCODE_PRE.length());
        return AesCryptUtil.decrypt(realCipher, PASSWORD_ENCODE_KEY);
    }
}
