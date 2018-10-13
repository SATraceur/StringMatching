package exactstringmatching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExactStringMatching {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length == 0) {
            System.out.println("usage: java exactstringmatching.ExtactStringMatching <file> <algorithm> <verbose>");
            System.out.println("  <file>: is the file to search");
            System.out.println("  <algorithm>: ESM algorithm to use [builtin|bm|kmp|other], default: builtin");
            System.out.println("  <versbose>: is specified and is verbose then timing info outputted, default: not specified");

        } else {
            
            String file = args[0];

            Scanner s = new Scanner(new File(file));
            StringBuilder targetBuilder = new StringBuilder();
            while (s.hasNextLine()) {
                targetBuilder.append(s.nextLine());
            }
            String target = targetBuilder.toString();

            StringMatcher matcher = null;

            String algorithm;
            if (args.length > 1) {
                algorithm = args[1];
            } else {
                algorithm = "builtin";
            }

            switch (algorithm) {
                case "builtin":
                    matcher = new StringMatcherBuiltin();
                    break;
                case "bruteforce": 
                    matcher = new BruteForce(); // checkpoint 7
                    break;
                case "bm": 
                    matcher = new BoyerMoore(); // checkpoint 8
                    break;
                case "kmp": 
                    matcher = new KMP(); // checkpoint 9
                    break;


            }

            matcher.setTargetTimed(target);

            Scanner in = new Scanner(System.in);
            String pattern = in.nextLine();

            // verbose output
            boolean verbose = false;
            if (args.length > 2) {
                verbose = args[2].equals("verbose");
            }

            while (!pattern.isEmpty()) {
                matcher.setPatternTimed(pattern);
                int index = matcher.matchTimed();
                System.out.print("index: " + index);
                if (!algorithm.equals("other") || verbose) {
                    System.out.print(", comp: " + matcher.getNumberOfComparisons());
                }
                if (verbose) {
                    System.out.print(", " + matcher.toTimingString());
                }
                System.out.println();
                pattern = in.nextLine();
            }
        }
    }
}
