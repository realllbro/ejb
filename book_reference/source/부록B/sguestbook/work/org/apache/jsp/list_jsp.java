package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kr.co.hanbitbook.ejb.guestbook.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=euc-kr");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>방명록 리스트 보기</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body bgcolor=\"#FFFFFF\">\r\n");

   boolean loginflag = false;
	Cookie[] c = request.getCookies();
	for(int i = 0; i < c.length; i++){
		String cname = c[i].getName();
		if(cname.equals("id")){
			if(c[i].getValue().equals("admin")){
				loginflag = true;
				break;
			}
		}
	} // for

      out.write("\r\n");
      out.write("\r\n");
      out.write("<h3>방명록 리스트 보기</h3>\r\n");

	java.util.ArrayList list = (java.util.ArrayList)request.getAttribute("list");
	String spage = (String)request.getAttribute("spage");

      out.write("\r\n");
      out.write("\r\n");

	for(int i = 0; i < list.size(); i++){
		GuestbookDataBean gdb = (GuestbookDataBean)list.get(i);

      out.write("\r\n");
      out.write("<table border=1 width=500>\r\n");
      out.write("<tr>\r\n");
      out.write("<td width=100>순번</td>\r\n");
      out.write("<td width=400>");
      out.print(gdb.getSeq());
      out.write('\r');
      out.write('\n');
 if(loginflag){

      out.write("\r\n");
      out.write("<a href=delete.do?seq=");
      out.print(gdb.getSeq());
      out.write(">삭제</a>\r\n");

}

      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<td>이름</td>\r\n");
      out.write("<td>");
      out.print(gdb.getName());
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<td>내용</td>\r\n");
      out.write("<td>");
      out.print(gdb.getContent());
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table><br/>\r\n");
      out.write("\r\n");

	}

      out.write("\r\n");
      out.write("<br><br>\r\n");
      out.write("<a href=writeform.do>글쓰기</a>\r\n");
      out.write("&nbsp; &nbsp; &nbsp;\r\n");
      out.write("\r\n");

	if(!loginflag){

      out.write("\r\n");
      out.write("<a href=loginform.do>로그인</a>\r\n");

	}else{

      out.write("\r\n");
      out.write("<a href=logout.do>로그아웃</a>\r\n");

	}

      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (pageContext != null) pageContext.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
