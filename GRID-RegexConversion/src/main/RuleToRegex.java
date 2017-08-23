package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import models.RegexUtils;
import models.Rule;
import models.RuleToRegexUtils;

public class RuleToRegex {

	public static void main(String[] args) {

		System.err.println("Rule To Regex Converter\n");
		
		RegexUtils regexUtils = new RegexUtils();
		RuleToRegexUtils ruleToRegexUtils = new RuleToRegexUtils();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		Rule rule = new Rule();
		
		try {
			while(!(line = br.readLine()).isEmpty()) {
				rule.getRuleElements().add(ruleToRegexUtils.convertStringToRuleElement(line.trim()));
			}

			String ruleToRegex = ruleToRegexUtils.ruleToRegex(rule);

			System.out.println("\n"+ruleToRegex);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
