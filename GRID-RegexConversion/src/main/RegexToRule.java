package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import models.RegexToRuleUtils;
import models.RegexUtils;
import models.Rule;
import models.RuleToRegexUtils;

public class RegexToRule {

	public static void main(String[] args) {
		
		System.err.println("Regex To Rule Converter\n");

		RegexToRuleUtils regexToRuleUtils = new RegexToRuleUtils();
		RuleToRegexUtils ruleToRegexUtils = new RuleToRegexUtils();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		
		try {
			while(!(line = br.readLine()).isEmpty() || line.length() > 0) {
				Rule rule = regexToRuleUtils.regexToRule(line);
				regexToRuleUtils.printRule(rule);
				
				String ruleToRegex = ruleToRegexUtils.ruleToRegex(rule);

				System.out.println("\n"+ruleToRegex);
				
	/*			if(regexFromRule.equals(line))
					System.err.println("\n\nOriginal Regex matches with the regex generated from rule");
*/				
//				regexUtils.HtmlToEntitiesInRules(regexUtils.RegexToRule(line));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

