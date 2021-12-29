<%@ page contentType="text/html;charset=EUC-KR"%> 
<%@ page import="java.sql.*"%> 
<%@ page import="javax.sql.*"%> 
<%@ page import="javax.rmi.*"%> 
<%@ page import="javax.naming.*"%> 
<%@ page import="javax.transaction.*"%> 
<%@ page import="net.sarang.sunny.ejb.exam.*"%> 
<html> 
<head> 
<title>Hello World CMPÀÇ ÀÌ¿ë</title> 
</head> 
<body> 
   <h3> 
<% 
   Context ctx = null; 
   String to = request.getParameter("to"); 
   if(to == null) to = "ksp"; 
    
   try{ 
      java.util.Properties p = new java.util.Properties(); 
      p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory"); 
      p.put(Context.PROVIDER_URL, "t3://localhost:7001"); 
      ctx = new InitialContext(p); 
      Object h = ctx.lookup("HelloEJB"); 
      HelloHome home = (HelloHome)PortableRemoteObject.narrow(h, HelloHome.class); 
      Hello Hello = home.create(); 
      Message m = new Message(); 
      m.setMessage(to); 
      out.println(Hello.hello(m)); 
   }catch(Exception e){ 
      out.println(e.toString()); 
   } 
%> 
   </h3> 
</body> 
</html> 