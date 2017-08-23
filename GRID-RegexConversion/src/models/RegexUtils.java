package models;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class RegexUtils {
	
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
	
	public RuleElement convertStringToRuleElement(String str) {
		RuleElement ruleElement = null;
		String tag="";
		Boolean tagFound = false;
		Iterator<String> tagIterator = tags.stream().iterator();
		
		
		do {
//			tag = tags.stream().findAny().get();
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
	
	public String replaceBrackets(String line) {
	
		return line.substring(1, line.length()-1);
	}
	
	public String RuleToRegex(Rule rule) {
		
		Regex regex = new Regex();
		
		for (RuleElement ruleElement : rule.getRuleElements()) {
			
			int startTagLength = ruleElement.getStartTag().length();
			String ruleElementValue = ruleElement.value.replace("&gt;", ">").replace("&lt;", "<");
			
			if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("prefix")) {
				regex.getPrefixes().add(ruleElementValue);
			}
			else if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("token")) {
				regex.setToken(ruleElementValue);
			}
			else if(ruleElement.getStartTag().substring(1, startTagLength-1).equals("postfix")) {
				regex.setPostfix(ruleElementValue);
			}
		}
		
		return regex.getRegex();
	}
	
	public Rule RegexToRule(String regex) {
		Rule rule = new Rule();
			
		String[] tokens = regex.split(Pattern.quote("(.*?)"));			

		String[] prefixTokens = tokens[0].split(Pattern.quote(".*?"));
		
		for(int i=0; i < prefixTokens.length; i++) {
			if(i == prefixTokens.length-1) {
				
//				rules.add("<token>"+replaceBrackets(prefixTokens[i])+"</token>");
				rule.getRuleElements().add(new RuleElement("token", replaceBrackets(prefixTokens[i])));
			}
			else
			{
//				rules.add("<prefix>"+prefixTokens[i]+"</prefix>");
				rule.getRuleElements().add(new RuleElement("prefix", prefixTokens[i]));
			}
		}
		
//		rules.add("<postfix>"+replaceBrackets(tokens[1])+"</postfix>");
		rule.getRuleElements().add(new RuleElement("postfix", replaceBrackets(tokens[1])));
		
		return rule;
	}
	
	public String checkTag(String tag) {
		return tags.stream().filter(x->x.equals(tag)).findFirst().get();
	}
	
	
	public Rule HtmlToEntitiesInRules(Rule rule) {
//		ArrayList<String> updatedRules = new ArrayList<>();
//		Rule updatedRule = new Rule();
		
		
			for (RuleElement ruleElement : rule.getRuleElements()) {
				String tag = checkTag(ruleElement.getStartTag().substring(1,ruleElement.getStartTag().length()-1));
				String startTag = "<"+tag+">";
				String endTag = "</"+tag+">";
				
				ruleElement.setValue(ruleElement.getValue().replace(">", "&gt;").replace("<", "&lt;").trim());
			}

//			return updatedRule;
			return rule;
	}
	
	
	public void printRule(Rule rule) {
		
		for(RuleElement ruleElement : rule.getRuleElements()) {
			System.out.println(ruleElement.getRuleElement());
		}
	}
	
}