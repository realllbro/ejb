// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package kr.co.hanbitbook.ejb.sguestbook.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * WriteForm.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 03-09-2004
 * 
 * XDoclet definition:
 * @struts:form name="writeForm"
 */
public class WriteForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** content property */
	private String content;

	/** name property */
	private String name;

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
		if(getName().equals(""))
			errors.add("name", new ActionError("이름이 입력되지 않았습니다."));
		if(getContent().equals(""))
			errors.add("content", new ActionError("내용이 입력되지 않았습니다."));			
		return errors;
	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		content = "";
		name = "";

	}

	/** 
	 * Returns the content.
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/** 
	 * Set the content.
	 * @param content The content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
