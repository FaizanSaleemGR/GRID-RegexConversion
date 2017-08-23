package bkup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


class Regex {
	ArrayList<String> prefixes = new ArrayList<>();
	String token = "";
	String postfix = "";
	
	public ArrayList<String> getPrefixes() {
		return prefixes;
	}
	public void setPrefixes(ArrayList<String> prefixes) {
		this.prefixes = prefixes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
	
	public String getRegex() {
		String regex = "";
		
		for (String prefix : prefixes)
		{
			regex += ".*?" + prefix;
		}
		
		regex += "(" + token + ")";
		regex += "(.*?)";
		regex += "(" + postfix + ")";
		
		return regex;
	}
	
	
}

class RuleElement {
	String startTag;
	String value;
	String endTag;
	
	public RuleElement(String startTag, String value, String endTag) {
		super();
		this.startTag = "<"+startTag+">";
		this.value = value;
		this.endTag = "</"+endTag+">";
	}

	public RuleElement(String tag, String value) {
		super();
		this.startTag = "<"+tag+">";
		this.value = value;
		this.endTag = "</"+tag+">";
	}
	
	public String getStartTag() {
		return startTag;
	}
	public void setStartTag(String startTag) {
		this.startTag = "<"+startTag+">";
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEndTag() {
		return endTag;
	}
	public void setEndTag(String endTag) {
		this.endTag = "</"+endTag+">";
	}
	
	public String getRuleElement() {
		return startTag+value+endTag;
	}
	
	
}

class Rule {
	ArrayList<RuleElement> ruleElements = new ArrayList<>();

	public ArrayList<RuleElement> getRuleElements() {
		return ruleElements;
	}

	public void setRuleElements(ArrayList<RuleElement> ruleElements) {
		this.ruleElements = ruleElements;
	}
}

class RegexUtils {
	
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

public class bkup_AngleBracketConversion {

	public static void main(String[] args) {
		
		RegexUtils regexUtils = new RegexUtils();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		
		try {
			while(!(line = br.readLine()).isEmpty() || line.length() > 0) {
				Rule rule = regexUtils.HtmlToEntitiesInRules(regexUtils.RegexToRule(line));
				regexUtils.printRule(rule);
				
				
//				System.out.println(regexUtils.RuleToRegex(rule));
				
//				regexUtils.HtmlToEntitiesInRules(regexUtils.RegexToRule(line));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
