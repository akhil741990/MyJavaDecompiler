package decompiler;

import java.io.FileInputStream;
import java.io.IOException;

import decompiler.util.ByteUtils;
import decompiler.util.ReadFileBytes;

public class FieldInfo {

	short nameIndex,descriptorIndex,attributeCount;
	byte []accessFlag;
	AttributeInfo attributeArray[];
	public FieldInfo(byte [] accessFlag,short nameIndex,short descriptorIndex,short attributeCount){
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributeCount = attributeCount;
		attributeArray = new AttributeInfo[attributeCount];
	}

	
	public static FieldInfo readField(FileInputStream f) throws IOException{
		byte [] accessFlag = ReadFileBytes.readBytes(f, 2);
		byte [] byteArr = ReadFileBytes.readBytes(f, 2);
		short nameIndex  = ByteUtils.byteToShort(byteArr);
		byteArr = ReadFileBytes.readBytes(f, 2);
		short descriptorIndex  = ByteUtils.byteToShort(byteArr);
		byteArr = ReadFileBytes.readBytes(f, 2);
		short attributeCount  = ByteUtils.byteToShort(byteArr);
		FieldInfo field = new FieldInfo(accessFlag, nameIndex, descriptorIndex, attributeCount);
		for (int i = 0; i < attributeCount;i++){
			AttributeInfo attr = AttributeInfo.readAttributes(f);
			field.attributeArray[i] = attr;
		}
		return field;
		
		
		
	}
}
