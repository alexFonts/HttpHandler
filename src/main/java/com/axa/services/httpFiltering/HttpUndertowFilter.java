package com.axa.services.httpFiltering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.ResponseCodeHandler;
import io.undertow.util.HeaderValues;

public class HttpUndertowFilter implements HttpHandler {

    private static final Logger log = LoggerFactory.getLogger(HttpUndertowFilter.class);

    private HttpHandler next;
    private String param1;

    public HttpUndertowFilter(HttpHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        //
        // it is very important NOT to run anything that could block the thread - this is executed by one of the
        // XNIO Worker's IO threads.
        //
    	//ResponseCodeHandler responseCode = ResponseCodeHandler.HANDLE_403;
    	//exchange.getResponseSender().send();
    	
    	
        //next.handleRequest(exchange);
        
        
        for(HeaderValues header : exchange.getRequestHeaders()) {
            for(String value : header) {
            	log.debug(header.getHeaderName() + ": " + value );
            }
        }
        
        ResponseCodeHandler.HANDLE_403.handleRequest(exchange);
    }

    public void setParam1(String s) {
        this.param1 = s;
    }

}
