package com.chenjx.redwars.interceptor;

import com.chenjx.redwars.constant.Constants;
import com.chenjx.redwars.result.GlobalErrorInfoEnum;
import com.chenjx.redwars.result.GlobalErrorInfoException;
import com.chenjx.redwars.utils.RedisOperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Interceptor implements HandlerInterceptor {
	
	@Autowired
	public RedisOperationUtils redisOperationUtils;
	
	public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse reponse, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse reponse,
                           Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
		
	}

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse,
                             Object arg2) throws Exception {
		request.setCharacterEncoding("utf-8");
		String seveletPath=request.getServletPath();
		System.out.println(seveletPath);
		if(seveletPath.equals(Constants.USER_LOGIN) || seveletPath.equals(Constants.USER_REGISTER)){
			return true;
		}else{
			String token = request.getHeader("token");
			if(StringUtils.isEmpty(token)) {
				throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NO_TOKEN);
			}
			String content = redisOperationUtils.get(Constants.TOKEN + token);

			if(StringUtils.isEmpty(content)){
				throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NO_TOKEN);
			}else{
				// 延长失效时间
				redisOperationUtils.expire(Constants.TOKEN + token, Constants.EXP_TIMES);
				return true;
			}
		}
//		return true;
	}
}
