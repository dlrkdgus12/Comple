package comple.common.util;

import java.util.HashMap;

public class JSONResultBuilder {
	
	/**
	 * result �� null ���� �ѱ�� ���� ���� ��ȯ</br>
	 * result �� content : content ����
	 * result �� status : status �� ����
	 * @param result
	 * @param content
	 * @param status
	 * @return
	 */
	public static HashMap<String, Object> buildResult(HashMap<String, Object> result, Object content, int status){
		if(result==null){
			result = new HashMap<String, Object>();
		}
		result.put("content", content);
		result.put("status", status+"");
		
		return result;
	}
	
	public static HashMap<String, Object> buildResult(HashMap<String, Object> result, int status, String message){
		if(result==null){
			result = new HashMap<String, Object>();
		}
		result.put("status", status+"");
		result.put("message", message);
		return result;
	}

	/**
	 * ��û�� ���� ����(���ϰ� ����)
	 * @param status
	 * @param msg
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap <String, Object> buildResult(int status, String msg){
		HashMap result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}
}
