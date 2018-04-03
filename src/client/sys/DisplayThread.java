package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import data.EmotivData;

/*
 * This thread adds each value to the graph dynamically.
 */
public class DisplayThread implements Runnable {
	  private volatile boolean exit=false;
	  EmotivData data;
	  public TimeSeries graphSeries[];
	  TimeSeriesCollection dataset ;
	  int value;
	  

  public DisplayThread( EmotivData data,TimeSeries[] graphSeries, TimeSeriesCollection dataset ) {
		super();
		this.data= data;
		this.graphSeries = graphSeries;
		this.dataset = dataset;
		this.value = value;
	}
@Override
  public void run() {
    while (!exit) {
  	  
    	double values[];
    	values = new double[12];
    	values[0]= 10*data.getLookingRight();
    	values[1]= 20*data.getEyebrowRaise();
    	values[2]= 30*data.getLookingLeft();
    	values[3]= 40*data.getEyebrowFurrow();
    	values[4]= 50*data.getLookingDown();
    	values[5]= 60*data.getLookingUp();
    	values[6]= 70*data.getRightWink();
    	values[7]= 80*data.getLeftWink();
    	values[8]= 90*data.getBlink();
    	values[9]= 100*data.getEyesOpen();
    	values[10]= 110*data.getSmile();
    	values[11]= 120*data.getClench();

				for(int i=0;i<12;i++)
				{
					System.out.println(values[i]);
						
						graphSeries[i].add(new Millisecond(),values[i]);
						dataset.getSeries(i).addOrUpdate(new Millisecond(),values[i]);
					
					
					
  	        }
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  public void stop()
  {
  	exit=true;
  }
}