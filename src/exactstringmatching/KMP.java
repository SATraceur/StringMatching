package exactstringmatching;

/*
    All code for this algorithm was taken directly from the lecture slides.
*/

public class KMP extends StringMatcher{

    String target;
    String pattern;
    int comparisons;
    int[] F = new int[256];
    
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
        failTable(); // construct fail table
        
        int i = 0, j = 0;
        while(i < target.length()) {
            if(pattern.charAt(j) == target.charAt(i)) {
                if(j == pattern.length() - 1){
                    comparisons++;
                    return (i - pattern.length() + 1);
                }
                i++; j++;
            }
            else if(j > 0) {
                j = F[j - 1];                
            }
            else {i++;}
            comparisons++;
        }
        return -1;
    }

    @Override
    public int getNumberOfComparisons() {
        return comparisons;
    }
    
    public void failTable() {
        F[0] = 0;
        int i = 1, j = 0;
               
        while(i < pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                F[i] = j + 1;
                i++; j++;   
            }
            else if (j < 0) {j = F[j - 1];}
            else {F[i] = 0; i++;}
        }         
    }
}
