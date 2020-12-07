package javaFile;

import java.util.Arrays;

class ConnectRoads{
    Road  road;					// 表示连接的道路
	int connectRoad_id;		// 表示下一个十字路口
	
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
	private Point position;	// 表示他的位置
	private int id;			// 表示路口的id
	private ConnectRoads[] connetRoad = new ConnectRoads[5];  // 表示和该十字路口连接的道路
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
	// 为该十字路口创建他所连接的道路
	public void add_connetRoad(Road road, int end) {
		/*
		 * param: road 所连接的路径 end 路径的终点
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
