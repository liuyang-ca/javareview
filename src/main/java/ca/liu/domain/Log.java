package ca.liu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="st_log")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@BatchSize(size=10)
public class Log {
	private int id;
	private User user;
	private String value;
	
	public Log() {
		
	}
	
	public Log(User user, String value) {
		this.setUser(user);
		this.setValue(value);
	}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
    @ManyToOne()
    @JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append("{id=").append(this.getId())
		  .append(", value=").append(this.getValue())
		  .append(", userId=").append(this.getUser() == null ? "null" : this.getUser().getId())
		  .append("}");
		return sb.toString();
	}
}
