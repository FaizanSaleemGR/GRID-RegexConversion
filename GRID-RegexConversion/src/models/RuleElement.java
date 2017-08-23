package models;
public class RuleElement {
	String startTag;
	String value;
	String endTag;
	Integer sequenceNo;
	
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
	
	public RuleElement(Integer sequenceNo, String tag, String value) {
		super();
		this.startTag = "<"+tag+">";
		this.value = value;
		this.endTag = "</"+tag+">";
		this.sequenceNo = sequenceNo;
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

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	
	
}