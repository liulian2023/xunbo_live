package com.zz.live.utils;




import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES对称加密和解密，有偏移量
 * @author tyg
 * @date   2018年6月28日下午12:48:01
 */
@SuppressWarnings("restriction")
public class AESUtil {
    private final static String IV = "f3L#$#g@RzfGdJML"; //你的偏移量
    private  static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private  static final String AES = "AES";//AES 加密
    private  static final String SHA1PRNG="SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法，现已可以不使用
    public static final String password = "Fs!TA@dO7coVf5TR"; //你的密钥

    public static String encrypt(String cleartext) {
        return encrypt(cleartext.getBytes());
    }
    /**
     * 加密
     * @param cleartext 需加密字段
     * @return
     */
    public static String encrypt(byte[] cleartext){
        IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes());
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), AES);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(cleartext);
            return new String(Base64.encode(encryptedData,Base64.DEFAULT));
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (BadPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (InvalidAlgorithmParameterException e) {
        }
        return null;
    }


    /**
     * 解密
     * @param encrypted 密文
     * @return
     */
    public static String decrypt(String encrypted){
       if(StringMyUtil.isEmptyString(encrypted)) {
            return "";
        }
        byte[] byteMi = Base64.decode(encrypted,Base64.DEFAULT);
        IvParameterSpec zeroIv = new IvParameterSpec(IV.getBytes());
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData);
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        } catch (BadPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (InvalidAlgorithmParameterException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "epXtkBEBZfqNQVlpvzXbSYn1Ir8au7bgAVtOIUlBizB7OuDckPFTrzWWW25XL5Ql3sFkoqErideS\\nC7kXkV3YsgLPr4aOPOL+jKlQFQRLkC6TikzQBQQAArGGG68ogKguhfEf1OKzCgCSSChwWjY2E3ko\\nlR+IhnxMkgKl0Pr5BtYqydm4F6HU4+S/JP6+wKW7IlU3aNzXCEsgUopgLj1S/dbcDceI4vh/Kaka\\n9skEcc8JmBcdSVZz8RKtlLDZLMK2G7GasVOxemkGICM3YeJv7w==\\n";

/*        String encrypt = encrypt(s);
        System.out.println("加密后:"+ encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println("解密后:"+decrypt);*/

        String decrypts = decrypt(s);
        System.out.println("解密test:"+decrypts);

    }

}