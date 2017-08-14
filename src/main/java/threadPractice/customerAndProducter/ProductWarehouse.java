package threadPractice.customerAndProducter;

/**
 * @author zouyang
 * @time 2017年1月9日 下午5:05:55
 * @description 产品仓库
 */
public class ProductWarehouse {
	private final int capacity = 100; // 容量
	private int curAmount; // 当前数量

	public int getCurAmount() {
		return curAmount;
	}

	public void setCurAmount(int curAmount) {
		this.curAmount = curAmount;
	}

	public int getCapacity() {
		return capacity;
	}

	public void sale(int count) {
		synchronized (this) {
			if (this.curAmount <= (this.capacity*0.1)) {
				System.out.println("库存空了，等待消费。还有物品：" + curAmount);
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("库存有余，正在消费。还有物品：" + curAmount);
				this.curAmount = this.curAmount - count;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				notifyAll();
			}
		}
	}

	public void produce(int count) {
		synchronized (this) {
			while (true) {
				if (this.curAmount < this.capacity) {
					System.out.println("库存未满，正在生产。还有物品:" + curAmount);
					this.curAmount = this.curAmount + count;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					notifyAll();
				} else {
					System.out.println("库存满了，停止生产。还有物品：" + curAmount);
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
