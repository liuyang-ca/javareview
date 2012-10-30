package ca.liu.struts.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.liu.struts.model.Account;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserLoginAction extends ActionSupport implements ModelDriven<Account>, Preparable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3173908866269886974L;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private String username;
	private String passwd;
	private Account account;
	
	final String LOGIN = "login";
	final String LOGOUT = "logout";

	
	
	public String checkLogin()
	{
		logger.debug("*****username={}, passwd={}", username, passwd);
		logger.debug("*****account.getUsername={}, account.getPasswd={}", account==null ? null : account.getUsername(), account==null ? null : account.getPasswd());

		if(this.username == null || !this.username.equalsIgnoreCase("admin"))
		{
			this.addFieldError("username", "UserName error");
			
			return ERROR;
		}
		else
		{
			if(this.passwd == null || !this.passwd.equalsIgnoreCase("123"))
			{
				this.addFieldError("passwd", "passwd error");
				return ERROR;
			}
		}		
		return LOGIN;
	}

	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}
	
	public Account getModel() {
		return this.account;
	}
	public String getPasswd() {
		return passwd;
	}

	public String getUsername() {
		return username;
	}

	public void prepare() throws Exception {
		account = new Account();
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String toHelloWorld()
	{
		return "HelloWorld";
	}

}
