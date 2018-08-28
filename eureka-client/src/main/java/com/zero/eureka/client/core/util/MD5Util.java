package com.zero.eureka.client.core.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类
 * @Author:xuyp
 * @Date:2018/7/26 22:24
 */
public class MD5Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);
    private static final int HEX_ZERO = 0xf;
    private static final char[] HEXGITIS = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F'};
    public static final String MD_5_NAME = "MD5";

    /**
     * 通过原始密码和盐获得md5加密字符串
     * @param original 原始密码
     * @param salt 盐
     */
    @Deprecated
    public static String encrypt(final String origin, final String salt) {
        final byte[] origins = (origin + salt).getBytes();
        return md5(origins);
    }

    /**
     * 直接对字符串进行加密
     * @param origin 源字符串
     * @return
     */
    @Deprecated
    public static String encrypt(final String origin) {
        final byte[] origins = (origin).getBytes();
        return md5(origins);
    }

    /**
     * 通过Shiro的加密工具类进行md5加密
     * @param origin
     * @param salt
     * @return
     */
    public static String shiroEncrypt(final String origin, final String salt) {
        final ByteSource bytes = ByteSource.Util.bytes(salt);
        final SimpleHash simpleHash = new SimpleHash(MD5Util.MD_5_NAME, origin, bytes, 1);
        return simpleHash.toHex();
    }

    /**
     * 使用shiro加密进行md5加密，不加盐
     * @param origin
     * @return
     */
    public static String shiroEncrypt(final String origin) {
        final ByteSource bytes = ByteSource.Util.bytes(origin);
        final SimpleHash simpleHash = new SimpleHash(MD5Util.MD_5_NAME, origin, bytes, 1);
        return simpleHash.toHex();
    }


    /**
     * 将字节数组转成md5字符串
     * @param plaintext
     * @return
     */
    private static String md5(byte[] plaintext) {
        try {
            // 获得MD5摘要算法的MessageDigest对象
            final MessageDigest messageDigest = MessageDigest.getInstance(MD5Util.MD_5_NAME);
            // 使用指定的字符串更新摘要
            messageDigest.update(plaintext);
            // 获得密文
            final byte[] md5Bytes = messageDigest.digest();
            // 转成16进制字符的字符串形式
            final int j = md5Bytes.length;
            final char[] chars = new char[(j << 1)];
            int k = 0;
            for (final byte byte1 : md5Bytes) {
                chars[k] = MD5Util.HEXGITIS[((int) byte1 >>> 4) & MD5Util.HEX_ZERO];
                k++;
                chars[k] = MD5Util.HEXGITIS[(int) byte1 & MD5Util.HEX_ZERO];
                k++;
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            MD5Util.LOGGER.error(MD5Util.MD_5_NAME, e);
            return null;
        }
    }

    public static void main(String[] args) {
//        System.out.println(encrypt("admin", "123"));
//        System.out.println(encrypt("admin"));
        System.out.println(shiroEncrypt("admin"));
        System.out.println(shiroEncrypt("admin", "123"));
        Md5Hash md5Hash = new Md5Hash("admin","123");
        System.out.println(md5Hash.toString());
        Md5Hash md5Hash1 = new Md5Hash("admin");
        System.out.println(md5Hash1.toString());
    }
}
