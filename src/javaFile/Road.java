package javaFile;

public class Road implements Runnable{
	private int start_point;		// 起始点
	private int end_point;		//终点
	private int id;				// 编号
	private int length;			// 长度
	private int carNum = 0;		// 表示这条路上有几辆车。
	
	
	public synchronized void carNumAdd() {				// 同步函数 同一时间只能有一个进程去访问
		this.carNum +=1;
		System.out.println("我是road" + this.id + "我的路上现在有" + this.carNum+"辆车");
	}
	public synchronized void carNumSub() {				// 同步函数 同一时间只能有一个进程去访问
		this.carNum -=1;
		System.out.println("我是road" + this.id + "我的路上现在有" + this.carNum+"辆车");
	}
	public int getCarNum() {
		return carNum;
	}
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	public Road(int id) {
		this.id = id;
		this.length = 0;
		this.start_point = -1;
		this.end_point = -1;
		this.carNum = 0;
	}
	public Road(int id, int length) {
		this.id = id;
		this.length = length;
	}
	public Road(int start_point, int end_point, int id, int length) {
		super();
		this.start_point = start_point;
		this.end_point = end_point;
		this.id = id;
		this.length = length;
	}
	public int getStart_point() {
		return start_point;
	}
	public void setStart_point(int start_point) {
		this.start_point = start_point;
	}
	public int getEnd_point() {
		return end_point;
	}
	public void setEnd_point(int end_point) {
		this.end_point = end_point;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "Road [start_point=" + start_point + ", end_point=" + end_point + ", id=" + id + ", length=" + length
				+ "]";
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();	// 表示系统当前的时间
		while(true) {
			long currTime = System.currentTimeMillis();
			try {
				Thread.sleep(2000);
				System.out.print("Now the number of" + this.id + this.carNum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double timeSpace =((double)currTime-startTime)/1000;
			if(timeSpace>=20)
				break;
		}
	}

	
}
