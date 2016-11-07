package comple.common.util;

import java.util.HashMap;

public class JSONResultBuilder {
	
	/**
	 * result 에 null 값을 넘기면 새로 만들어서 반환</br>
	 * result 에 content : content 삽입
	 * result 에 status : status 로 삽입
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
	 * 요청에 대한 응답(리턴값 없음)
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
