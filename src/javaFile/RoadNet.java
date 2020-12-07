package javaFile;
import java.util.*;

/*
 *  һ��·���������ɵ�ʮ��·���Լ����ɵĵ�·
 * 
 * */
public class RoadNet {
	private Road[]  roads;						// ��·��
	private CrossRoads[] crossRoads;			// ʮ��·�ڼ�
	private int roadsNum;						// ��·����
	private int crossRoadsNum;					// ʮ��·����
	private int[][] mindistance;				// ��ʾ����·��֮�����̾���
	private int[][] preCrossRoad;				// ��ʾ����һ��·�ڵ�ǰ��·�ڱ�
	
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
	// ����·������С������ ����Ѱ�����·��  ���Ľṹֱ����ʮ��·�ڵ�id�Ž��б�ʾ
	public void minDistanceOfRoadNet() {
		// ����һ���ڵ������С������ ��������±��ʾ�ڵ��id �±�Ϊ0��ʾ��һ��ʮ��·�� �洢������ΪҪ���ﵱǰ�ڵ�����Ҫ��������һ��ʮ��·�ڵ�id
		int[][] distance = new int[this.crossRoadsNum][this.crossRoadsNum];
		for (int i=0; i< this.crossRoadsNum;i++) {
			for (int j=0;j< this.crossRoadsNum;j++)
				if(i!=j)
					distance[i][j] = 100000; // ���þ������10000 ��ʾ���ɴ�
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
		for (int i=0;i<this.crossRoadsNum;i++){			//Ĭ�Ͽ���ֱ�ӵ�����˳�ʼ��ǰ���ڵ�Ϊ��㡣
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
            //��i���㿪ʼ����
            for (int i=0;i<this.crossRoadsNum;i++){
                for (int j=0;j<this.crossRoadsNum;j++){
                    int len = distance[i][k] + distance[k][j];  //��i��������������k������j
                    if (len < distance[i][j]){
                    	distance[i][j] = len; //���¾���
                        pre[i][j] = pre[k][j];  //����ǰ������
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
		/* ��������֮�����·����ǰ�����ȡ���·����Ӧ�ıߵļ��� */
		int[] roadId = new int[10]; //  �����洢·��id
		int j = 9;
		// ������ĳһ��ʮ��·�ڵ�ǰ���ڵ㲻�ǿ�ʼ�ڵ�ʱ
		while(startId != this.preCrossRoad[startId-1][endId-1]) {
			
			CrossRoads crossRoads = this.crossRoads[this.preCrossRoad[startId-1][endId-1]-1]; // ��ʾ���������ӵı�
			for(int i =0; i < crossRoads.getConnectRoadNum(); i++) {
				if(crossRoads.getConnetRoad()[i].connectRoad_id == endId) {
					roadId[j] = crossRoads.getConnetRoad()[i].road.getId();
					j--;
				}
			}
			endId = this.preCrossRoad[startId-1][endId-1];
		}
		// ������ĳһ��ʮ��·�ڵ�ǰ���ڵ���ǿ�ʼ�ڵ�ʱ����ʾ�ö�·Ϊ��һ��·
		CrossRoads crossRoads = this.crossRoads[startId -1];
		for(int i =0; i < crossRoads.getConnectRoadNum(); i++) {
			if(crossRoads.getConnetRoad()[i].connectRoad_id == endId) {
				roadId[j] = crossRoads.getConnetRoad()[i].road.getId();
			}
		}
		
		int[] roadPath = new int[10-j+1];
		roadPath[0] =10 - j;		// �����һλ��ʾ��Ҫ�߼���·��
		
		for(int i=1; j < roadId.length;i++,j++) {
			roadPath[i] = roadId[j];
		}
		 return roadPath;
	}
}
