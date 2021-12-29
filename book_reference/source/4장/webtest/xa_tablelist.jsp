<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<html>
<head>
<title> ����ڰ� ������ �ִ� ��� ���̺� ���� </title>
</head>
<body>
	<h3>
<%
	Context ctx = null;
	DataSource ds = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	UserTransaction tx = null;

	String sql = "select * from tab";

	try{
		ctx = new InitialContext();
		tx = (UserTransaction)ctx.lookup("javax.transaction.UserTransaction");
		tx.begin();
		ds = (javax.sql.DataSource)ctx.lookup("oraxa9");
		con = ds.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			out.println(rs.getString(1) + "&nbsp;&nbsp;&nbsp;" + rs.getString(2));
		}
		tx.commit();
	}catch(Exception e){
		out.println(e.toString());
	}finally{
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException ex){}
		}
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException ex){}
		}
		if(con != null){
			try{
				con.close();
			}catch(SQLException ex){}
		}
	} // finally
%>
	</h3>
</body>
</html>
