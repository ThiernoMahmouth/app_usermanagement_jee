package filter;

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

@WebFilter("/users/*")
public class AuthenticationFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (request.getSession().getAttribute("utilisateur") == null)
		{
			response.sendRedirect(request.getContextPath() + "/login");
		}
		else
		{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() 
	{		
	}

	
	
}
