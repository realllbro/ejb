// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.action;

import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * LogoutAction.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="list.do" path="list.do" redirect="true"
 */
public class LogoutAction extends Action {

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
		Cookie[] c = request.getCookies();
		for(int i = 0; i < c.length; i++){
			String cname = c[i].getName();
			if(cname.equals("id")){
				if(c[i].getValue().equals("admin")){
					c[i].setMaxAge(0);
					c[i].setPath("/");
					response.addCookie(c[i]);
					break;
				}
			}
		} // for
		
		return mapping.findForward("list");
	}

}
