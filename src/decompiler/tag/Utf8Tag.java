package decompiler.tag;

import java.nio.charset.StandardCharsets;

public class Utf8Tag extends BasicTag {
	short length;
	byte [] bytes;
	public Utf8Tag(){
		
	}
	
	public Utf8Tag(byte tag,short length, byte bytes[]){
		super(tag);
		this.length = length;
		this.bytes = bytes;
	}
	public String printTag(){
		String str = new String(this.bytes,StandardCharsets.UTF_8);
		return "Tag:"+this.tag+" Length:"+this.length+" Bytes:"+str;
	}
}
