import java.util.ArrayList;

public class Regex {
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
		
		for (int i=0; i < prefixes.size(); i++)
		{
			if(i>0)
				regex += ".*?";
			
			regex += prefixes.get(i);
			
			if(i==prefixes.size()-1)
				regex += ".*?";
		}
		
		regex += "(" + token + ")";
		regex += "(.*?)";
		regex += "(" + postfix + ")";
		
		return regex;
	}
	
	
}