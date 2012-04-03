package shop.util;

public class Sequence {
	
	private int nextNumber; 
    private String seqname; 
     
    public Sequence(int initial, String name) 
    { 
              nextNumber = initial; 
              seqname = name; 
    } 
     
    public int nextVal()
    { 
              this.nextNumber = nextNumber + 1; 
              return nextNumber; 
    } 
     
    public int currVal()
    { 
              return nextNumber; 
    } 
     
    public String getName() 
    { 
              return this.seqname; 
    }
    
    public void setName(String name)
    {
    		seqname = name;
    }
}
