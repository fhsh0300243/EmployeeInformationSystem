package tw.eis.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class OpenSessionViewFilter implements Filter {


	private SessionFactory sessionFactory;
	private WebApplicationContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		String sessionFactoryBeanName = fConfig.getInitParameter("sessionFactoryBeanName");
		ServletContext application = fConfig.getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory)context.getBean(sessionFactoryBeanName);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Session session = sessionFactory.getCurrentSession();
		try {			
			session.beginTransaction();
			//System.out.println("Transaction begin.");
			chain.doFilter(request, response);
			session.getTransaction().commit();
		}catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("Transaction rollback.");
			System.out.println("From filter:"+e);
			chain.doFilter(request, response);
		}finally {
			//System.out.println("Session close.");
		}
	}

	public void destroy() {
		((ConfigurableApplicationContext)context).close();
	}

}