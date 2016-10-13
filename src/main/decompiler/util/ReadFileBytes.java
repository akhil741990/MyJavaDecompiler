package decompiler.util;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileBytes {
	private int offset ;
	public ReadFileBytes() {
		offset = 0;
	}
	public static byte []  readBytes(FileInputStream f,int length) throws IOException{
		byte [] bytes = new byte[length];
		int readLen = f.read(bytes, 0,length);
		//System.out.println("ReadLen:"+readLen);
		return bytes;
	}
}
