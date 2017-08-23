package models;

import java.util.Collections;
import java.util.regex.Pattern;

public class RegexToRuleUtils {
	
	RegexUtils regexUtils = new RegexUtils();
	
	/*
	 * Function remove outermost round brackets of token and postfix.
	 */
	public String replaceBrackets(String line) {
	
		return line.substring(1, line.length()-1);
	}

	/*
	 * Function to convert given regex in string to Rule format.
	 */
	public Rule regexToRule(String regex) {
		Rule rule = new Rule();
		Integer sequenceNo;
		String[] tokens = regex.split(Pattern.quote("(.*?)"));			

		String[] prefixTokens = tokens[0].split(Pattern.quote(".*?"));
		
		for(int i=0; i < prefixTokens.length; i++) {
			if(i == prefixTokens.length-1) {
				rule.getRuleElements().add(new RuleElement(0, "token", replaceBrackets(prefixTokens[i])));
//				rule.setSequenceNo(1);
			}
			else
			{
				sequenceNo = rule.getSequenceNo();
				rule.getRuleElements().add(new RuleElement(++sequenceNo, "prefix", prefixTokens[i]));
				rule.setSequenceNo(sequenceNo);
			}
		}
		
		
		tokens[1] = replaceBrackets(tokens[1]);
		String[] postfixTokens = tokens[1].split(Pattern.quote("|"));
		
		for(int i=0; i < postfixTokens.length; i++) {
			sequenceNo = rule.getSequenceNo();
			rule.getRuleElements().add(new RuleElement(++sequenceNo, "postfix", postfixTokens[i]));
			rule.setSequenceNo(sequenceNo);
		}
		
		
		// Sorting rule elements by sequence number
		Collections.sort(rule.ruleElements, (r1, r2) -> r1.getSequenceNo().compareTo(r2.getSequenceNo()));
		
		rule = regexUtils.htmlToEntities(rule);
		
		return rule;
	}
	

	
	/*
	 * Function to print rule in the *EXACT* following format:
	 * 		<startTag>Value<endTag>
	 */
	public void printRule(Rule rule) {
		
		for(RuleElement ruleElement : rule.getRuleElements()) {
			System.out.println(ruleElement.getRuleElement());
		}
	}

}
