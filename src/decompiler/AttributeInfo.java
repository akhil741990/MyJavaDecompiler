package decompiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import decompiler.util.ReadFileBytes;
import decompiler.attribute.CodeAttribute;
import decompiler.tag.BasicTag;
import decompiler.tag.Utf8Tag;
import decompiler.util.ByteUtils;

public class AttributeInfo {
	short nameIndex;
	int attributeLength;
	CodeAttribute cAttr;
	
	public AttributeInfo(short nameIndex, int attributeLength, CodeAttribute cAttr){
		this.nameIndex = nameIndex;
		this.attributeLength = attributeLength;
		this.cAttr = cAttr;
		
	}
	
	public static AttributeInfo readAttributes(FileInputStream f, Map<Integer,BasicTag> pool) throws IOException{
		byte [] byteArr = ReadFileBytes.readBytes(f, 2);
		short nameIndex = ByteUtils.byteToShort(byteArr);
		
		BasicTag t = pool.get((int)nameIndex);
				
		byteArr = ReadFileBytes.readBytes(f, 4);
		int attributeLength = ByteUtils.byteToInt(byteArr);
		
		AttributeInfo aInfo = null;
		Utf8Tag uutf8Tag;
		if(t instanceof Utf8Tag){
			uutf8Tag = (Utf8Tag)t;
		
			System.out.println("utf8                   00000000000000000000              ");
			String attribName =new String(uutf8Tag.bytes,StandardCharsets.UTF_8);
			if("Code".equals(attribName)){
				System.out.println("Code                   00000000000000000000              ");
				CodeAttribute cAttr = CodeAttribute.readCodeAttribute(f);
				aInfo = new AttributeInfo(nameIndex,attributeLength,cAttr);
			}
		}
		return aInfo;
	}
}
