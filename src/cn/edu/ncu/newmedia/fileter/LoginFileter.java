package cn.edu.ncu.newmedia.fileter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFileter
 * @author xuguanhua
 */
@WebFilter("/LoginFileter")
public class LoginFileter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFileter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 判断用户是否是通过登录页面合法的进行数据访问和操作
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//设置编码方式
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//获取根目录所对应的绝对路径
		String currentURL = req.getRequestURI();
		//截取到当前文件名用于比较
		String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
		
		//获取到当前的session对象
		HttpSession session = req.getSession(false);
		
		//判断
		if(!"/index.html".equals(targetURL)){
			//判断当前页面是否是重定向后的登录页面，如果是就不做session的判断，防止死循环
			if(session==null||session.getAttribute("communityid")==null){
				//如果session为空表示用户没有登录就重定向到login.jsp页面
				res.sendRedirect(req.getContextPath()+"/index.html");
				return;
			}
				
		}
		//继续往下面执行
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 初始化方法
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
