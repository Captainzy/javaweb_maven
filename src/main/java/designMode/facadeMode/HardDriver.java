package designMode.facadeMode;

public class HardDriver {
    public byte[] read(long lba, int size) {
    	System.out.println("HardDriver reading...");
    	return new byte[]{};
    }
} 
