package decompiler;

public class ExceptionInfo {
	short startPc, endPc, handlerPc, catchType;
	
	public ExceptionInfo(short startPc, short endPc, short handlerPc, short catchType){
		this.startPc = startPc;
		this.endPc = endPc;
		this.handlerPc = handlerPc;
		this.catchType = catchType;
	}
	
	public  void print(){
		System.out.println("StartPc:"+this.startPc);
		System.out.println("EndPc:"+this.endPc);
		System.out.println("HandlePc:"+this.handlerPc);
		System.out.println("CatchType:"+this.catchType);
	}
	
	public ExceptionInfo(){
		
	}
}
