package models;
import java.util.ArrayList;


public class Rule {
	ArrayList<RuleElement> ruleElements = new ArrayList<>();

	public ArrayList<RuleElement> getRuleElements() {
		return ruleElements;
	}

	public void setRuleElements(ArrayList<RuleElement> ruleElements) {
		this.ruleElements = ruleElements;
	}
}