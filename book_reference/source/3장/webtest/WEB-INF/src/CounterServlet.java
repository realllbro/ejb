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
        // count.jsp에게 증가된 카운트 값을 RequestDispatcher를 이용하여 전달
        // 하고 있다.
		count++;
        request.setAttribute("count", new Integer(count));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/count.jsp");
        rd.forward(request, response);
	}// doGet
}
