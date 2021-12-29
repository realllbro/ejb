package my.count;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CounterServlet extends HttpServlet {
	private int count = 0;

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		doGet(request, response);
	} // doPost

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        // count.jsp���� ������ ī��Ʈ ���� RequestDispatcher�� �̿��Ͽ� ����
        // �ϰ� �ִ�.
		count++;
        request.setAttribute("count", new Integer(count));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/count.jsp");
        rd.forward(request, response);
	}// doGet
}
