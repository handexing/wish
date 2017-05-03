package com.wish.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginFilter implements Filter {

    Logger log = LoggerFactory.getLogger(LoginFilter.class);

	public void destroy() {
	}

    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) sRequest;
        final String uri = request.getRequestURI();

        log.debug("url:{}", uri);

		final String[] ignoreList = { "login.jsp", "user/login", "user/logout", ".js", ".css", ".png",
				".jpg",
				".gif",
                ".map", "ttf", ".woff"};

        boolean ignore = false;
        for (final String path : ignoreList) {
            if (uri.endsWith(path)) {
                ignore = true;
                break;
            }
        }

        final Object admin = request.getSession().getAttribute("user");

        if (admin != null || ignore) {
            chain.doFilter(sRequest, sResponse);
        } else {
            final HttpServletResponse response = (HttpServletResponse) sResponse;
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }
}
