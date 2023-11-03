package nr.springfaas.filters;

import java.io.IOException;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FaaSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var res = (HttpServletResponse) response;

        if (req.getHeader("Authorization") == null || !req.getHeader("Authorization").contains("947cf9e2-1765-4635-e980-dd2a966700cd")) {
            res.getOutputStream().write("{ \"error\": \"🔒\" }".getBytes());
            return;
        }
        chain.doFilter(request, response);
    }
}
