// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.action;

import javax.servlet.http.*;
import kr.co.hanbitbook.ejb.sguestbook.form.LoginForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * LoginAction.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:action path="/login" name="loginForm" input="/loginform.do" validate="true"
 * @struts:action-forward name="list.do" path="list.do" redirect="true"
 */
public class LoginAction extends Action {

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
		LoginForm loginForm = (LoginForm) form;
		if(loginForm.getId().equals("admin") && loginForm.getPasswd().equals("12345")){
			Cookie userc = new Cookie("id", "admin");
			userc.setPath("/");
			response.addCookie(userc);				
		}
		return mapping.findForward("list");
	}

}
