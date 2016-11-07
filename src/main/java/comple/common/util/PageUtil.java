package comple.common.util;

import java.util.HashMap;
import org.apache.ibatis.session.RowBounds;


public class PageUtil {
//	public static void setPageValue(HttpServletRequest request, HashMap<String, Object> map, ModelAndView mav, int total){
//		String row = request.getParameter("row");
//		if(row == null) row = Integer.toString(PageCode.PAGE_ROW);
//		int pageRow = Integer.parseInt(row);
//		
//		setPageValue(request, map, mav, total, pageRow);
//	}
	
//	public static LoginInfo setSessionInfo(HttpServletRequest req, User user){
//		LoginInfo info = new LoginInfo();
//		info.setInfos(user);
//		HttpSession ss = req.getSession(true);
//		ss.setAttribute("loginInfo", info);
//		return info;
//	}
	
	/**
	 * Í¥?Î¶¨Ïûê Î°úÍ∑∏?ù∏ ?Ñ∏?Öò Í∞??†∏?ò§Í∏?
	 * @param req
	 * @param exception
	 * @return
	 * @throws VlocallyException
	 */
//	public static LoginAdminInfo getAdminSessionInfo(HttpServletRequest req, boolean exception) throws VlocallyException{
//		HttpSession ss = req.getSession(false);
//		if(ss==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		LoginAdminInfo loginInfo = (LoginAdminInfo) ss.getAttribute("loginAdminInfo");
//		if(loginInfo==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		return loginInfo;
//	}
//	
//	public static LoginStoreInfo getStoreSessionInfo(HttpServletRequest req, boolean exception) throws VlocallyException {
//		HttpSession ss = req.getSession(false);
//		if(ss==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		LoginStoreInfo loginInfo = (LoginStoreInfo) ss.getAttribute("loginStoreInfo");
//		if(loginInfo==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		return loginInfo;
//	}
//	
//	public static LoginComInfo getCompanySessionInfo(HttpServletRequest req, boolean exception) throws VlocallyException {
//		HttpSession ss = req.getSession(false);
//		if(ss==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		LoginComInfo loginInfo = (LoginComInfo) ss.getAttribute("loginComInfo");
//		if(loginInfo==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		return loginInfo;
//	}
	
//	public static LoginUserInfo getUserSessionInfo(HttpServletRequest req, boolean exception) throws VlocallyException {
//		HttpSession ss = req.getSession(false);
//		if(ss==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		LoginUserInfo loginInfo = (LoginUserInfo) ss.getAttribute("loginUserInfo");
//		if(loginInfo==null){
//			if(exception){
//				throw new VlocallyException(Errors.General_NeedLogin);
//			} else {
//				return null;
//			}
//		}
//		return loginInfo;
//	}
//	
//	public static void setPageValue(HttpServletRequest request, HashMap<String, Object> map, ModelAndView mav, int total, int pageRow){
//		String page = request.getParameter("page");
//		if(page == null) page = "1";
//		int startLimit = (Integer.parseInt(page) - 1) * pageRow;	
//		
//		map.put("startLimit", startLimit);
//		map.put("pageRow", pageRow);
//		
//		mav.addObject("page", page);
//		mav.addObject("pageRow", pageRow);
//		mav.addObject("total", total);
//		
//		int pageCount = (int) Math.ceil((float)total/(float)pageRow);
//		if(pageCount <= 0) pageCount = 1;
//		mav.addObject("pageCount", pageCount);
//	}

	public static RowBounds setPageValue(HashMap<String, Integer> listinfo,	Integer total) {
		Integer page = listinfo.get("page");
		Integer pageRow = listinfo.get("pageRow");
		Integer offset = (page-1)*pageRow;
		listinfo.put("total", total);
		
//		Integer pageCount = (int) Math.ceil((float)total/(float)pageRow);
//		listinfo.put("pageCount", pageCount);
		return new RowBounds(offset, pageRow);
	}

	public static HashMap<String, Integer> genListInfo(int page, int pageRow) {
		HashMap<String, Integer> listinfo = new HashMap<String, Integer>();
		listinfo.put("page", page);
		listinfo.put("pageRow", pageRow);
		return listinfo;
	}



}
