package javaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


import window.Win;

public class Start {

	public static void init_road(Scanner input, Road[] roads, int roadNum) {
		/*
		 * 初始化道路信息 包含编号以及长度
		 */
		System.out.print("请输入道路的编号和长度");
		for (int i = 0; i < roadNum; i++) {
			int id = input.nextInt();
			int length = input.nextInt();
			Road road = new Road(id, length);
			roads[i] = road;
		}
	}

	public static void init_crossRoad(Scanner input, CrossRoads[] crossRoads, int crossRoadNum) {
		/*
		 * 初始化十字路口的信息，包含编号
		 */
		System.out.print("请输入十字路口的编号");
		for (int i = 0; i < crossRoadNum; i++) {
			int id = input.nextInt();
			CrossRoads crossRoad = new CrossRoads(id);
			crossRoads[i] = crossRoad;
		}
	}

	public static void connetRoadAndCrossRoad(Scanner input, Road[] roads, CrossRoads[] crossRoads) {
		/*
		 * 将十字路口与道路连接起来
		 */
		System.out.println("分别输入起始，终点的十字路口编号，以及对应道路的编号");
		for (int i = 0; i < roads.length; i++) {
			int start_id = input.nextInt();
			int end_id = input.nextInt();
			int road_id = input.nextInt();
			crossRoads[start_id - 1].add_connetRoad(roads[road_id - 1], end_id);
			crossRoads[end_id - 1].add_connetRoad(roads[road_id - 1], start_id); // 默认生成的是无向图，起点和终点可以互换
			
			roads[road_id - 1].setStart_point(start_id);
			roads[road_id - 1].setEnd_point(end_id);
			roads[road_id - 1].setStart_point(end_id);
			roads[road_id - 1].setEnd_point(start_id);
		}

	}

	public static void init_roadNet(RoadNet roadNet, Road[] roads, CrossRoads[] crossRoads) {
		roadNet.setCrossRoads(crossRoads);
		roadNet.setRoads(roads);
		roadNet.minDistanceOfRoadNet();
	}

	public static void printInfo(Object[] obj) {
		for (int i = 0; i < obj.length; i++) {
			System.out.println(obj[i]);
		}
	}
	public static RoadNet createRoadNet() throws FileNotFoundException {
		System.out.println("请输入十字路口数以道路数");
		System.out.println(System.getProperty("user.dir"));
		File file = new File("E:\\java_WorkStation\\Project1\\src\\data\\road.txt");
		if (file.exists()) {
			System.out.println("文件已存在");
		}
		else {
			System.out.println("文件不存在");
		}
		Scanner input = new Scanner(file);
		// 输入十字路口的数目
		int crossRoadNum = input.nextInt();
		// 输入道路的数目
		int roadNum = input.nextInt();
		// 初始化路网
		RoadNet roadNet = new RoadNet(crossRoadNum, roadNum);
		// 创建数组存储十字路口的信息以及道路的信息
		CrossRoads[] crossRoads = new CrossRoads[crossRoadNum];
		Road[] roads = new Road[roadNum];
		// 分别输入道路以及十字路口的相关信息
		init_road(input, roads, roadNum);
		init_crossRoad(input, crossRoads, crossRoadNum);
		// 将道路与十字路口进行匹配
		connetRoadAndCrossRoad(input, roads, crossRoads);
		input.close();
//		printInfo(roads); 
//		printInfo(crossRoads);
		init_roadNet(roadNet, roads, crossRoads);
//		System.out.println(roadNet);
		return roadNet;
	}
	public static void init_car(int carNum,double carSpeed,int startLabel,int endLabel,RoadNet roadNet) {
		Car car1 = new Car(carNum,carSpeed);		        	// 利用编号和车速对车进行初始化
		car1.setPath_id(roadNet.getRoad(startLabel, endLabel));		// 指定起点和终点并获得车的路线
		for (int i = 0; i< car1.getPath_id().length;i++) {
			System.out.print(" " + car1.getPath_id()[i]);			// 第一位表示一共要走几段路，接下来的表示要走那几段
		}
		System.out.println();
		car1.comValeOfDis(roadNet.getRoads());		// 计算距离每一段路的起点距离,用于判断当前车辆在哪条路上
		car1.setRoadNet(roadNet);
		Thread carThread1 = new Thread(car1);	// 实例化Tread 对象
		carThread1.start();
	}
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Win w = new Win();
		w.biuldWind();
		RoadNet roadNet = createRoadNet();		// 创建路网。

//		init_car(1,0.5,2,5,roadNet);
//		Thread.sleep(2000);
//		init_car(2,0.5,2,1,roadNet);
//		init_car(3,0.5,1,5,roadNet);
//		System.out.println("我是下一步");
	}
}
/*
 * 4 3 1 5 2 8 3 10 1 2 3 4 1 3 1 3 4 2 2 3 3
6 8
1 8
2 1
3 15
4 4
5 1
6 10
7 2
8 3
1
2
3
4
5
6
1 2 1
2 6 2
1 6 3
1 3 4
3 6 5
6 4 6
3 5 7
4 5 8
 */
