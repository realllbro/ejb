// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanbitbook.ejb.sguestbook.form.ListForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import kr.co.hanbitbook.ejb.guestbook.*;
import java.util.*;
import javax.naming.*;
import javax.rmi.*;

/** 
 * ListAction.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:action path="/list" name="listForm" input="/list.do" validate="true"
 * @struts:action-forward name="list.jsp" path="list.jsp"
 */
public class ListAction extends Action {

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
		ListForm listForm = (ListForm) form;
		request.setAttribute("spage", listForm.getPage());
		Context ctx = null;
		ArrayList list = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("GuestbookBean");
			GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
			Guestbook guestbook = home.create();
			list = guestbook.getGuestbookBean();
		}catch(Exception ex){
			System.out.println(ex);		
		}
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}

}
