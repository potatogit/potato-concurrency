package com.potato.concurrency;

import com.potato.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		log.info("do filter, {}", httpServletRequest.getPathInfo());
		RequestHolder.setId(Thread.currentThread().getId());
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		log.info("filter destroy");
		RequestHolder.remove();
	}
}
