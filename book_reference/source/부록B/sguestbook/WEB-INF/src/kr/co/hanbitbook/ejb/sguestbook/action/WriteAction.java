// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanbitbook.ejb.sguestbook.form.WriteForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import kr.co.hanbitbook.ejb.guestbook.*;
import javax.naming.*;
import javax.rmi.*;

/** 
 * WriteAction.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:action path="/write" name="writeForm" input="/writeform.do" validate="true"
 * @struts:action-forward name="list.do" path="list.do" redirect="true"
 */
public class WriteAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		WriteForm writeForm = (WriteForm) form;
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("GuestbookBean");
			GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
			Guestbook guestbook = home.create();
			GuestbookDataBean gdb = new GuestbookDataBean();
			gdb.setName(writeForm.getName());
			gdb.setContent(writeForm.getContent());
			guestbook.addGuestbookBean(gdb);
		}catch(Exception ex){
			System.out.println(ex);		
		}		
		return mapping.findForward("list");
	}

}
