package tw.eis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

public class GlobalService {
	
	public static byte[] processImgData(MultipartFile mFile) {
		String fileName = null;
		byte[] b=null;
		try {
			fileName = mFile.getOriginalFilename();
			String savePath = "C:/temp/files/" + fileName;
			File saveFile = new File(savePath);
			mFile.transferTo(saveFile);
			InputStream is1 = new FileInputStream(savePath);
			b=new byte[is1.available()];
			is1.read(b);
			is1.close();
			saveFile.delete();
			return b;
		} catch (Exception e) {
			return b;
		}
	}
	
	public static Date dateOfToday() { //yyyy-MM-dd
		Timestamp tsmp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Date.valueOf(sdf.format(tsmp));
		return today;
	}
	
	public static String formatToyyyyMMddHHmmss(Timestamp datetime) { //yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(datetime);
	}
	
	public static String formatToyyyyMMdd(java.util.Date datetime) { //yyyy-MM-dd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(datetime);
	}
	
	public static String formatToyyyyMMdd(String datetime) { //yyyy-MM-dd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(datetime);
	}
	
}
