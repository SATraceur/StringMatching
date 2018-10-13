package exactstringmatching;

public abstract class StringMatcher {
    
    private double timeToSetTarget;
    private double timeToSetPattern;
    private double timeToMatch;
    
    public void setTargetTimed(String target)
    {
        long tic = System.nanoTime();
        this.setTarget(target);
        long toc = System.nanoTime();
        timeToSetTarget = (toc-tic)/1000000.0;
    }
    
    public void setPatternTimed(String target)
    {
        long tic = System.nanoTime();
        this.setPattern(target);
        long toc = System.nanoTime();
        timeToSetPattern = (toc-tic)/1000000.0;
    }
    
    public int matchTimed()
    {
        long tic = System.nanoTime();
        int m = this.match();
        long toc = System.nanoTime();
        timeToMatch = (toc-tic)/1000000.0;
        
        return m;
    }
    
    public String toTimingString()
    {
        return "Target: " + this.timeToSetTarget + "ms, " +
                "Pattern: " + this.timeToSetPattern + "ms, " +
                "Match: " + this.timeToMatch +"ms, " + "\n" +
                "Total: " + (this.timeToSetTarget+this.timeToSetPattern+this.timeToMatch) + "ms";
    }
    
    public abstract void setTarget(String target);
    public abstract void setPattern(String pattern);    
    public abstract int match();
    public abstract int getNumberOfComparisons();
    
}
