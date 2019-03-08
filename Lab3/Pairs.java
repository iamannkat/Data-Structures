public class Pair {    
    private Character c; // character    
    private int p; // position
        
    Pair(Character c, int p)
    {
        this.c = c; this.p = p;
    }
    
    public int getP(){ 
		return this.p; }
    
	public Character getC(){ 
		return this.c; }
}
