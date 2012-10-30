package ca.liu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="st_user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1506000594262988591L;
	@XmlAttribute
	private int id;
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	@XmlTransient
	private List<Log> logs;
	
	public User(){
		logs = new ArrayList<Log>();
	}
	
	public User(String username, String password) {
		this();
		this.setUsername(username);
		this.setPassword(password);
	}
	
	@Id //@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@OneToMany(mappedBy = "user")//, fetch = FetchType.EAGER)
	public List<Log> getLogs() {
		return logs;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append("{id=").append(this.getId())
		  .append(", username=").append(this.getUsername())
		  .append(", password=").append(this.getPassword())
		  //.append(", logsSize=").append(this.getLogs().size())
		  .append("}");
		return sb.toString();
	}
}
