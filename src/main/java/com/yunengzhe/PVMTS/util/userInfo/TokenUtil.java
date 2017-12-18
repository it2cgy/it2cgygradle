package com.yunengzhe.PVMTS.util.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.AESUtil;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.PropertiesUtil;

public class TokenUtil {
	private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class); 

	public static final String CACHE_USERBYID = "PVMTS_USERBYID";
	
	/**
	 * token 解密 并获取用户信息
	 * @param token
	 * @return
	 */
	public static UserVO getTokenInfoFromToken(String token){
		if(StringUtils.isBlank(token)){ 
			logger.error("token is null");
			return null;
		} 
 
		String key= PropertiesUtil.getString("token_key","2wscde3!&werqqwe");
		String tokenStr = "";
		try {
			tokenStr = AESUtil.decrypt(token, key);//.decrypt(token, key);
			if(StringUtils.isBlank(tokenStr) || !StringUtils.isNumeric(tokenStr)){
				logger.error("bad token:"+tokenStr);
				return null;
			}
			UserVO user = CacheManUtil.getObjectFromJson(CACHE_USERBYID,tokenStr,UserVO.class);
			return user;
		} catch (Exception e) { 
			e.printStackTrace();
			logger.error(" tokenStr:"+tokenStr,e );
		}
		return null;
	}
	
	/**
	 * @Title:getLoginUser
	 * @Description:TODO(获取登录的用户信息) 
	 * @param @return
	 * @return UserVO
	 * @throws
	 */
	public static UserVO getLoginUser(){
		UserVO userVO = (UserVO) HttpSessionUtil.getAttribute("user");
		if(userVO!=null){//web请求
			return userVO;
		}
		//ajax
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		String token = request.getHeader("token");
		return getTokenInfoFromToken(token); 
	}
	
	/**
	 * @Title:isLogin
	 * @Description:TODO(判断用户是否登录) 
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isLogin(){
		UserVO userVO = (UserVO) HttpSessionUtil.getAttribute("user");
		if(userVO!=null){
			return true;
		}
		 
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		String token = request.getHeader("token");
		return isLogin(token);
	}
	
	/**
	 * @Title:isLogin
	 * @Description:TODO(通过代入的token判断用户是否登录) 
	 * @param @return
	 * @return Boolean
	 * @throws
	 */
	public static boolean isLogin(String token){
		if(StringUtils.isBlank(token)){ 
			logger.error("token is null");
			return false;
		} 
		String key= PropertiesUtil.getString("token_key","2wscde3!&werqqwe");
		String tokenStr = "";
		try {
			tokenStr = AESUtil.decrypt(token, key);
			if(StringUtils.isBlank(tokenStr) || !StringUtils.isNumeric(tokenStr)){
				logger.error("bad token:"+tokenStr);
				return false;
			}
			UserVO user = CacheManUtil.getObjectFromJson(CACHE_USERBYID,tokenStr,UserVO.class);
			if(user!=null){
				return true;
			}
			return false;
		} catch (Exception e) { 
			e.printStackTrace();
			logger.error(" tokenStr:"+tokenStr,e );
		}
		return false;
	} 
	
	/**
	 * @Title:getUserIdByToken
	 * @Description:TODO(通过token获取用户id 用户id不存在返回0) 
	 * @param @param token
	 * @param @return
	 * @return int
	 * @throws
	 */
	public static int getUserIdByToken(String token){
		if(StringUtils.isBlank(token)){ 
			logger.error("token is null");
			return 0;
		} 
		String key= PropertiesUtil.getString("token_key","2wscde3!&werqqwe");
		String tokenStr = "";
		try {
			tokenStr = AESUtil.decrypt(token, key);//.decrypt(token, key);
			if(StringUtils.isBlank(tokenStr) || !StringUtils.isNumeric(tokenStr)){
				logger.error("bad token:"+tokenStr);
				return 0;
			}
			int userId = Integer.valueOf(tokenStr);
			return userId;
		} catch (Exception e) { 
			e.printStackTrace();
			logger.error(" tokenStr:"+tokenStr,e );
		}
		return 0;
	}
	
	public static void main(String[] args){
		int userId = getUserIdByToken("DA58A5485E52B2A5DA3EA90F367A1636");
		System.out.println("用户id"+userId);
	}
}
