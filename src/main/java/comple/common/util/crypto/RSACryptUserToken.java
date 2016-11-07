package comple.common.util.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

@Component
public class RSACryptUserToken extends RSACrypt{

	public RSACryptUserToken() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchPaddingException {
//		private : MIHCAgEAMA0GCSqGSIb3DQEBAQUABIGtMIGqAgEAAiEAjg5uYHyebY5Cfjx/LMbJLV/A6neeH1HiSi6oF6lvsvcCAwEAAQIgOwQ90PIU85jlgQJrJ1QSoZs90R26yeXBnpWEgbF168kCEQDNpbWpvcD/bHVezObkHZ+TAhEAsNa+ab3PAU/7ujExTDFVjQIQdL6vK8aqixayZvgh17ytewIQEijbQOyW92GJTiODFnnyeQIRAJEvJa3vJyusgilLP6gubxM=
//		public : MDwwDQYJKoZIhvcNAQEBBQADKwAwKAIhAI4ObmB8nm2OQn48fyzGyS1fwOp3nh9R4kouqBepb7L3AgMBAAE=

		super("MIHCAgEAMA0GCSqGSIb3DQEBAQUABIGtMIGqAgEAAiEAjg5uYHyebY5Cfjx/LMbJLV/A6neeH1HiSi6oF6lvsvcCAwEAAQIgOwQ90PIU85jlgQJrJ1QSoZs90R26yeXBnpWEgbF168kCEQDNpbWpvcD/bHVezObkHZ+TAhEAsNa+ab3PAU/7ujExTDFVjQIQdL6vK8aqixayZvgh17ytewIQEijbQOyW92GJTiODFnnyeQIRAJEvJa3vJyusgilLP6gubxM=","MDwwDQYJKoZIhvcNAQEBBQADKwAwKAIhAI4ObmB8nm2OQn48fyzGyS1fwOp3nh9R4kouqBepb7L3AgMBAAE=");
	}
	
	public String genAccessToken(String cellphone) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		// cellphone, time
		long time = System.currentTimeMillis();
		
		String token = cellphone+"_"+time;
		
		return Base64ForUrl.encode(encryptByPublic(token.getBytes()));
	}
}
