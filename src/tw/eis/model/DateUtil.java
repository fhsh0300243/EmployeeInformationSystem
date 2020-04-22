package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

		public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000");
		return sdf.format(new Date());
		}

		public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.toString();
		}
		}

