package decompiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import decompiler.tag.BasicTag;
import decompiler.util.ByteUtils;
import decompiler.util.ReadFileBytes;

public class MethodInfo {
	short nameIndex,descriptorIndex,attributeCount;
	byte []accessFlag;
	AttributeInfo attributeArray[];
	public MethodInfo(byte [] accessFlag,short nameIndex,short descriptorIndex,short attributeCount){
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributeCount = attributeCount;
		attributeArray = new AttributeInfo[attributeCount];
	}

	
	public static MethodInfo readField(FileInputStream f,Map<Integer,BasicTag> pool) throws IOException{
		byte [] accessFlag = ReadFileBytes.readBytes(f, 2);
		byte [] byteArr = ReadFileBytes.readBytes(f, 2);
		short nameIndex  = ByteUtils.byteToShort(byteArr);
		byteArr = ReadFileBytes.readBytes(f, 2);
		short descriptorIndex  = ByteUtils.byteToShort(byteArr);
		byteArr = ReadFileBytes.readBytes(f, 2);
		short attributeCount  = ByteUtils.byteToShort(byteArr);
		
		
		if(ByteUtils.byteToShort(accessFlag) == 0){
			return null;
		}
		
		MethodInfo field = new MethodInfo(accessFlag, nameIndex, descriptorIndex, attributeCount);
		for (int i = 0; i < attributeCount;i++){
			AttributeInfo attr = AttributeInfo.readAttributes(f,pool);
			field.attributeArray[i] = attr;
		}
		return field;
		
		
		
	}
}
