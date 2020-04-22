package tw.eis.controller;

import tw.eis.util.SecurityService;

public class Test {

	public static void main(String[] args) {
		//String decryptedString = SecurityService.decryptString();
		//String encryptedString = SecurityService.encryptString("aa12345");
		//System.out.println(decryptedString);
		//System.out.println(encryptedString);
		String encryptedString = SecurityService.parseByte2HexStr(SecurityService.encrypt("aa12345"));
		System.out.println(encryptedString);
		byte[] result = SecurityService.decrypt(SecurityService.parseHexStr2Byte("D783BFB71A21F6B6706D25ACE3176C4B"));
		System.out.println(new String(result));
	}

}
