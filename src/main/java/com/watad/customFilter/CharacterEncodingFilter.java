package com.watad.customFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;


public class CharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("im called here init");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("im called here onn");

        // Specify the source and target encodings
        String sourceEncoding = "ISO-8859-1";
        String targetEncoding = "UTF-8";

        // Iterate through all request parameters
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String parameterName = entry.getKey();
            System.out.println(parameterName);
            String[] parameterValues = entry.getValue();
            for (int i = 0; i < parameterValues.length; i++) {
                try {
                    // Convert each parameter value to the target encoding
                    parameterValues[i] = new String(parameterValues[i].getBytes(sourceEncoding), targetEncoding);
                    System.out.println("changed :"+parameterValues[i]);
                    request.setAttribute(parameterName, parameterValues[i]);
                } catch (UnsupportedEncodingException e) {
                    System.err.println("err happened in encoding filter: "+e.getMessage());
                }
            }
        }

        // Continue the filter chain
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("im called here destroy");
		
	}

}
