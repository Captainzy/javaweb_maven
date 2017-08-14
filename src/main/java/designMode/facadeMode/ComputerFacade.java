package designMode.facadeMode;

public class ComputerFacade {
    private Cpu processor;
    private Memory ram;
    private HardDriver hd;

    public ComputerFacade() {
        this.processor = new Cpu();
        this.ram = new Memory();
        this.hd = new HardDriver();
    }

    public void start() {
        processor.freeze();
        ram.load(123, hd.read(123, 133));
        processor.jump(133);
        processor.execute();
    }
}  
