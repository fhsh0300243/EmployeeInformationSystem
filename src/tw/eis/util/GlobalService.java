package tw.eis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
	

}
