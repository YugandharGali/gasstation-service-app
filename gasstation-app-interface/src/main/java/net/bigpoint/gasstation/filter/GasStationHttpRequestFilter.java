package net.bigpoint.gasstation.filter;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import net.bigpoint.gasstation.service.UserService;

@WebFilter("/v1/gasstation/*")
public class GasStationHttpRequestFilter implements Filter {

	@Autowired
	private UserService userService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String accessToken = request.getHeader("api_key");
		System.out.println("accessToken :" + accessToken);
		if (null == accessToken) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), "Access Token (api_key) is null.");
			return;
		}

		// validate token is expired or not..
		// if expired mark expired='true' in User table
		boolean validate = userService.validateAccessToken(accessToken);
		if (validate) {
			chain.doFilter(req, res);
		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Access Token expired / invalid.");
			return;
		}
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}