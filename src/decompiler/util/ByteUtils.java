package decompiler.util;

import java.nio.ByteBuffer;

public class ByteUtils {

	public static short byteToShort(byte [] arr){
		ByteBuffer buff = ByteBuffer.wrap(arr);
		return buff.getShort();
	}
	
	public static int byteToInt(byte [] arr){
		ByteBuffer buff = ByteBuffer.wrap(arr);
		return buff.getInt();
	}
	
	public static long byteToLong(byte [] arr){
		ByteBuffer buff = ByteBuffer.wrap(arr);
		return buff.getLong();
	}
}
