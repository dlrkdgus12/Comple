package comple.common.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/*
 * ?•„?š” ?¼ë¦¬ë¸Œ?Ÿ¬ë¦?
 <dependency>
 <groupId>javax.mail</groupId>
 <artifactId>javax.mail-api</artifactId>
 <version>1.4.5</version>
 <scope>compile</scope>
 </dependency>
 */
public class EmailSender {
	
//	private String to = "";
	
	private String charset = "UTF-8";
	private String host = "smtp.gmail.com";
	private String from = "support@vlocally.com";
	private String snedername = "Vlocally Support";
	private String id= "support@vlocally.com";
	private String password = "tnawldnjs";
	
	public EmailSender(){}

	public void sendEmail(String toemail, String toname, String title, String context) throws EmailException {
		SimpleEmail envelop = new SimpleEmail();
		envelop.setCharset(charset);
		envelop.setHostName(host);
		envelop.setSSL(true);
			envelop.setFrom(from, snedername);
		envelop.setAuthentication(id, password); //SMTP ?¸ì¦ì´ ?•„?š”?•  ê²½ìš°
		
		envelop.addTo(toemail, toname); //ë°›ëŠ”?‚¬?Œ
		envelop.setSubject(title); //ë©”ì¼ ? œëª?
		envelop.setMsg(context); //ë©”ì¼ ?‚´?š©
		envelop.send(); //ë©”ì¼ ë°œì†¡
    }
}
