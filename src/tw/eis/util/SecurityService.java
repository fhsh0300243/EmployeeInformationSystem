package tw.eis.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecurityService {

	private static final String Key="EEIT112Group6EIS20200508";
	//private static final String Key="KittySnoopyGarfieldMicky";
	
	public static String encryptString(String Plaintext) {
		String encryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,genekey());
			byte[] result = cipher.doFinal(Plaintext.getBytes());
			encryptedString=parseByte2HexStr(result);
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedString;
	}

	public static String decryptString() {
		String decryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, genekey());
			byte[] encodedBytes = parseHexStr2Byte("4CC1B2F1F78654A231222C046A5A20BC");
			byte[] result = cipher.doFinal(encodedBytes);
			decryptedString=result.toString();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	
	private static SecretKey genekey() throws Exception {
		KeyGenerator keygenerator =KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom(); 
		random.setSeed("EEIT112Group6EIS20200508".getBytes());
		keygenerator.init(128);
		SecretKey secretkey = keygenerator.generateKey();
		return secretkey;
	}
	
	   public static byte[] encrypt(String Plaintext) {
	        try {           
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");
	                kgen.init(128, new SecureRandom(Key.getBytes()));
	                SecretKey secretKey = kgen.generateKey();
	                byte[] enCodeFormat = secretKey.getEncoded();
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	                Cipher cipher = Cipher.getInstance("AES");
	                byte[] byteContent = Plaintext.getBytes("utf-8");
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
	   
	    public static byte[] decrypt(byte[] content) {
	        try {
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");
	                 kgen.init(128, new SecureRandom(Key.getBytes()));
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
	    
	    
	
}
