package decompiler.tag;

public class RefTag extends BasicTag {
	public short index, typeIndex;
	
	public RefTag(){
		
	}
	
	public RefTag(byte tag, short index, short typeIndex){
		super(tag);
		this.index = index;
		this.typeIndex = typeIndex;
		
	}
	
	public String printTag(){
		return "Tag:"+this.tag+" Index:"+this.index+ " TypeIndex:"+this.typeIndex;
	}
}
