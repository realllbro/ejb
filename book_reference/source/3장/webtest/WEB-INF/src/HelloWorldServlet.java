package examples;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HelloWorldServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>�ȳ��ϼ���. Hello</title></head>");
		out.println("<body>�ȳ��ϼ���. ����<br> Hello World</body>");
		out.println("</html>");
	}
}