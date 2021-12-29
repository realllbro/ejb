<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<html>
<head>
<title> 사용자가 가지고 있는 모든 테이블 보기 </title>
</head>
<body>
	<h3>
<%
	Context ctx = null;
	DataSource ds = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	String sql = "select * from tab";

	try{
		java.util.Properties p = new java.util.Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, "t3://localhost:7001");
		ctx = new InitialContext(p);
		ds = (javax.sql.DataSource)ctx.lookup("ora9");
		con = ds.getConnection();
		con.setAutoCommit(false);
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			out.println(rs.getString(1) + "&nbsp;&nbsp;&nbsp;" + rs.getString(2));
		}
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
