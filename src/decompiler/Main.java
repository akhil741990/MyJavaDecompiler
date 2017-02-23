package decompiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import decompiler.tag.BasicTag;
import decompiler.tag.ClassStringTag;
import decompiler.tag.TagProcessor;
import decompiler.util.ByteUtils;

public class Main {

	public static void main(String args[]){
		System.out.println("Starting decompiler");
		try{
		
			ReadFileBytes fileReader = new  ReadFileBytes();
			FileInputStream f = new FileInputStream("D:\\Java\\JavaProblems\\src\\decompiler\\Test.class");
			byte[] bytes = fileReader.readBytes(f,4);
			System.out.println(String.format("MagicNumber "+"%02x%02x%02x%02x", bytes[0],bytes[1],bytes[2],bytes[3]));
			byte[] version = fileReader.readBytes(f, 4);
			System.out.println(String.format("Version "+"%02x%02x%02x%02x", version[0],version[1], version[2],version[3]));
		    byte[] constantpoolCount = fileReader.readBytes(f,2);
			//System.out.println(String.format("ConstantPoolCount"+"%02x%02x",constantpoolCount[0],constantpoolCount[1]));
			
			int cPoolCount;
			ByteBuffer buff = ByteBuffer.wrap(constantpoolCount);
			cPoolCount = buff.getShort();
			System.out.println("PoolCount:"+cPoolCount);
			byte [] tag ;
			
			TagProcessor tagProc = new TagProcessor();
			//System.out.println("Index:"+index[0]);
			for (int i = 0;i < cPoolCount-1;i++ ){
				tag = fileReader.readBytes(f, 1);
				//System.out.println("Tag:"+tag[0]);
				tagProc.processTag(tag[0], f);
				
			}
			tagProc.printConstantPool();
			
			short thisClassIndex;
			byte [] byteArr = fileReader.readBytes(f, 2);
			
			System.out.println(String.format("AccessFlag"+"%02x%02x",byteArr[0],byteArr[1]));
			
			byteArr = fileReader.readBytes(f, 2);
			thisClassIndex = ByteUtils.byteToShort(byteArr);
			System.out.println("thisClassIndex:"+thisClassIndex);
			//tagProc.printConstantPool();
			BasicTag t = tagProc.get((int)thisClassIndex);
		
			t = tagProc.get(((ClassStringTag)t).index);
			System.out.println("ThisClass:"+t.printTag());
			
			
			
			byteArr = fileReader.readBytes(f, 2);
			thisClassIndex = ByteUtils.byteToShort(byteArr);
			System.out.println("SuperClassIndex:"+thisClassIndex);
			//tagProc.printConstantPool();
			t = tagProc.get((int)thisClassIndex);
		
			t = tagProc.get(((ClassStringTag)t).index);
			System.out.println("SuperClass:"+t.printTag());
			
			
			byteArr = fileReader.readBytes(f, 2);
			short interfaceCount = ByteUtils.byteToShort(byteArr);
			System.out.println("Interface Count:"+interfaceCount);
			
			
			byteArr = fileReader.readBytes(f, 2);
			short fieldCount = ByteUtils.byteToShort(byteArr);
			System.out.println("Field Count:"+fieldCount);
			
			FieldInfo field;
			for(int i = 0;i < fieldCount;i++){
				field = FieldInfo.readField(f,tagProc.constantPool);
				System.out.println("Printing Fields");
				System.out.println(String.format("AccessFlag"+"%02x%02x",field.accessFlag[0],field.accessFlag[1]));
				t = tagProc.get((int)field.nameIndex);
				System.out.println("FieldName: "+t.printTag());
				t = tagProc.get((int)field.descriptorIndex);
				System.out.println("Descriptor: "+t.printTag());
				for(int j = 0 ;j < field.attributeCount;j++){
					System.out.println("Attributes");
					t = tagProc.get((int)field.attributeArray[j].nameIndex);
					System.out.println("Name: "+t.printTag());
					
				}
			}
			
			byteArr = fileReader.readBytes(f, 2);
			short methodCount = ByteUtils.byteToShort(byteArr);
			System.out.println("Method Count:"+methodCount);
			
			MethodInfo method;
			for(int i = 0 ; i < methodCount ;i++){
				method = MethodInfo.readField(f,tagProc.constantPool);
				System.out.println("Printing Methods");
				System.out.println(String.format("AccessFlag"+"%02x%02x",method.accessFlag[0],method.accessFlag[1]));
				t = tagProc.get((int)method.nameIndex);
				
				
				// Access flag should never be 0 need to find the root cause of this.
				// 
				
				if(t!=null){
					System.out.println("Name: "+t.printTag());
				}
				t = tagProc.get((int)method.descriptorIndex);
				
				
				System.out.println("Attributes Count:"+method.attributeCount);
				
				if(t!= null){
					System.out.println("Descriptor: "+t.printTag());
				}
				for(int j = 0 ;j < method.attributeCount;j++){
					System.out.println("Attributes");
					t = tagProc.get((int)method.attributeArray[j].nameIndex);
					System.out.println("Name: "+t.printTag());
					System.out.println("Length: "+method.attributeArray[j].attributeLength);
					method.attributeArray[j].cAttr.print(tagProc.constantPool);
				}
			}
			
			int b;
			while( (b = f.read())!= -1){
				//System.out.println(Integer.toHexString(b));
			}
			f.close();
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	
	public static short byteToShort(byte [] arr){
		ByteBuffer buff = ByteBuffer.wrap(arr);
		return buff.getShort();
	}
}



