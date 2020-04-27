package tw.eis.util;

//  AES加解密 (以8的位元倍數為基礎,ex:8、16、24、32...)
//  可參考tw.eis.util裡面  password.java這支程式
// "1234567890123456"-->代表至少16位元,試過8位元會報錯
//  本程式會印出加密後的明碼,只需要把自己的密碼替換掉我的"aa12345"直接跑,其他不用改


public class DemoAESAction {

	public static void main(String[] args) throws Exception {
		String encodeData = password.encrypt("1234567890123456", "aa12345");
		String decodeData = password.decrypt("1234567890123456", encodeData);
		System.out.println("decodeData:" + decodeData);
		System.out.println("encodeData:" + encodeData);
	}

}
