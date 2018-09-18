import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class main {

    /** initialSolution -
     * Add words to a set. If set.add(z) returns false,
     * we have a duplicate.
     * Requires making a duplicate of database (until duplicate is found).
     * Thus, initialSolution can be costly in terms of memory.
     * @param databaseArr - the "database" of words
     * @return index of first duplicated string
     */
    private int initialSolution(ArrayList<String> databaseArr){
        HashSet<String> set = new HashSet<>();
        for(int z = 0; z < databaseArr.size(); z++){
            boolean tmp = set.add(databaseArr.get(z));
            if(!tmp) return z;
        }
        return -1;
    }

//-----------------------------------------------------------------------------------------//

    /** sort - Part of secondSolution
     * One-pass Insertion sort. While sorting,
     * if duplicate found return, and stop sorting.
     * @param arr - the subList of database to be sorted
     * @return index of first duplicated string
     */
    private boolean sort(List<String> arr) {
            if(arr.size()<2) return false;
            int n = arr.size();
            String nextWord = arr.get(n-1);
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            int j = n-2;
            // Breaks in two cases:
            // 1) j = -1
            // 2) nextWord is either the same or after arr.get(j)
            while (j>= 0 && arr.get(j).compareTo(nextWord) > 0) {
                arr.set(j+1,arr.get(j));
                j = j-1;
            }
            arr.set(j+1, nextWord);
            if(j==-1){
                return false;
            }
            if(arr.get(j).compareTo(nextWord)==0){
                System.out.println("First: " + arr.get(j) + " Second: " + nextWord);
                return true;
            }

            return false;
    }


    /** secondSolution
     * Sorts words in place. While sorting, checks for
     * duplicate. Use of O(n) insertion sort each time
     * Thus, secondSolution minimizes memory usage, but is computationally effective.
     * Benefit: Database only needs to be sorted once and is stored sorted.
     *
     * @param databaseArr - the "database" of words
     * @return index of first duplicated string
     */

    private int secondSolution(ArrayList<String> databaseArr){
        for(int z = 0; z <= databaseArr.size(); z++) {
            if(sort(databaseArr.subList(0,z))) return z;
            //System.out.println(databaseArr.subList(0, z));
            //System.out.println(z + " --------------------------------------------");
        }
        return -1;
    }

//-----------------------------------------------------------------------------------------//

    /**
     * Assumption, looking for first duplicate word without regards to capitalization
     * @param args - command line args
     */
    public static void main(String[] args){
        main mainInstance = new main();

        File file = new File("./src/database.txt");
        ArrayList<String> databaseArr = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        }catch (FileNotFoundException e){
            System.out.println("Please make sure to download \"database.txt\". Additionaly," +
                    "please make sure both \"main.java\" and \"database.txt\" are a folder named " +
                    "\"src\".");
            e.printStackTrace();
        }
        while(scanner.hasNext()){
            //Using default white space delimiter
            databaseArr.add(scanner.next().toLowerCase());
        }
        //for(int z = 0; z < databaseArr.size(); z++) System.out.println(databaseArr.get(z));

        System.out.println(mainInstance.secondSolution(databaseArr));
    }
}
