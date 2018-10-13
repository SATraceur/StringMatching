package exactstringmatching;
/*
    All code for this algorithm was taken directly from the lab sheet.
*/
public class BruteForce extends StringMatcher{
    
    String target;
    String pattern;
    int comparisons;

    @Override
    public void setTarget(String target) {
         this.target = target;
    }

    @Override
    public void setPattern(String pattern) {
         this.pattern = pattern;
    }

    @Override
    public int match() {
        for(int i = 0; i <= target.length() - pattern.length(); i++) { // Iterate through target
            int j = 0; 
            while(j < pattern.length() && (target.charAt(i + j) == pattern.charAt(j))){ // Iterate through pattern
                j++; comparisons++;
            }
            if (j == pattern.length()) {return i;}
            comparisons++;
        }
        return -1;
    }

    @Override
    public int getNumberOfComparisons() {
         return comparisons;
    }
    
}
