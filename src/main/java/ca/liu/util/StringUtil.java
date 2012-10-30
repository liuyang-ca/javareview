package ca.liu.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum StringUtil {
	instance;
	
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class.getName());
	
	public String stripTags(String str) {
		if(str == null) {
			return null;
		}
		
		str = str.trim();
	    if (str.startsWith("<![CDATA[")) {
	      str = str.substring(9);
	      int i = str.indexOf("]]>");
	      if (i != str.length()-3 ) {
	        logger.error("Argument starts with <![CDATA[ but not ending with ]]>.");
	      } else {
	    	  str = str.substring(0, i);
	      }
	    }

		return str.replaceAll("\\<.*?>","");
	}
	
	public List<Integer> parseIds(String ids) {
		String[] strArray = ids.split("(\\s)*,(\\s)*");
		List<Integer> idList = new ArrayList<Integer>();
		for(String str : strArray) {
			idList.add(Integer.parseInt(str));
		}
		
		return idList;
	}
	
	public String trimIfNotNull(String string) {
		return string == null ? null : string.trim();
	}
	
	public boolean isNullOrEmpty(String string) {
		return string == null || string.trim().equals("");
	}
	
	public String trimAndNormalize(String string) {
		return string == null ? null : string.trim().toLowerCase().replace(" ", "_");
	}
}
