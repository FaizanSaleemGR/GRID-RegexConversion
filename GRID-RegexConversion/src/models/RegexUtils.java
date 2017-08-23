package models;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class RegexUtils {
	
	/*
	 * Tags we support in a rule.
	 */
	ArrayList<String> tags = new ArrayList<String>() {{ 
		add("token");
		add("prefix");
		add("postfix");
		add("key_mapping");
		add("value_type");
		add("occurance");
		add("name_seperator");
		add("name_sequence");
		add("date_format");
		add("booking_status");
		add("postfix included=\"yes\"");
	}};
	
	
	/*
	 * Function to check if a tag provided by user is listed in the tags we support.
	 */
	public String checkTag(String tag) {
		return tags.stream().filter(x->x.equals(tag)).findFirst().get();
	}
	
	/*
	 * Function to perform following conversions:
	 * 		< to &lt;
	 *		> to &gt;
	 */
	public Rule htmlToEntities(Rule rule) {
			for (RuleElement ruleElement : rule.getRuleElements()) {
				String tag = this.checkTag(ruleElement.getStartTag().substring(1,ruleElement.getStartTag().length()-1));
				String startTag = "<"+tag+">";
				String endTag = "</"+tag+">";
				
				ruleElement.setValue(ruleElement.getValue().replace(">", "&gt;").replace("<", "&lt;").trim());
			}

			return rule;
	}
	
	public String htmlToEntities(String str) {
		return	str.replace(">", "&gt;").replace("<", "&lt;").trim();
	}

	
	
	public String entitiesToHtml(String str) {
		return	str.replace("&gt;", ">").replace("&lt;", "<").trim();
	}
	

	public ArrayList<String> getTags() {
		return tags;
	}


	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	
}