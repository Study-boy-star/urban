package javaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


import window.Win;

public class Start {

	public static void init_road(Scanner input, Road[] roads, int roadNum) {
		/*
		 * ��ʼ����·��Ϣ ��������Լ�����
		 */
		System.out.print("�������·�ı�źͳ���");
		for (int i = 0; i < roadNum; i++) {
			int id = input.nextInt();
			int length = input.nextInt();
			Road road = new Road(id, length);
			roads[i] = road;
		}
	}

	public static void init_crossRoad(Scanner input, CrossRoads[] crossRoads, int crossRoadNum) {
		/*
		 * ��ʼ��ʮ��·�ڵ���Ϣ���������
		 */
		System.out.print("������ʮ��·�ڵı��");
		for (int i = 0; i < crossRoadNum; i++) {
			int id = input.nextInt();
			CrossRoads crossRoad = new CrossRoads(id);
			crossRoads[i] = crossRoad;
		}
	}

	public static void connetRoadAndCrossRoad(Scanner input, Road[] roads, CrossRoads[] crossRoads) {
		/*
		 * ��ʮ��·�����·��������
		 */
		System.out.println("�ֱ�������ʼ���յ��ʮ��·�ڱ�ţ��Լ���Ӧ��·�ı��");
		for (int i = 0; i < roads.length; i++) {
			int start_id = input.nextInt();
			int end_id = input.nextInt();
			int road_id = input.nextInt();
			crossRoads[start_id - 1].add_connetRoad(roads[road_id - 1], end_id);
			crossRoads[end_id - 1].add_connetRoad(roads[road_id - 1], start_id); // Ĭ�����ɵ�������ͼ�������յ���Ի���
			
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
		System.out.println("������ʮ��·�����Ե�·��");
		System.out.println(System.getProperty("user.dir"));
		File file = new File("E:\\java_WorkStation\\Project1\\src\\data\\road.txt");
		if (file.exists()) {
			System.out.println("�ļ��Ѵ���");
		}
		else {
			System.out.println("�ļ�������");
		}
		Scanner input = new Scanner(file);
		// ����ʮ��·�ڵ���Ŀ
		int crossRoadNum = input.nextInt();
		// �����·����Ŀ
		int roadNum = input.nextInt();
		// ��ʼ��·��
		RoadNet roadNet = new RoadNet(crossRoadNum, roadNum);
		// ��������洢ʮ��·�ڵ���Ϣ�Լ���·����Ϣ
		CrossRoads[] crossRoads = new CrossRoads[crossRoadNum];
		Road[] roads = new Road[roadNum];
		// �ֱ������·�Լ�ʮ��·�ڵ������Ϣ
		init_road(input, roads, roadNum);
		init_crossRoad(input, crossRoads, crossRoadNum);
		// ����·��ʮ��·�ڽ���ƥ��
		connetRoadAndCrossRoad(input, roads, crossRoads);
		input.close();
//		printInfo(roads); 
//		printInfo(crossRoads);
		init_roadNet(roadNet, roads, crossRoads);
//		System.out.println(roadNet);
		return roadNet;
	}
	public static void init_car(int carNum,double carSpeed,int startLabel,int endLabel,RoadNet roadNet) {
		Car car1 = new Car(carNum,carSpeed);		        	// ���ñ�źͳ��ٶԳ����г�ʼ��
		car1.setPath_id(roadNet.getRoad(startLabel, endLabel));		// ָ�������յ㲢��ó���·��
		for (int i = 0; i< car1.getPath_id().length;i++) {
			System.out.print(" " + car1.getPath_id()[i]);			// ��һλ��ʾһ��Ҫ�߼���·���������ı�ʾҪ���Ǽ���
		}
		System.out.println();
		car1.comValeOfDis(roadNet.getRoads());		// �������ÿһ��·��������,�����жϵ�ǰ����������·��
		car1.setRoadNet(roadNet);
		Thread carThread1 = new Thread(car1);	// ʵ����Tread ����
		carThread1.start();
	}
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Win w = new Win();
		w.biuldWind();
		RoadNet roadNet = createRoadNet();		// ����·����

//		init_car(1,0.5,2,5,roadNet);
//		Thread.sleep(2000);
//		init_car(2,0.5,2,1,roadNet);
//		init_car(3,0.5,1,5,roadNet);
//		System.out.println("������һ��");
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
