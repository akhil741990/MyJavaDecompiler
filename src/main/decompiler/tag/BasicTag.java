package decompiler.tag;

public class BasicTag {
	byte tag;
	
	
	public BasicTag(){
		
	}
	public BasicTag(byte tag) {
		this.tag = tag;
	
	}
	
	public String printTag(){
		return "Tag:"+tag;
	}
}
