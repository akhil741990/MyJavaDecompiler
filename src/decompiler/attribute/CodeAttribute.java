package decompiler.attribute;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import decompiler.ExceptionInfo;
import decompiler.tag.BasicTag;
import decompiler.tag.ClassStringTag;
import decompiler.tag.RefTag;
import decompiler.util.ByteUtils;
import decompiler.util.ReadFileBytes;

public class CodeAttribute extends BaseAttribute{
	short maxStack,maxLocals,expTableLength, attrCount;
	int codeLength;
	byte[] code;
	ExceptionInfo [] exp;
	
	public CodeAttribute(short nameIndex, int length, short maxStack, short maxLocals,
			int codelenght,byte []code, short expTableLength, short attrCount){
		super(nameIndex,length);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLength = codelenght;
		this.code = code;
		this.expTableLength = expTableLength;
		this.attrCount = attrCount;
		exp = new ExceptionInfo[expTableLength];
	}
	
	
	public static CodeAttribute readCodeAttribute(FileInputStream f) throws IOException{
		int counter = 0;
		short nameIndex,maxStack,maxLocals,expTableLength, attrCount;
		int codeLength,attLen;
		byte[] code;
		ExceptionInfo [] exp;
		
		byte[] byteArray;
		
		byteArray = ReadFileBytes.readBytes(f, 2);
		nameIndex = ByteUtils.byteToShort(byteArray);
		
		byteArray = ReadFileBytes.readBytes(f, 4);
		attLen = ByteUtils.byteToInt(byteArray);
		
		byteArray = ReadFileBytes.readBytes(f, 2);
		maxStack = ByteUtils.byteToShort(byteArray);
		counter = counter + 2; 
		byteArray = ReadFileBytes.readBytes(f, 2);
		maxLocals = ByteUtils.byteToShort(byteArray);
		
		counter = counter + 2;
		byteArray = ReadFileBytes.readBytes(f, 4);
		codeLength = ByteUtils.byteToInt(byteArray);
		
		counter = counter + 4;
		code = ReadFileBytes.readBytes(f, codeLength);
		
		
		counter = counter + codeLength;
		byteArray = ReadFileBytes.readBytes(f, 2);
		expTableLength =  ByteUtils.byteToShort(byteArray);
	
		counter = counter + 2;
		CodeAttribute c = new CodeAttribute(nameIndex, attLen, maxStack, maxLocals, codeLength, code, expTableLength,(short)0);
		
		short startPc, endPc, handlerPc, catchType;
		for(int i = 0 ;i < expTableLength; i++){
			byteArray = ReadFileBytes.readBytes(f, 2);
			startPc = ByteUtils.byteToShort(byteArray);
			counter = counter + 2;
			
			byteArray = ReadFileBytes.readBytes(f, 2);
			endPc = ByteUtils.byteToShort(byteArray);
			counter = counter + 2;
			byteArray = ReadFileBytes.readBytes(f, 2);
			handlerPc = ByteUtils.byteToShort(byteArray);
			counter = counter + 2;
			byteArray = ReadFileBytes.readBytes(f, 2);
			catchType = ByteUtils.byteToShort(byteArray);
			counter = counter + 2;
			c.exp[i] =  new ExceptionInfo(startPc, endPc, handlerPc, catchType);
		}
		
		byteArray = ReadFileBytes.readBytes(f, 2);
		attrCount = ByteUtils.byteToShort(byteArray);
		
		counter = counter + 2;
		c.attrCount = attrCount;
		
		System.out.println("@@@@@@@@@@@@@@@@@@@ Print Counter:"+counter);
		return c;
	}
	
	public void print(Map<Integer,BasicTag> pool){
		System.out.println("Printing CodeAttribute");
		BasicTag t = pool.get((int)(this.nameIndex));
		RefTag refTag = (RefTag)t;
		
		t = pool.get((int)refTag.index);
		
		ClassStringTag classIndex = (ClassStringTag) t;
		
		t = pool.get((int)classIndex.index); 
		
		System.out.println("ClassName:"+t.printTag());
		
		t = pool.get((int)refTag.typeIndex);
		
		RefTag nameTypeIndex = (RefTag)t;
		
		t = pool.get((int)nameTypeIndex.index);
		System.out.println("Name:"+t.printTag());
		
		t = pool.get((int)nameTypeIndex.typeIndex);
		System.out.println("Descriptor:"+t.printTag());

		
		System.out.println("AttrLen:"+this.attrCount);
		System.out.println("MaxStack:"+this.maxStack);
		System.out.println("MaxLocals:"+this.maxLocals);
		
		System.out.println("Printing Byte Code");
		for(int i =0;i<this.codeLength;i++){
			//System.out.println(String.format("%02x",this.code[i]));
		}
		
		System.out.println("ExpTableLength:"+this.expTableLength);
		
		System.out.println("Printing expTable");
		
		for(int i = 0 ;i < this.expTableLength;i++){
			this.exp[i].print();
		}
		
		System.out.println("AtttributeCount:"+this.attrCount);
		
	}
	
}
