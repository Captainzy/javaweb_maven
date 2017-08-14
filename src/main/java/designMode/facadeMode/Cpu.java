package designMode.facadeMode;

public class Cpu {
    public void freeze() { 
    	System.out.println("CPU freeze....");
    }
    
    public void jump(long position) {
    	System.out.println("CPU jump....");
    }
    
    public void execute() { 
    	System.out.println("CPU execute....");
    }
}
