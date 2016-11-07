package comple.common.util.crypto;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSACrypt {
	
	private Cipher cipher;
	private KeyPair key;
	
	/**
	 * ?Éù?Ñ±?ûêÎ°? base64 ?óîÏΩîÎî© Ï≤òÎ¶¨?êú Í≥µÍ∞ú?Ç§ ÎπÑÍ≥µÍ∞úÌÇ§ ?ûÖ?†•
	 * @param privateKey
	 * @param publicKey
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 */
	public RSACrypt(String privateKey, String publicKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchPaddingException{
		this(stringToKeyPair(privateKey, publicKey));
	}
	
	public RSACrypt(PrivateKey privateKey, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException{
		this(new KeyPair(publicKey, privateKey));
	}
	
	public RSACrypt(KeyPair kp) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException{
		if(Security.getProvider("BC")==null){
			Security.addProvider(new BouncyCastleProvider());
		}
		key = kp;
		
		cipher = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
	}
	
	/*
	 * ===================================================
	 * ?ïî?ò∏?ôî, Î≥µÌò∏?ôî
	 * ===================================================
	 */
	
	public byte[] encryptByPrivate(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
	    cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
	    byte[] cipherText = cipher.doFinal(input);//cipher.update(input);
	    return cipherText;
	}
	
	public byte[] decryptByPublic(byte[] cipherText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
	    cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
	    byte[] plainText = cipher.doFinal(cipherText);//cipher.update(cipherText);
	    
	    return plainText;
	}
	
	public byte[] encryptByPublic(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	    cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
	    byte[] cipherText = cipher.doFinal(input);
	    return cipherText;
	}
	
	public byte[] decryptByPrivate(byte[] cipherText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
	    cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
	    byte[] plainText = cipher.doFinal(cipherText);
	    return plainText;
	}
	
	/*
	 * =============================================================
	 * RSA ?Ç§?†ú?ñ¥ Î©îÏÑú?ìú?ì§
	 * =============================================================
	 */
	
	/**
	 * ?ÉàÎ°úÏö¥ ÎπÑÎ?Ïπ? ?Ç§ ?Éù?Ñ± 
	 * @param length
	 * 		128, 256, 512, 1024, 2048 ?ã®?úÑÎ°? ?ûÖ?†•?ï†Í≤?
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static KeyPair genKeyPair(int length) throws NoSuchAlgorithmException, NoSuchProviderException{
		if(Security.getProvider("BC")==null){
			Security.addProvider(new BouncyCastleProvider());
		}
		
		SecureRandom random = new SecureRandom();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA","BC");
        kpg.initialize(length, random);
        KeyPair kp = kpg.genKeyPair();
        
        return kp;
	}
	
	/**
	 * ÎπÑÎ??Ç§Î•? base64Î°? ?óîÏΩîÎî©?ïò?ó¨ Ï∂úÎ†•
	 * @param key
	 * @return
	 */
	public static String privateKeyToBase64(PrivateKey key){
		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(key.getEncoded());
		return new String(Base64Coder.encode(privateSpec.getEncoded()));
	}
	
	/**
	 * Í≥µÍ∞ú?Ç§Î•? base 64Î°? ?óîÏΩîÎî©?ïò?ó¨ Ï∂úÎ†•
	 * @param key
	 * @return
	 */
	public static String publicKeyToBase64(PublicKey key){
		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(key.getEncoded());
        return new String(Base64Coder.encode(publicSpec.getEncoded()));
	}
	
	/**
	 * ?ïò?Çò?ùò ?Ç§?éò?ñ¥Î•? ÎπÑÎ??Ç§, Í≥µÍ∞ú?Ç§ ?àú?úºÎ°? base64 ?óîÏΩîÎî©?ïò?ó¨ Î¨∏Ïûê?ó¥ Î∞∞Ïó¥Î°? Ï∂úÎ†•
	 * @param kp
	 * @return
	 */
	public static String[] keyToBase64(KeyPair kp){
		String[] result = new String[2];
		result[0] = privateKeyToBase64(kp.getPrivate());
        result[1] = publicKeyToBase64(kp.getPublic());
        return result;
	}
	
	/**
	 * base64 ?óîÏΩîÎî© Ï≤òÎ¶¨?êú ÎπÑÎ??Ç§Î•? ÎπÑÎ??Ç§ Í∞ùÏ≤¥Î°? Î≥??ôò
	 * @param privateKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey stringToPrivateKey(String privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
		if(Security.getProvider("BC")==null){
			Security.addProvider(new BouncyCastleProvider());
		}
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64Coder.decode(privateKey));
		return keyFactory.generatePrivate(privateSpec);
	}
	
	/**
	 * base64 ?óîÏΩîÎî© Ï≤òÎ¶¨?êú Í≥µÍ∞ú?Ç§Î•? Í≥µÍ∞ú?Ç§ Í∞ùÏ≤¥Î°? ?†Ñ?ôò
	 * @param publicKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey stringToPublicKey(String publicKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
		if(Security.getProvider("BC")==null){
			Security.addProvider(new BouncyCastleProvider());
		}
		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64Coder.decode(publicKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
		return keyFactory.generatePublic(publicSpec);
	}
	
	/**
	 * base64 ?óî?Ü†?î© Ï≤òÎ¶¨?êú ÎπÑÎ??Ç§, Í≥µÍ∞ú?Ç§Î•? ?Ç§?éò?ñ¥ Í∞ùÏ≤¥Î°? ?†Ñ?ôò
	 * @param privateKey
	 * @param publicKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	public static KeyPair stringToKeyPair(String privateKey, String publicKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
		PrivateKey privKey = null;
		PublicKey pubKey = null;
		if(privateKey!=null){
			privKey =  stringToPrivateKey(privateKey);
		}
		if(publicKey != null){
			pubKey =  stringToPublicKey(publicKey);
		}
		return new KeyPair(pubKey, privKey);
	}
}