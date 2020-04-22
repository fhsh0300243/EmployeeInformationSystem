package tw.eis.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AEStest {
	
	public static final String KEY = "EEIT112Group6EIS20200508";
	
    public static byte[] encrypt(String content, String password) {
        try {           
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(128, new SecureRandom(password.getBytes()));
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                byte[] byteContent = content.getBytes("utf-8");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] result = cipher.doFinal(byteContent);
                return result; 
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        } catch (NoSuchPaddingException e) {
                e.printStackTrace();
        } catch (InvalidKeyException e) {
                e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
        } catch (BadPaddingException e) {
                e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] decrypt(byte[] content, String password) {
        try {
                 KeyGenerator kgen = KeyGenerator.getInstance("AES");
                 kgen.init(128, new SecureRandom(password.getBytes()));
                 SecretKey secretKey = kgen.generateKey();
                 byte[] enCodeFormat = secretKey.getEncoded();
                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
                 Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] result = cipher.doFinal(content);
                return result; 
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        } catch (NoSuchPaddingException e) {
                e.printStackTrace();
        } catch (InvalidKeyException e) {
                e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
        } catch (BadPaddingException e) {
                e.printStackTrace();
        }
        return null;
    }
    
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
                String hex = Integer.toHexString(buf[i] & 0xFF);
                if (hex.length() == 1) {
                        hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
                return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input password: ");
		String pwd=scanner.next();
		String encryptPwd=parseByte2HexStr(encrypt(pwd, KEY));
		//parseHexStr2Byte(encryptPwd);
		
		byte[] DencryptPwd=decrypt(parseHexStr2Byte(encryptPwd), KEY);
		
		System.out.println("pwd: "+pwd);
		System.out.println("encryptPwd: "+encryptPwd);
		System.out.println("DencryptPwd: "+new String(DencryptPwd));
		
		
		scanner.close();
		
		
//		String content = "test";
//        String password = "12345678";
//        //加密
//        System.out.println("加密前：" + content);
//        byte[] encryptResult = encrypt(content, password);
//        String encryptResultStr = parseByte2HexStr(encryptResult);
//        System.out.println("加密后：" + encryptResultStr);
//        //解密
//        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
//        byte[] decryptResult = decrypt(decryptFrom,password);
//        System.out.println("解密后：" + new String(decryptResult));

	}

}