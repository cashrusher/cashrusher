package com.rusher.interfaces.ws.endpoint;

import com.rusher.ws.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.transport.TransportConstants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * Author: Liam
 * Date: 2016/9/30
 */

public class WebServiceRequestEndPoint extends HttpServlet {
    private final Log logger = LogFactory.getLog("ERR_LOG");
    private final WebServiceRequestMessageReader reader;
    private final WebServiceRequestReplyWriter writer;
    private final WebServiceRequestValidator validator;
    private final WebServiceMessageMarshaller marshaller;
    private final WebServiceRequestProcessService<Object, Object> processService;

    public WebServiceRequestEndPoint(WebServiceRequestMessageReader reader, WebServiceRequestReplyWriter writer, WebServiceMessageMarshaller marshaller, WebServiceRequestProcessService<Object, Object> processService) {
        this(reader, writer, null, marshaller, processService);
    }

    public WebServiceRequestEndPoint(WebServiceRequestMessageReader reader, WebServiceRequestReplyWriter writer, WebServiceRequestValidator validator, WebServiceMessageMarshaller marshaller, WebServiceRequestProcessService<Object, Object> processService) {
        this.reader = reader;
        this.writer = writer;
        this.validator = validator;
        this.marshaller = marshaller;
        this.processService = processService;
    }

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            validateWebParam(httpServletRequest);
            final WebServiceRequestMessage message = reader.read(httpServletRequest);
            final Object request = marshaller.unmarshal(WebServiceRequestMessageHelper.toString(message));
            final Object response = processService.process(request, message);
            write(httpServletRequest, httpServletResponse, message.getCharset(), marshaller.marshal(response));
        } catch (Throwable e) {
            writeException(httpServletRequest, httpServletResponse, reader.getRequestCharset(httpServletRequest), e);
        }
    }

    private void validateWebParam(HttpServletRequest httpServletRequest) {
        if (validator != null) {
            validator.validate(httpServletRequest);
        }
    }

    private void write(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Charset charset, String response) {
        try {
            httpServletResponse.addHeader(TransportConstants.HEADER_ACCEPT, httpServletRequest.getContentType());
            writer.write(httpServletRequest, httpServletResponse, charset, response);
        } catch (Throwable we) {
            logger.error(we.getMessage(), we);
        }
    }

    private void writeException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Charset charset, Throwable e) {
        try {
            writer.writeError(httpServletRequest, httpServletResponse, charset, e);
        } catch (Throwable we) {
            logger.error(we.getMessage(), we);
        }
    }
}
