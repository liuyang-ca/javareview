package ca.liu.jsf.listener;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.liu.jsf.bean.I18nBean;

public class LanguageChangeListener implements ValueChangeListener{
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public void processValueChange(ValueChangeEvent e) throws AbortProcessingException {
		//access country bean directly
		I18nBean bean = (I18nBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("i18nBean");
		String newValue = e.getNewValue().toString();
		Locale local = bean.getCountires().get(newValue);
		logger.debug("new Local is {} with code {}.", local, newValue);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(local);
	}
}
