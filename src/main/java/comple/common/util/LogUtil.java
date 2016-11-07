package comple.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class LogUtil {

	@SuppressWarnings("unchecked")
	public static void writeBean(Logger log, Object bean, String message){
		Map<String, Object> map;
		String out = "";
		try {
			map = BeanUtils.describe(bean);
			for(String key : map.keySet()){
				out += "\n\t"+key+" : "+map.get(key);
			}
		} catch (IllegalAccessException e) {
			out = e.getMessage();
		} catch (InvocationTargetException e) {
			out = e.getMessage();
		} catch (NoSuchMethodException e) {
			out = e.getMessage();
		}
		log.info(message+out);
	}

	public static void writeErrorBean(Logger log, Object bean, String message, Exception ee) {
		Map<String, Object> map;
		String out = "";
		try {
			map = BeanUtils.describe(bean);
			for(String key : map.keySet()){
				out += "\n\t"+key+" : "+map.get(key);
			}
		} catch (IllegalAccessException e) {
			out = e.getMessage();
		} catch (InvocationTargetException e) {
			out = e.getMessage();
		} catch (NoSuchMethodException e) {
			out = e.getMessage();
		}
		log.error(message+out, ee);
	}
}
