package exactstringmatching;

/*
    This algorithm was constructed from the notes within the lecture slides and
    the search method from http://algs4.cs.princeton.edu/53substring/BoyerMoore.java.html
*/

public class BoyerMoore extends StringMatcher{

    String target;
    String pattern;
    int comparisons;
    int R = 256; // The Radix
    int[] L = new int[R];
    
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
        lastOccurrenceTable(); // Construct last occurrence table
        
        int skip;
        for (int i = 0; i <= target.length() - pattern.length(); i += skip) { // Iterate through target
            skip = 0;
            for (int j = pattern.length() - 1; j >= 0; j--) {   // Iterate through pattern right to left (Looking Glass)
                if (pattern.charAt(j) != target.charAt(i+j)) { 
                    skip = 1 > j - L[target.charAt(i+j)] ? 1 : j - L[target.charAt(i+j)]; // Skip = 1 or the last occurance of current target character
                    break;
                }
                comparisons++;
            }
            if (skip == 0) {return i;}  
            comparisons++;
        }
        return -1; 
    }

    @Override
    public int getNumberOfComparisons() {
        return comparisons;
    }
    
    public void lastOccurrenceTable() {
        for (int c = 0; c < R; c++) {L[c] = -1;}
        for (int j = 0; j < pattern.length(); j++) {L[pattern.charAt(j)] = j;}   
    }    
}
