package comple.common.util.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

@Component
public class RSACryptForPassword extends RSACrypt{

	public RSACryptForPassword() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchPaddingException {
//		String privatek = "MIHCAgEAMA0GCSqGSIb3DQEBAQUABIGtMIGqAgEAAiEAjrig1MnCIz/nIMYfTJtIsJH2Vf/pfV66k98wvXZoqCcCAwEAAQIgXY4JX3MiH5k0GPfGbKEd9CXWVULxiOLqKbMI5q3QoYECEQDIlk7Wur9Tmf5luJkq9FIdAhEAtiX+DECxJTvG+ZjJfJvQEwIQK9NjcFknGm4H/lOh65esJQIQAPvfjW1kB5F1F7ANlpC45QIRAJ+aYE7dN157PtJ3bR8PlTg="; 
//		String publick = "MDwwDQYJKoZIhvcNAQEBBQADKwAwKAIhAI64oNTJwiM/5yDGH0ybSLCR9lX/6X1eupPfML12aKgnAgMBAAE=";
		super(null, "MDwwDQYJKoZIhvcNAQEBBQADKwAwKAIhAI64oNTJwiM/5yDGH0ybSLCR9lX/6X1eupPfML12aKgnAgMBAAE=");
	}
}
