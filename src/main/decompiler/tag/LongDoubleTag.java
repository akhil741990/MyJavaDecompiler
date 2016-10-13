package decompiler.tag;

public class LongDoubleTag extends BasicTag {
	long value;
	public LongDoubleTag(){
		
	}
	
	public LongDoubleTag(byte tag,long value){
		super(tag);
		this.value = value;
		
	}
	
	public String printTag(){
		
		return "Tag:"+this.tag+" Value:"+this.value;
	}
}
