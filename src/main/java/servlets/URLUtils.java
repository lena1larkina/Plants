package servlets;

import jakarta.servlet.http.HttpServletRequest;

public class URLUtils {
    public static String getFullURLForRedirect(HttpServletRequest request, String baseURL) {
        return  request.getContextPath() + baseURL;
    }
}
