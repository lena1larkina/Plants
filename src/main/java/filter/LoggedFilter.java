package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlets.URLUtils;

import java.io.IOException;

@WebFilter("/plants/*")
public class LoggedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            ((HttpServletResponse) response).sendRedirect(URLUtils.getFullURLForRedirect(httpServletRequest, "/login"));
            return;
        }

        chain.doFilter(request, response);
    }
}
