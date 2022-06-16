package net.codeJava.atozService;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.AppExceptionLog;
import net.codeJava.atozRepository.AppExceptionRepository;

@Service
public class AppExceptionLogService {
	
	@Autowired
	private AppExceptionRepository appExceptionRepository;
	public void save(AppExceptionLog appExceptionLog) {
		appExceptionRepository.save(appExceptionLog);
	}
	public void saveLog(String message, String url, String userName) {
		// TODO Auto-generated method stub
		AppExceptionLog obj = new AppExceptionLog();
		try {
			obj.setMessage(new SerialBlob(message.getBytes()));
			obj.setUrl(url);
			obj.setCreateByUser(userName);
			obj.setCreateDate(new Date());
			appExceptionRepository.save(obj);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
