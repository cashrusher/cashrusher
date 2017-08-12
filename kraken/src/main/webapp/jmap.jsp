<%@ page import="java.io.*" %>
<%@ page import="java.lang.management.ManagementFactory" %>
<%@ page import="java.nio.charset.Charset" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: white;font-family: 'Arial';font-size: 12px;">
<head>
    <title>JMap</title>
</head>
<body style='font-family: 宋体,Arial;font-size: 12px;'>
<div>showHisto=true:显示详情</div>
<div>showHisto=true&live=true:先做full gc，然后显示详情</div>

<%
    try {
        String jdk = getJdk(request, out);
        if (jdk == null) {
            return;
        }
        String live = request.getParameter("live");
        String showHisto = request.getParameter("showHisto");

        String pid = ManagementFactory.getRuntimeMXBean().getName();
        int indexOf = pid.indexOf('@');
        if (indexOf > 0) {
            pid = pid.substring(0, indexOf);
        }

        firstOut = true;
        execHeap(out, jdk, pid);
        execGcCapacity(out, jdk, pid);
        execGc(out, jdk, pid);
        if (showHisto != null && "true".equals(showHisto.trim())) {
            execHisto(out, jdk, pid, live);
        }
    } catch (Exception e) {
        e.printStackTrace(new PrintWriter(out));
    }
%>
</body>
</html>
<%!
    private String getJdk(HttpServletRequest request, JspWriter out) throws IOException {
        String jdk = request.getParameter("jdk");
        if (jdk != null && !jdk.trim().isEmpty()) {
            return jdk.trim();
        }

        jdk = System.getenv("JAVA_HOME");
        File unixJmap = new File(jdk + "/bin/jmap");
        if (unixJmap.exists() && unixJmap.isFile()) {
            return jdk;
        }

        File windowsJmap = new File(jdk + "/bin/jmap.exe");
        if (windowsJmap.exists() && windowsJmap.isFile()) {
            return jdk;
        }

        jdk = "/usr/local/platform/jdk";
        unixJmap = new File(jdk + "/bin/jmap");
        if (unixJmap.exists() && unixJmap.isFile()) {
            return jdk;
        }

        out.println("<div style='color:red;'>jdk dir is missing</div>");
        return null;
    }

    private boolean firstOut = true;

    private void execHisto(JspWriter out, String jdk, String pid, String live) throws IOException {
        final String cmd = live != null && "true".equalsIgnoreCase(live.trim()) ? "/bin/jmap -histo:live " : "/bin/jmap -histo ";
        Process process = Runtime.getRuntime().exec(jdk.trim() + cmd + pid);
        outValue(out, process, false, 0);
    }

    private void execHeap(JspWriter out, String jdk, String pid) throws IOException {
        Process process = Runtime.getRuntime().exec(jdk.trim() + "/bin/jmap -heap " + pid);
        outValue(out, process, false, 0);
    }

    private void execGcCapacity(JspWriter out, String jdk, String pid) throws IOException {
        Process process = Runtime.getRuntime().exec(jdk.trim() + "/bin/jstat -gccapacity " + pid);
        outValue(out, process, true, 14);
    }

    private void execGc(JspWriter out, String jdk, String pid) throws IOException {
        Process process = Runtime.getRuntime().exec(jdk.trim() + "/bin/jstat -gc " + pid);
        outValue(out, process, true, 10);
    }

    private void outValue(JspWriter out, Process process, boolean toTable, int n) throws IOException {
        if (firstOut) {
            firstOut = false;
        } else {
            out.println("<br>");
            out.println("<br>");
            out.println("=========================================================================================================================================================");
            out.println("<br>");
            out.println("<br>");
        }
        String info = toString(process.getInputStream()).trim();
        if (toTable) {
            while (info.contains("  ")) {
                info = info.replaceAll("  ", " ");
            }
            while (info.contains("\n ")) {
                info = info.replaceAll("\n ", "\n");
            }
            while (info.contains(" \n")) {
                info = info.replaceAll(" \n", "\n");
            }
            info = info.replaceAll("\n", "&nbsp;</td></tr><tr><td>&nbsp;");
            info = info.replaceAll(" ", "&nbsp;</td><td>&nbsp;");
            info = "<table name=" + n + " style='font-family: 宋体,Arial;font-size: 12px;'><tr><td>&nbsp;" + info + "&nbsp;</td></tr></table>";
        } else {
            info = "<pre style='font-family: 宋体,Arial;font-size: 12px;'>" + info + "</pre>";
        }
        out.println(info);
        process.getInputStream().close();
    }

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private String toString(InputStream input) throws IOException {
        return toString(input, Charset.defaultCharset());
    }

    private String toString(InputStream input, Charset encoding) throws IOException {
        StringWriter sw = new StringWriter();
        copyLarge(new InputStreamReader(input, toCharset(encoding)), sw, new char[DEFAULT_BUFFER_SIZE]);
        return sw.toString();
    }

    private long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    private Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }
%>

<script type="text/javascript">
    var tables = document.getElementsByTagName("table");
    for (var i = 0; i < tables.length; i++) {
        var table = tables[i];
        var number = table.getAttribute("name") - 1;
        var tds = table.getElementsByTagName("tr")[1].getElementsByTagName("td");
        for (var n = 0; n < tds.length; n++) {
            if (n > number) {
                continue;
            }
            var v = tds[n].innerHTML;
            v = v.replace("&nbsp;", "").replace("&nbsp;", "").trim() / 1024;
            tds[n].innerHTML = "&nbsp;" + v + "MB&nbsp;";
        }
    }
</script>