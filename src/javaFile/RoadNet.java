package javaFile;
import java.util.*;

/*
 *  一个路网包含若干的十字路口以及若干的道路
 * 
 * */
public class RoadNet {
	private Road[]  roads;						// 道路集
	private CrossRoads[] crossRoads;			// 十字路口集
	private int roadsNum;						// 道路总数
	private int crossRoadsNum;					// 十字路口数
	private int[][] mindistance;				// 表示两个路口之间的最短距离
	private int[][] preCrossRoad;				// 表示到达一个路口的前驱路口表
	
	public RoadNet() {
		super();
	}
	public RoadNet(int crossRoadNum, int roadsNum) {	
		this.roadsNum = roadsNum;
		this.crossRoadsNum = crossRoadNum;
		this.roads = new Road[roadsNum];
		this.crossRoads = new CrossRoads[crossRoadNum];
	}
	public Road[] getRoads() {
		return roads;
	}

	public void setRoads(Road[] roads) {
		this.roads = roads;
	}

	public CrossRoads[] getCrossRoads() {
		return crossRoads;
	}

	public void setCrossRoads(CrossRoads[] crossRoads) {
		this.crossRoads = crossRoads;
	}

	public int getRoadsNum() {
		return roadsNum;
	}

	public void setRoadsNum(int roadsNum) {
		this.roadsNum = roadsNum;
	}

	public int getCrossRoadsNum() {
		return crossRoadsNum;
	}

	public void setCrossRoadsNum(int crossRoadsNum) {
		this.crossRoadsNum = crossRoadsNum;
	}
	@Override
	public String toString() {
		return "RoadNet [roads=" + Arrays.toString(roads) + ", crossRoads=" + Arrays.toString(crossRoads)
				+ ", roadsNum=" + roadsNum + ", crossRoadsNum=" + crossRoadsNum + "]";
	}
	// 生成路网的最小生成树 用来寻找最短路径  树的结构直接用十字路口的id号进行表示
	public void minDistanceOfRoadNet() {
		// 将第一个节点放入最小生成树 用数组的下标表示节点的id 下标为0表示第一个十字路口 存储的数据为要到达当前节点所需要经过的上一个十字路口的id
		int[][] distance = new int[this.crossRoadsNum][this.crossRoadsNum];
		for (int i=0; i< this.crossRoadsNum;i++) {
			for (int j=0;j< this.crossRoadsNum;j++)
				if(i!=j)
					distance[i][j] = 100000; // 利用距离等于10000 表示不可达
		}
		for(int i = 0; i < this.roadsNum;i++) {
			distance[this.roads[i].getStart_point()-1][this.roads[i].getEnd_point()-1] = this.roads[i].getLength();
			distance[this.roads[i].getEnd_point()-1][this.roads[i].getStart_point()-1] = this.roads[i].getLength();
		}
		
//		for (int i=0; i< this.crossRoadsNum;i++) {
//			for (int j=0;j< this.crossRoadsNum;j++)
//				System.out.print(String.format("%-16s", distance[i][j]));
//			System.out.println();
//		}
		
		
		int[][] pre = new int[this.crossRoadsNum][this.crossRoadsNum];
		for (int i=0;i<this.crossRoadsNum;i++){			//默认可以直接到，因此初始的前驱节点为起点。
			Arrays.fill(pre[i],i+1);
		}
		
//		for(int i =0; i<this.crossRoadsNum;i++) {
//			for(int j = 0; j< this.crossRoadsNum; j++) {
//				System.out.print(String.format("%-16s", pre[i][j]));
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		for (int k=0;k<this.crossRoadsNum;k++){
            //从i顶点开始出发
            for (int i=0;i<this.crossRoadsNum;i++){
                for (int j=0;j<this.crossRoadsNum;j++){
                    int len = distance[i][k] + distance[k][j];  //从i出发，经过顶点k，到达j
                    if (len < distance[i][j]){
                    	distance[i][j] = len; //更新距离
                        pre[i][j] = pre[k][j];  //更新前驱顶点
                    }
                }
            }
        }
		this.mindistance = distance;
		this.preCrossRoad = pre;
		System.out.println();
		for (int i=0; i< this.crossRoadsNum;i++) {
			for (int j=0;j< this.crossRoadsNum;j++)
				System.out.print(String.format("%-16s", distance[i][j]));
			System.out.println();
		}
		System.out.println();
		for(int i =0; i<this.crossRoadsNum;i++) {
			for(int j = 0; j< this.crossRoadsNum; j++) {
				System.out.print(String.format("%-16s", pre[i][j]));
			}
			System.out.println();
		}
	}
	
	public int[] getRoad(int startId, int endId) {
		/* 依据两点之间最短路径的前驱点获取最短路径对应的边的集合 */
		int[] roadId = new int[10]; //  用来存储路径id
		int j = 9;
		// 当到达某一个十字路口的前驱节点不是开始节点时
		while(startId != this.preCrossRoad[startId-1][endId-1]) {
			
			CrossRoads crossRoads = this.crossRoads[this.preCrossRoad[startId-1][endId-1]-1]; // 表示其所有连接的边
			for(int i =0; i < crossRoads.getConnectRoadNum(); i++) {
				if(crossRoads.getConnetRoad()[i].connectRoad_id == endId) {
					roadId[j] = crossRoads.getConnetRoad()[i].road.getId();
					j--;
				}
			}
			endId = this.preCrossRoad[startId-1][endId-1];
		}
		// 当到达某一个十字路口的前驱节点就是开始节点时，表示该段路为第一段路
		CrossRoads crossRoads = this.crossRoads[startId -1];
		for(int i =0; i < crossRoads.getConnectRoadNum(); i++) {
			if(crossRoads.getConnetRoad()[i].connectRoad_id == endId) {
				roadId[j] = crossRoads.getConnetRoad()[i].road.getId();
			}
		}
		
		int[] roadPath = new int[10-j+1];
		roadPath[0] =10 - j;		// 用最后一位表示需要走几条路。
		
		for(int i=1; j < roadId.length;i++,j++) {
			roadPath[i] = roadId[j];
		}
		 return roadPath;
	}
}
