package com.Filter;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Map;


public class LoginFilter   implements Filter {
    private Log log = LogFactory.getLog(LoginFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.error("initinitinitinitinitinitinitinitinit");
         }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws  IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
          log.error("doFilterdoFilterdoFilterdoFilterdoFilter");
         try{
            String   asd=getIpAddr(req);
             String aa=req.getRequestURI();
             StringBuffer  url=req.getRequestURL();
             Enumeration sad=request.getParameterNames();
             Map map1= request.getParameterMap();
             log.error("ip------"+asd+"-----urlurl"+aa);
             log.error("url"+url.toString());
        }catch (Exception var31){

        }

        chain.doFilter(request,response);

    }

    public void destroy() {
        log.error("destroydestroydestroydestroy");
    }
    public String getIpAddr(HttpServletRequest request)  throws Exception  {
        String ipAddress = null;

        String[] header = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP"};
        for (int i = 0; i < header.length; i++) {
            ipAddress = request.getHeader(header[i]);
            if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                continue;
            } else {
                break;
            }
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                   log.error("ipAddressException",e);
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length() = 15
        if (!StringUtils.isEmpty(ipAddress) && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        } else if (StringUtils.isEmpty(ipAddress)) {
            throw new Exception("获取ip异常");
        }
        return ipAddress;
    }
}