package decompiler.tag;
import decompiler.util.ReadFileBytes;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import decompiler.util.ByteUtils;

public class TagProcessor {

	public Map<Integer,BasicTag> constantPool;
	int counter;
	public TagProcessor(){
		constantPool = new HashMap<Integer,BasicTag>();
		counter = 1;
	}
	
	public void processTag(byte tag, FileInputStream f) throws IOException{
		byte [] byteArray = null;
		byte [] data = null;
		short index,typeIndex;
		switch(tag){
			case (byte)1:
				
				//System.out.println("Processing UTF8 Tag");
				byteArray = ReadFileBytes.readBytes(f, 2);
				short len = ByteUtils.byteToShort(byteArray);
				data = ReadFileBytes.readBytes(f, len);
				constantPool.put(counter, new Utf8Tag(tag, len,data));
				counter++;
				break;
			case (byte)3:
			case (byte)4:
				  //System.out.println("Processing Integer/FloatTag");
			      byteArray = ReadFileBytes.readBytes(f, 4);
			      int value = ByteUtils.byteToInt(byteArray);
			      constantPool.put(counter, new IntegerFloatTag(tag,value));
				  counter++;
			case (byte)5:
			case (byte)6:
				
				  //System.out.println("Processing Long/Double");
			      byteArray = ReadFileBytes.readBytes(f, 8);
			      long longValue = ByteUtils.byteToLong(byteArray);
			      constantPool.put(counter, new LongDoubleTag(tag,longValue));
				  counter++;
				  break;
			case (byte)7:
			case (byte)8:	
				  //System.out.println("Processing Class/String");
			      byteArray = ReadFileBytes.readBytes(f, 2);
			      index = ByteUtils.byteToShort(byteArray);
			      constantPool.put(counter, new ClassStringTag(tag,index));
				  counter++;
				  break;
			case (byte)9: 
			case (byte)10:
			case (byte)11:
			case (byte)12:	
				  //System.out.println("Processing Ref");
			      byteArray = ReadFileBytes.readBytes(f, 2);
			      index = ByteUtils.byteToShort(byteArray);
			      byteArray = ReadFileBytes.readBytes(f, 2);
			      typeIndex = ByteUtils.byteToShort(byteArray);
			      constantPool.put(counter, new RefTag(tag,index,typeIndex));
				  counter++;
				  break;
		}
	}
	public void printConstantPool()
	{
		for(int i = 1; i < this.counter ;i ++){
			BasicTag tag = constantPool.get(i);
			System.out.println("Index: "+i+" "+tag.printTag());
		}
	}
	public BasicTag get(int index){
		return constantPool.get(index);
	}
}
