package decompiler;

import java.io.FileInputStream;
import java.io.IOException;

import decompiler.util.ReadFileBytes;
import decompiler.util.ByteUtils;

public class AttributeInfo {
	short nameIndex;
	int attributeLength;
	byte [] info;
	
	public AttributeInfo(short nameIndex, int attributeLength, byte [] info){
		this.nameIndex = nameIndex;
		this.attributeLength = attributeLength;
		this.info = info;
		
	}
	
	public static AttributeInfo readAttributes(FileInputStream f) throws IOException{
		byte [] byteArr = ReadFileBytes.readBytes(f, 2);
		short nameIndex = ByteUtils.byteToShort(byteArr);
		
		byteArr = ReadFileBytes.readBytes(f, 4);
		int attributeLength = ByteUtils.byteToInt(byteArr);
		
		byte [] info = ReadFileBytes.readBytes(f,attributeLength);
		
		
		return new AttributeInfo(nameIndex,attributeLength,info);
	}
}
