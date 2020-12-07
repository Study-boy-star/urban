package javaFile;

import java.util.Arrays;

class ConnectRoads{
    Road  road;					// ��ʾ���ӵĵ�·
	int connectRoad_id;		// ��ʾ��һ��ʮ��·��
	
	public ConnectRoads(Road road, int end) {
		this.road = road;
		this.connectRoad_id = end;
	}

	@Override
	public String toString() {
		return "ConnectRoads [road=" + road + ", connectRoad_id=" + connectRoad_id + "]";
	}
	
}


public class CrossRoads {
	private Point position;	// ��ʾ����λ��
	private int id;			// ��ʾ·�ڵ�id
	private ConnectRoads[] connetRoad = new ConnectRoads[5];  // ��ʾ�͸�ʮ��·�����ӵĵ�·
	public ConnectRoads[] getConnetRoad() {
		return connetRoad;
	}
	public void setConnetRoad(ConnectRoads[] connetRoad) {
		this.connetRoad = connetRoad;
	}
	private int ConnectRoadNum;
	
	public int getConnectRoadNum() {
		return ConnectRoadNum;
	}
	public void setConnectRoadNum(int connectRoadNum) {
		ConnectRoadNum = connectRoadNum;
	}
	public CrossRoads(int id) {
		this.position = null;
		this.id = id;
		this.ConnectRoadNum = 0;
	}
	public CrossRoads(Point position, int id) {
		this.position = position;
		this.id = id;
		this.ConnectRoadNum = 0;
	}
	// Ϊ��ʮ��·�ڴ����������ӵĵ�·
	public void add_connetRoad(Road road, int end) {
		/*
		 * param: road �����ӵ�·�� end ·�����յ�
		 */
		ConnectRoads connectRoads = new ConnectRoads(road, end);
		this.connetRoad[this.ConnectRoadNum] = connectRoads;
		this.ConnectRoadNum += 1;
	}
	@Override
	public String toString() {
		return "CrossRoads [position=" + position + ", id=" + id + ", connetRoad=" + Arrays.toString(connetRoad)
				+ ", ConnectRoadNum=" + ConnectRoadNum + "]";
	}

}
