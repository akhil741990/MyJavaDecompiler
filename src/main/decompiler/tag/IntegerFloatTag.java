package decompiler.tag;

public class IntegerFloatTag extends BasicTag {
	int value;
	
	public IntegerFloatTag(){
		
	}
	public IntegerFloatTag(byte tag, int value){
		super(tag);
		this.value = value;
		
	}
	public String printTag(){
	
		return "Tag:"+this.tag+" Value:"+this.value;
	}
}
