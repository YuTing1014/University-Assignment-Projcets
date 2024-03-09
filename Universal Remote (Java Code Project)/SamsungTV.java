
public class SamsungTV implements UniversalRemote
{
	boolean on = false;
	int volume = 1;   //max volume = 10
	int hours=0;
	int mins=0;
	int channel=1;
	public void on() 
	{
		 this.on = true;
		 System.out.println(" >>>The Samsung TV is ON.");
	}

	public void off() 
	{
		 this.on = false;
		 System.out.println(" >>>The Samsung TV is OFF.");
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
	        System.out.println("The Samsung is not On");
	    }
	}
	public String get_timer()
	{
		return this.hours+":"+this.mins;
	}
	public void volumeUP(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && volume < 10) 
		    {
		        this.volume++;
		    }
		}
		if (this.on == false) 
	    {
	        this.volume = 0;
	    }
	}
	public void setChannel(int newChannel) 
	{
        if (on && newChannel >= 1 && newChannel <= 120) 
        {
            channel = newChannel;
            System.out.println(" >>>The TV channel is "+this.channel);
        }
        if (this.on == false) 
	    {
	        System.out.println(" >>>The TV is Off\n");
	    }
    }
	
	public int getChannel()
	{
		return this.channel;
	}
	
	public void volumeDOWN(int value)
	{
		for(int i=0; i<value; i++)
		{
		    if (this.on == true && volume > 0) 
		    {
		        this.volume--;
		        if(volume ==0)// editon
		        {
		        	System.out.println(" >>>The TV is muted.");
		        }
		    }
		    
		}
		
	    if (this.on == false) 
	    {
	        this.volume = 0;
	        System.out.println(" >>>The TV is not switch on.");//edition
	    }
	}
	//havent declare the case yet
	public void muteON()
	{
		if(this.on== true)
		{
			this.volume=0;
			System.out.println(" >>>The TV is muted.");
		}
		else
			System.out.println(" >>>The TV is not switch on.");
	}

	
	public int getVolume()
	{
		return this.volume;
	}
	
	public String toString()
	{
		return "Samsung TV";
	}

}
