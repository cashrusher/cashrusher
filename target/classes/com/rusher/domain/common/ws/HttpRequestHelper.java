package com.rusher.domain.common.ws;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public abstract class HttpRequestHelper {
    public static String getRequestUri(HttpServletRequest httpServletRequest) {
        final String contextPath = httpServletRequest.getContextPath();
        final String requestURI = httpServletRequest.getRequestURI();
        if (contextPath.equals("") || contextPath.equals("/")) {
            return requestURI;
        }
        if (contextPath.endsWith("/")) {
            return requestURI.substring(contextPath.length() - 1);
        }
        return requestURI.substring(contextPath.length());
    }

    public static byte[] getContent(HttpServletRequest httpServletRequest) throws IOException {
//        if (HttpHeaderHelper.hasContentLength(httpServletRequest)) {
//            return IOUtils.readBytes(httpServletRequest.getInputStream(), HttpHeaderHelper.getContentLength(httpServletRequest));
//        } else {
//            return IOUtils.readBytes(httpServletRequest.getInputStream());
//        }
        return null;
    }

    public static String toRequestMessage(Map<String, Object> request, String charset) {
//        try {
//            final StringBuilder sb = new StringBuilder();
//            for (Map.Entry<String, Object> entry : request.entrySet()) {
//                if (sb.length() > 0) {
//                    sb.append("&");
//                }
//                sb.append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : URLEncoder.encode(entry.getValue().toString(), charset));
//            }
//            return sb.toString();
//        } catch (Exception e) {
//            throw ExceptionUtils.toRuntimeException(e);
//        }
        return null;
    }

    public static String[] getParameters(HttpServletRequest request, String name) {
        return request.getParameterValues(name);
    }

    public static String getParameter(HttpServletRequest request, String name) {
        String[] parameters = getParameters(request, name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        if (parameters.length == 1) {
            return trimToNull(parameters[0]);
        }

        throw newException("Duplicate", name);
    }

    public static String getRequiredParameter(HttpServletRequest request, String name) {
        String parameter = getParameter(request, name);
        if (parameter == null) {
            throw newException("Missing", name);
        }
        return parameter;
    }

    public static int getPageNumber(HttpServletRequest request) {
        String page = getParameter(request, "pageNumber");
        page = page == null ? getParameter(request, "page") : page;
        try {
            int value = page == null ? 1 : Integer.parseInt(page);
            if (value <= 0) {
                return 1;
            }
            return value;
        } catch (Exception e) {
            return 1;
        }
    }

    public static int getPageSize(HttpServletRequest request) {
        final String pageSize = getParameter(request, "pageSize");
        try {
            int value = pageSize == null ? 20 : Integer.parseInt(pageSize);
            if (value <= 0) {
                return 20;
            }
            return value;
        } catch (Exception e) {
            return 20;
        }
    }

    public static Boolean getBooleanParameter(HttpServletRequest request, String name) {
        final String v = getParameter(request, name);
        if (v == null) {
            return null;
        }

        if ("true".equalsIgnoreCase(v)) {
            return true;
        } else if ("false".equalsIgnoreCase(v)) {
            return false;
        } else {
            throw newException("Illegal", name);
        }
    }

    public static boolean getRequiredBooleanParameter(HttpServletRequest request, String name) {
        final Boolean value = getBooleanParameter(request, name);
        if (value == null) {
            throw newException("Missing", name);
        }
        return value;
    }


    public static Long getLongParameter(HttpServletRequest request, String name) {
        final String v = getParameter(request, name);
        if (v == null) {
            return null;
        }

        try {
            return Long.parseLong(v);
        } catch (Exception e) {
            throw newException("Illegal", name);
        }
    }

    public static long getRequiredLongParameter(HttpServletRequest request, String name) {
        final Long value = getLongParameter(request, name);
        if (value == null) {
            throw newException("Missing", name);
        }
        return value;
    }

    public static Integer getIntegerParameter(HttpServletRequest request, String name) {
        final String v = getParameter(request, name);
        if (v == null) {
            return null;
        }

        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            throw newException("Illegal", name);
        }
    }

    public static int getRequiredIntegerParameter(HttpServletRequest request, String name) {
        final Integer value = getIntegerParameter(request, name);
        if (value == null) {
            throw newException("Missing", name);
        }
        return value;
    }

    public static Date getDateParameter(HttpServletRequest request, String name) {
//        final String v = getParameter(request, name);
//        if (v == null) {
//            return null;
//        }
//
//        try {
//            return DateUtils.parse(v);
//        } catch (Exception e) {
//            throw newException("Illegal", name);
//        }
        return null;
    }

    public static Date getRequiredDateParameter(HttpServletRequest request, String name) {
        final Date value = getDateParameter(request, name);
        if (value == null) {
            throw newException("Missing", name);
        }
        return value;
    }

    public static <T extends Enum> T getEnumParameter(HttpServletRequest request, String name, Class<T> type) {
        final String v = getParameter(request, name);
        if (v == null) {
            return null;
        }

        try {
            return (T) Enum.valueOf(type, v);
        } catch (Exception e) {
            throw newException("Illegal", name);
        }
    }

    public static <T extends Enum> T getRequiredEnumParameter(HttpServletRequest request, String name, Class<T> type) {
        final T value = getEnumParameter(request, name, type);
        if (value == null) {
            throw newException("Missing", name);
        }
        return value;
    }

    private static IllegalArgumentException newException(String error, String name) {
        return newException(error, name, null);
    }

    private static IllegalArgumentException newException(String error, String name, RuntimeException e) {
        char[] chars = name.toCharArray();
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (i == 0) {
                n.append(Character.toUpperCase(aChar));
            } else if (aChar >= 'A' && aChar <= 'Z') {
                n.append(' ').append(aChar);
            } else {
                n.append(aChar);
            }
        }

        return new IllegalArgumentException(error + ":" + n.toString(), e);
    }

    private static String trimToNull(String v) {
        if (v == null) {
            return null;
        }
        v = v.trim();
        return "".equals(v) ? null : v;
    }
}
