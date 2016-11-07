package comple.common.util;

import java.security.SecureRandom;

public class NumberUtil {

	public static String genRandDec(String seed, int digit){
		String result = "";
		SecureRandom sr = new SecureRandom(seed.getBytes());
		int r = (digit/9)+1;
		byte[] rand = sr.generateSeed(r*4);
		int cap=0, offset = 0;
		do{
			result +=Integer.toString(toInt(rand, offset*4));
			cap = result.length() - digit;
			if(offset<r){
				offset++;
			} else {
				offset = 0;
			}
		} while(cap<0);
		
		return result.substring(cap);
	}
	
	public static String genRandHex(String seed, int digit){
		String result = "";
		SecureRandom sr = new SecureRandom(seed.getBytes());
		int r = (digit/8)+1;
		byte[] rand = sr.generateSeed(r*4);
		result = byteArrayToHex(rand);
		int cap = result.length() - digit;
		return result.substring(cap);
	}
	
	/**
	 * bytes ?˜ ?¬ê¸°ëŠ” 4 ?´?ƒ?´?–´?•¼ ?•œ?‹¤
	 * @param bytes
	 * @param offset
	 * @return
	 */
	public static int toInt(byte[] bytes, int offset) {
		int ret = 0;
		for (int i = 0; i<4 && i+offset < bytes.length; i++) {
			ret <<= 8;
			ret |= (int) bytes[i+offset] & 0xFF;
		}
		return Math.abs(ret);
	}
	
	public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }
}
