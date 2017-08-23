import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class RuleToRegex {

	public static void main(String[] args) {

		System.err.println("Rule To Regex Converter\n");
		
		RegexUtils regexUtils = new RegexUtils();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		Rule rule = new Rule();
		
		try {
			while((line = br.readLine()).length() > 0 ) {
				rule.getRuleElements().add(regexUtils.convertStringToRuleElement(line.trim()));
			}

			String regexFromRule = regexUtils.RuleToRegex(rule);
			System.out.println(regexFromRule);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
