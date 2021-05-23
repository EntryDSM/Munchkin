package kr.hs.entrydsm.main.security.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestLogger extends OncePerRequestFilter {

    private final LogWriter logWriter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
        logRequest(requestWrapper, response);
    }

    private void logRequest(RequestWrapper request, HttpServletResponse response) throws IOException {
        // 2021-05-18 15:51:24.102 :: 127.0.0.1 [POST] (/user/auth?asd=asd 200) {asd: asd}
        String requestTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String requestIP = request.getRemoteHost();
        String method = request.getMethod();
        String url = request.getRequestURI();
        String params = request.getParamsString();
        int statusCode = response.getStatus();
        String body = request.getBody();

        logWriter.writeLog(String.format("%s :: %s [%s] (%s%s %d) %s",
                requestTime, requestIP, method, url, params, statusCode, body));
    }

}
