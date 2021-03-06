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
	 * ??±?λ‘? base64 ?μ½λ© μ²λ¦¬? κ³΅κ°?€ λΉκ³΅κ°ν€ ?? ₯
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
	 * ??Έ?, λ³΅νΈ?
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
	 * RSA ?€? ?΄ λ©μ??€
	 * =============================================================
	 */
	
	/**
	 * ?λ‘μ΄ λΉλ?μΉ? ?€ ??± 
	 * @param length
	 * 		128, 256, 512, 1024, 2048 ?¨?λ‘? ?? ₯? κ²?
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
	 * λΉλ??€λ₯? base64λ‘? ?μ½λ©??¬ μΆλ ₯
	 * @param key
	 * @return
	 */
	public static String privateKeyToBase64(PrivateKey key){
		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(key.getEncoded());
		return new String(Base64Coder.encode(privateSpec.getEncoded()));
	}
	
	/**
	 * κ³΅κ°?€λ₯? base 64λ‘? ?μ½λ©??¬ μΆλ ₯
	 * @param key
	 * @return
	 */
	public static String publicKeyToBase64(PublicKey key){
		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(key.getEncoded());
        return new String(Base64Coder.encode(publicSpec.getEncoded()));
	}
	
	/**
	 * ??? ?€??΄λ₯? λΉλ??€, κ³΅κ°?€ ??Όλ‘? base64 ?μ½λ©??¬ λ¬Έμ?΄ λ°°μ΄λ‘? μΆλ ₯
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
	 * base64 ?μ½λ© μ²λ¦¬? λΉλ??€λ₯? λΉλ??€ κ°μ²΄λ‘? λ³??
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
	 * base64 ?μ½λ© μ²λ¦¬? κ³΅κ°?€λ₯? κ³΅κ°?€ κ°μ²΄λ‘? ? ?
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
	 * base64 ?? ?© μ²λ¦¬? λΉλ??€, κ³΅κ°?€λ₯? ?€??΄ κ°μ²΄λ‘? ? ?
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