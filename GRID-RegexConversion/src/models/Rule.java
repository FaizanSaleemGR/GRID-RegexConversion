package models;
import java.util.ArrayList;


public class Rule {
	ArrayList<RuleElement> ruleElements = new ArrayList<>();
	Integer sequenceNo = 0;

	public ArrayList<RuleElement> getRuleElements() {
		return ruleElements;
	}

	public void setRuleElements(ArrayList<RuleElement> ruleElements) {
		this.ruleElements = ruleElements;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
}