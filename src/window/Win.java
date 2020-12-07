package window;
import java.io.FileNotFoundException;
import java.util.Random;

import javaFile.RoadNet;
import javaFile.Start;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Win extends Application implements Runnable{
	private int carId = 4;
	private static RoadNet roadNet;
	
	private static Button addCar;
	private static Button road1;
	private static Button road2;
	private static Button road3;
	private static Button road4;
	private static Button road5;
	private static Button road6;
	private static Button road7;
	private static Button road8;
	private static Button run;
	private static Button monitor;
	
	private static TextField road1CarNum;
	private static TextField road2CarNum;
	private static TextField road3CarNum;
	private static TextField road4CarNum;
	private static TextField road5CarNum;
	private static TextField road6CarNum;
	private static TextField road7CarNum;
	private static TextField road8CarNum;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("win.fxml"));
    	bind(root);
    	primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
        primaryStage.show();

    }
    public void bind(Parent root) {
    	road1CarNum = (TextField) root.lookup("#road1CarNum");
    	road2CarNum = (TextField) root.lookup("#road2CarNum");
    	road3CarNum = (TextField) root.lookup("#road3CarNum");
    	road4CarNum = (TextField) root.lookup("#road4CarNum");
    	road5CarNum = (TextField) root.lookup("#road5CarNum");
    	road6CarNum = (TextField) root.lookup("#road6CarNum");
    	road7CarNum = (TextField) root.lookup("#road7CarNum");
    	road8CarNum = (TextField) root.lookup("#road8CarNum");
    	run = (Button) root.lookup("#run");
    	run.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					carRunning();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		
    	});
    	monitor = (Button) root.lookup("#monitor");
    	monitor.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Win w = new Win();
				Thread t = new Thread(w);
				t.start();
			}
    		
    	});
    	addCar = (Button) root.lookup("#addCar1");
    	addCar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Random r = new Random(System.currentTimeMillis());
				int startLabel = r.nextInt(5)+1;
				int endLabel = r.nextInt(5)+1;
				if(startLabel==endLabel) {
					endLabel = startLabel + 1;
				}
				System.out.println("起点" + startLabel);
				System.out.println("终点" + endLabel);
				Start.init_car(carId, 0.5, startLabel, endLabel, roadNet);
				System.out.println(333333333);
				carId+=1;
			}
		});
    	road1 = (Button) root.lookup("#road1");
    	road1.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road1CarNum.setText(roadNet.getRoads()[0].getCarNum()+"");
			}
    		
    	});
    	road2 = (Button) root.lookup("#road2");
    	road2.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road2CarNum.setText(roadNet.getRoads()[1].getCarNum()+"");
			}
    		
    	});
    	road3 = (Button) root.lookup("#road3");
    	road3.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(roadNet);
				road3CarNum.setText(roadNet.getRoads()[2].getCarNum()+"");
			}
    		
    	});
    	road4 = (Button) root.lookup("#road4");
    	road4.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road4CarNum.setText(roadNet.getRoads()[3].getCarNum()+"");
			}
    		
    	});
    	road5 = (Button) root.lookup("#road5");
    	road5.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				road5CarNum.setText(roadNet.getRoads()[4].getCarNum()+"");
			}
    		
    	});
    	road6 = (Button) root.lookup("#road6");
    	road6.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road6CarNum.setText(roadNet.getRoads()[5].getCarNum()+"");
			}
    		
    	});
    	road7 = (Button) root.lookup("#road7");
    	road7.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road7CarNum.setText(roadNet.getRoads()[6].getCarNum()+"");
			}
    		
    	});
    	road8 = (Button) root.lookup("#road8");
    	road8.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				road8CarNum.setText(roadNet.getRoads()[7].getCarNum()+"");
			}
    		
    	});
 
    	//monitorAllTime(roadNet);
    }
    public  void monitorAllTime() {
		long startTime = System.currentTimeMillis();	// 表示系统当前的时间
		System.out.println(roadNet);
		System.out.println("当前程序中线程的数目");
		System.out.println(Thread.activeCount());
		while(true) {
			road1CarNum.setText(roadNet.getRoads()[0].getCarNum()+"");
			road2CarNum.setText(roadNet.getRoads()[1].getCarNum()+"");
			road3CarNum.setText(roadNet.getRoads()[2].getCarNum()+"");
			road4CarNum.setText(roadNet.getRoads()[3].getCarNum()+"");
			road5CarNum.setText(roadNet.getRoads()[4].getCarNum()+"");
			road6CarNum.setText(roadNet.getRoads()[5].getCarNum()+"");
			road7CarNum.setText(roadNet.getRoads()[6].getCarNum()+"");
			road8CarNum.setText(roadNet.getRoads()[7].getCarNum()+"");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long nowtime = System.currentTimeMillis();
//			if ((nowtime-startTime)/1000>=20)
//				break;
		}
	}
    public static void biuldWind() {
    	launch();
    }
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    	RoadNet roadNet = Start.createRoadNet();		// 创建路网。
    	Win w = new Win();
    	w.roadNet = roadNet;
    	biuldWind();
		
    }
    public void carRunning() throws InterruptedException {
    	Start.init_car(1,0.5,2,5,roadNet);
		Thread.sleep(2000);
		Start.init_car(2,0.5,2,1,roadNet);
		Start.init_car(3,0.5,1,5,roadNet);
		System.out.println("当前程序中线程的数目");
		System.out.println(Thread.activeCount());
    }

	@Override
	public void run() {
		monitorAllTime();
	}
	
}
