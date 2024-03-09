
public class PanasonicRemoteFan implements UniversalRemote
{
	boolean on = false;
	boolean swing = false;
	int speed = 1; //max:5
	int hours = 0;
	int mins =0;
	
	public void on() 
	{
		 this.on = true;
		 System.out.println(" >>>The Panasonic Remote Fan is ON.");
	}

	public void off() 
	{
		 this.on = false;
		 System.out.println(" >>>The Panasonic Remote Fan is OFF.");
	}
	public void timer(int hrs, int min)
	{
		if(on && hrs <=12 && min <=60)
		{
			this.hours=hrs;
			this.mins=min;
		}
		if (this.on == false) 
	    {
	        System.out.println("The Fan is not ON");
	    }
	}

	public String get_timer()
	{
		return this.hours+":"+this.mins;
	}
	public void SpeedUP(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && speed < 5) 
		    {
		        this.speed++;
		    }
		}
		if (this.on == false) 
	    {
	        this.speed = 0;
	    }
	}
	
	public void SpeedDOWN(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && speed > 0) 
		    {
		        this.speed--;
		    }
		}
	    if (this.on == false) 
	    {
	        this.speed = 0;
	    }
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void swingON()
	{
		if(this.on == true)
		{
			this.swing = true;
			System.out.println(" >>>The swing mode of Fan is ON.");
		}
		else
			System.out.println(" >>>The Fan is not switched on yet.");
		
	}
	
	public void swingOFF()
	{
		if(this.on == true)
		{
			this.swing = false;
			System.out.println(" >>>The swing mode of Fan is OFF.");
		}
		else
			System.out.println(" >>>The Fan is not switched on yet.");
	}
	
	public String getSwing()
	{
		if(this.swing==true)
			return "The Swing mode of Fan is ON.";
		else
			return "The Swing mode of Fan is OFF.";
	}
	
	public String toString()
	{
		return "Panasonic Remote Fan";
	}
}
