package comple.common.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comple.common.dao.UserDAO;
import comple.common.util.crypto.Base64Coder;
import comple.common.vo.User;
@Service
public class UserService {
    
    @Autowired 
    private UserDAO userDAO;
    
	private Logger log = Logger.getLogger("service");
	private Logger error2 = Logger.getLogger("error2");
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * password encoding
	 * @param pw
	 * @return encoded password
	 * @throws NoSuchAlgorithmException
	 */
	private String passwdDigest( String pw ) throws NoSuchAlgorithmException{
		String input;
		if(pw.length()>4){
			input = "lt"+pw.substring(0, 4)+"so"+pw.substring(4);
		} else {
			input = "lt"+pw+"so";
		}
		
		byte[] bNoti = input.getBytes();
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] digest = md.digest(bNoti);
	    String pass = Base64Coder.encodeLines(digest);
	    return pass;
	}
    

	public User selectUserLogin ( String loginid, String passwd) throws NoSuchAlgorithmException {
		log.info("Login access try with id :" + loginid + " password : " + passwdDigest(passwd));
		return userDAO.selectUserLogin(loginid, passwdDigest(passwd));
	}


	public int insertAccount(String email, String password,
			String type, String user) throws NoSuchAlgorithmException {
		return userDAO.insertAccount(email,passwdDigest(password),type);
	}
    

}