package models;

import java.util.ArrayList;
import java.util.Iterator;

public class RuleToRegexUtils {

	RegexUtils regexUtils = new RegexUtils();
	
	/*
	 * Function to convert given string to rule format needed for Rule-To-Regex conversion
	 */
	public RuleElement convertStringToRuleElement(String str) {
		RuleElement ruleElement = null;
		String tag="";
		Boolean tagFound = false;
		Iterator<String> tagIterator = regexUtils.getTags().stream().iterator();
		
		
		do {
			tag = tagIterator.next();
			String startTag = "<"+tag+">";
			String endTag = "</"+tag+">";
		
			if(str.contains(tag)) {
				tagFound = true;
				str = str.replace(startTag, "").replace(endTag, "");
				ruleElement = new RuleElement(tag, str);
			}
		}while(!tagFound);
		
		return ruleElement;
	}
	
	
	/*
	 * Function to convert given Rule to regex string.
	 */
	public String ruleToRegex(Rule rule) {
		
		Regex regex = new Regex();
		
		for (RuleElement ruleElement : rule.getRuleElements()) {
			
			int startTagLength = ruleElement.getStartTag().length();
			String ruleElementValue = regexUtils.entitiesToHtml(ruleElement.getValue());
			
			if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("prefix")) {
				regex.getPrefixes().add(ruleElementValue);
			}
			else if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("token")) {
				regex.setToken(ruleElementValue);
			}
			else if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("postfix")) {
				regex.getPostfixes().add(ruleElementValue);
			}
		}
		
		return regex.getRegex();
	}
	
	
}
