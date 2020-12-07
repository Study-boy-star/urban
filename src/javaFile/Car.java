package javaFile;

public class Car implements Runnable{
	private int id;		              // 车的编号
	private String start_point;
	private String end_point;
	private String current_position;
	private int current_road;
	private Double speed;		// 表示 m/s
	private int[] path_id;   // 存储路线的id	// 第一位用来存储表示一共需要走几条路。 下标1 表示要走的第一条路 以此类推。
	private Double[] disToStart;			//到起点的距离，用来判断所处的道路id。
	private long startTime;					// 表示车辆行驶的开始时间
	private Double alltime;
	private RoadNet roadNet;				// 	表示车辆所属的路网，用于更新路网的一些信息。
	
	public RoadNet getRoadNet() {
		return roadNet;
	}
	public void setRoadNet(RoadNet roadNet) {
		this.roadNet = roadNet;
	}
	public Car(int id,Double speed) {
		this.id = id;
		this.speed = speed;
		
	}
	public Car(String start_point, String end_point, String current_position, int current_road, Double speed) {
		super();
		this.start_point = start_point;
		this.end_point = end_point;
		this.current_position = current_position;
		this.current_road = current_road;
		this.speed = speed;
	}
	
	public int[] getPath_id() {
		return path_id;
	}
	
	public void setPath_id(int[] path_id) {
		this.path_id = path_id;
	}

	public Double[] getDisToStart() {
		return disToStart;
	}

	public void setDisToStart(Double[] disToStart) {
		this.disToStart = disToStart;
	}

	public String getStart_point() {
		return start_point;
	}

	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}

	public String getEnd_point() {
		return end_point;
	}

	public void setEnd_point(String end_point) {
		this.end_point = end_point;
	}

	public String getCurrent_position() {
		return current_position;
	}

	public void setCurrent_position(String current_position) {
		this.current_position = current_position;
	}

	public int getCurrent_road() {
		return current_road;
	}

	public void setCurrent_road(int current_road) {
		this.current_road = current_road;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	// 这里需要用到路线的长度， 因此因此需要将路网中的道路传入，方便获取长度。
	public void comValeOfDis(Road[] roads) {
//		for(int i=0;i<roads.length;i++) {
//			System.out.println(roads[i].getLength());
//		}
//		
		Double[] dis = new Double[this.path_id.length];
		dis[0]=0.0;
		for(int i=1; i<dis.length;i++) {
			dis[i] = dis[i-1] + (double)roads[this.path_id[i]-1].getLength();
			System.out.println(dis[i]);
		}
		this.disToStart = dis;
		this.alltime = dis[dis.length-1] / this.speed; // 路程除以速度等于时间
	}
	// 传进来的是一个double 类型的数据 表示车已经行走的距离。
	public boolean computerCurrentRoad(double distance) {
		int past_road = this.current_road;						// 表示前一段时间在哪条路上。
		for(int i = 0;i<this.disToStart.length; i++) {
			if(distance < this.disToStart[i]) {		
				this.current_road = this.path_id[i];	// 如果大于的话就表示从当前这段路已经走过了。
				System.out.println("car"+this.id+"在路" +this.current_road+"上");
				int count;
				if(past_road == this.current_road)				// 表示和一秒之前在一条路上
					return false;
				else
					count = 0;
					for(int j=0;j<this.getRoadNet().getRoads().length;j++) {
						Road road = this.getRoadNet().getRoads()[j];
						if (road.getId()==past_road) {
							road.carNumSub();
							count +=1;
							if(count==2)
								break;
						}
						if(road.getId()==this.current_road) {
							road.carNumAdd();
							count+=1;
							if(count == 2)
								break;
						}
					}
					return true;								// 表示在上一条路上。
			}
		}
		return true; // 这种表示到达终点了。
	}
	
	public void startRun() throws InterruptedException {
		this.startTime = System.currentTimeMillis();	// 表示系统当前的时间
		
		while(true) {
			Thread.sleep(1000);
			long currTime = System.currentTimeMillis();
			double timeSpace =((double)currTime-this.startTime)/1000;
			if (timeSpace>this.alltime)
				break;
			computerCurrentRoad(timeSpace*this.speed);		// 用来计算当前在哪段路上。
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.startRun();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
