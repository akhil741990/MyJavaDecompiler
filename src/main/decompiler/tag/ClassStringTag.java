package decompiler.tag;

public class ClassStringTag extends BasicTag {
	public short index;
	
	public ClassStringTag(){
		
	}
	public ClassStringTag(byte tag, short index){
		super(tag);
		this.index = index;
		
	}
	public String printTag(){
		return "Tag:"+this.tag+" Index:"+this.index;
	}
}
