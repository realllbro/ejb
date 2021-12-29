// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * ListForm.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:form name="listForm"
 */
public class ListForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** page property */
	private String page;

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if(getPage() == null)
			setPage("1");
		try{
			Integer.parseInt(getPage());
		}catch(Exception ex){
			setPage("1");
		}
		return errors;
	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		page = "";

	}

	/** 
	 * Returns the page.
	 * @return String
	 */
	public String getPage() {
		return page;
	}

	/** 
	 * Set the page.
	 * @param page The page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

}
