package javaFile;

public class Car implements Runnable{
	private int id;		              // ���ı��
	private String start_point;
	private String end_point;
	private String current_position;
	private int current_road;
	private Double speed;		// ��ʾ m/s
	private int[] path_id;   // �洢·�ߵ�id	// ��һλ�����洢��ʾһ����Ҫ�߼���·�� �±�1 ��ʾҪ�ߵĵ�һ��· �Դ����ơ�
	private Double[] disToStart;			//�����ľ��룬�����ж������ĵ�·id��
	private long startTime;					// ��ʾ������ʻ�Ŀ�ʼʱ��
	private Double alltime;
	private RoadNet roadNet;				// 	��ʾ����������·�������ڸ���·����һЩ��Ϣ��
	
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
	// ������Ҫ�õ�·�ߵĳ��ȣ� ��������Ҫ��·���еĵ�·���룬�����ȡ���ȡ�
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
		this.alltime = dis[dis.length-1] / this.speed; // ·�̳����ٶȵ���ʱ��
	}
	// ����������һ��double ���͵����� ��ʾ���Ѿ����ߵľ��롣
	public boolean computerCurrentRoad(double distance) {
		int past_road = this.current_road;						// ��ʾǰһ��ʱ��������·�ϡ�
		for(int i = 0;i<this.disToStart.length; i++) {
			if(distance < this.disToStart[i]) {		
				this.current_road = this.path_id[i];	// ������ڵĻ��ͱ�ʾ�ӵ�ǰ���·�Ѿ��߹��ˡ�
				System.out.println("car"+this.id+"��·" +this.current_road+"��");
				int count;
				if(past_road == this.current_road)				// ��ʾ��һ��֮ǰ��һ��·��
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
					return true;								// ��ʾ����һ��·�ϡ�
			}
		}
		return true; // ���ֱ�ʾ�����յ��ˡ�
	}
	
	public void startRun() throws InterruptedException {
		this.startTime = System.currentTimeMillis();	// ��ʾϵͳ��ǰ��ʱ��
		
		while(true) {
			Thread.sleep(1000);
			long currTime = System.currentTimeMillis();
			double timeSpace =((double)currTime-this.startTime)/1000;
			if (timeSpace>this.alltime)
				break;
			computerCurrentRoad(timeSpace*this.speed);		// �������㵱ǰ���Ķ�·�ϡ�
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
