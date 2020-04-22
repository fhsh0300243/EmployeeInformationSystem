package tw.eis.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class password {

	public static final String bm = "UTF-8";
	public static final String KEY = "EEIT112Group6EIS20200508";

	public static String encrypt(String dataPassword, String cleartext) throws Exception {
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(cleartext.getBytes());
		return new String(parseByte2HexStr(encryptedData));
	}

	public static String decrypt(String dataPassword, String encrypted) throws Exception {
		byte[] byteMi = parseHexStr2Byte(encrypted);
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedData = cipher.doFinal(byteMi);
		return new String(decryptedData);

	}

// 將16進制轉成二進制
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

// 將二進制轉成十六進制
	public static String parseByte2HexStr(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xff);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();

	}

}
