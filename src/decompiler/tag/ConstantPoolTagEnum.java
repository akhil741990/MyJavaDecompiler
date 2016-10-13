package decompiler.tag;

public enum ConstantPoolTagEnum {
	UTF8((byte)1),
	INTEGER((byte)3),
	FLOAT((byte)4),
	LONG((byte)5),
	DOUBLE((byte)6),
	CLASS((byte)7),
	STRING((byte)8),
	FILED_REF((byte)9),
	METHOD_REF((byte)10),
	INTERFACE_METHOD_REF((byte)11),
	NAME_AND_TYPE((byte)12)
	;
	
	
	private final byte value;
	ConstantPoolTagEnum(byte value){
		this.value = value;
	}
	public byte getValue(){
		return this.value;
	}
	 
}
