package decompiler.attribute;

public class BaseAttribute {
	short nameIndex;
	int length;
	
	public BaseAttribute(short nameIndex, int length){
		this.nameIndex = nameIndex;
		this.length =  length;
	}
	
	public BaseAttribute(){
		
	}
}
