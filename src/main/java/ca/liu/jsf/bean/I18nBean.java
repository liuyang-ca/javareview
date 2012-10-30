package ca.liu.jsf.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ManagedBean
@SessionScoped
public class I18nBean implements Serializable {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;
	private String localeCode;
	private static Map<String, Locale> countires;
	
	static {
		countires = new HashMap<String, Locale>();
		countires.put("English", Locale.ENGLISH);
		countires.put("Chinese", Locale.SIMPLIFIED_CHINESE);
	}

	public I18nBean() {
		localeCode = "English";
	}
	
	
	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public Set<String> getCountriesKeySet() {
		return countires.keySet();
	}
	
	public Map<String, Locale> getCountires() {
		return countires;
	}
	
	/**
	 * This lintener doesn't work
	 * @param e
	 */
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		String newValue = e.getNewValue().toString();
		Locale local = (Locale)countires.get(newValue);
		logger.debug("new Local is {} with code {}.", local, newValue);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(local);
	}
}