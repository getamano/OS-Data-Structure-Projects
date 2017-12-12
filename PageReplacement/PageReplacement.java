/*
 * @author Aman Hordofo
 * Date: 11/20/2017
 * Description: A program that implements page replacement algorithms FIFO,  LRU and Optimal
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*; // will import all such us (ArrayList, List, Scanner)

public class PageReplacement 
{

    //method for FIFO page replacement algorithm
    public static int fifo(String reference_string, int frames) {
        int faults = 0;

        int[] array = new int[frames];

        for (int i = 0; i < frames; i++) {
            array[i] = -1;
        }

        int pointer = 0;

        //String tokenizer class to break a string into tokens
        StringTokenizer tok = new StringTokenizer(reference_string);
        //loop up to the end
        while (tok.hasMoreTokens()) {
            int value = Integer.parseInt(tok.nextToken());
            //check if exist in frame already 
            if (!(isInFrames(value, frames, array))) {
                faults++;	//increase count of faults
                array[pointer] = value;
                pointer = (pointer + 1) % frames; //move the pointer up in the queue
            }
        }
        return faults;
    }
    //method for LRU page replacement algorithm
    static int lru(String reference_string, int frames) {
        int faults = 0;
        int[] array = new int[frames];
        int[] age = new int[frames];

        for (int i = 0; i < frames; i++) {
            array[i] = -1;
            age[i] = 0;
        }

        //String tokenizer class to break a string into tokens
        StringTokenizer tok = new StringTokenizer(reference_string);
        //loop up to the end
        while (tok.hasMoreTokens()) {

            for (int i = 0; i < frames; i++) {
                age[i]++;
            }

            int value = Integer.parseInt(tok.nextToken());
            //check if exist in frame already 
            if (!(isInFrames(value, frames, array))) {
                faults++;
                int index = findMax(age);
                array[index] = value;
                age[index] = 0;
            } else {
                for (int i = 0; i < frames; i++) {
                    if (array[i] == value) {
                        age[i] = 0;
                    }
                }
            }
        }
        return faults;
    }
    //method for OPTIMAL page replacement algorithm
    static int optimal(String reference_string, int frames) {
        int faults = 0;
        int count = 0;
        int[] array = new int[frames];

        //fill frames with -1
        for (int i = 0; i < frames; i++) {			
            array[i] = -1;							
        }											

        //String tokenizer class to break a string into tokens
        StringTokenizer tok = new StringTokenizer(reference_string);
        //loop up to the end
        while (tok.hasMoreTokens()) {
            int value = Integer.parseInt(tok.nextToken());
            count++;
            //check if exist in frame already 
            if (!(isInFrames(value, frames, array))) {
                faults++;

                String rest = reference_string + "";

                int idx = findOptimal(rest, frames, count, array);

                array[idx] = value;
            }
        }
        return faults;
    }

    // method to to check if value already exist in the frame 
    static boolean isInFrames(int value, int frames, int[] array) {

        for (int i = 0; i < frames; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
    
    // method to find the max value 
    static int findMax(int[] arr) {
        int idx = 0, maxValue = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
                idx = i;
            }
        }
        return idx;
    }

    static int findOptimal(String rest, int frames, int count, int[] array) {
        int[] nxt = new int[frames];
        int val;

        for (int i = 0; i < frames; i++) {
            nxt[i] = 10000;
        }

        for (int i = 0; i < frames; i++) {
            int count2 = 0;
            StringTokenizer tok = new StringTokenizer(rest);

            for (int j = 0; j < count; j++) {
                tok.nextToken();
            }

            while (tok.hasMoreTokens()) {
                val = Integer.parseInt(tok.nextToken());

                if (val == array[i]) {
                    nxt[i] = count2;
                    break;
                }
                count2++;
            }
        }

        int idx = findMax(nxt);

        return idx;
    }
    
    //method to generate random reference string with the given limit
    static String generateReferenceString( int limit) 
    {
        String reference_string = ""; 
        for(int i = 0; i < limit; i++)
        {
            reference_string += " " + getRandNumber();
        }
        return  reference_string;
    }
    
    //method to generate a random values between 0-9
    static int getRandNumber()
    {
        int min = 0; int max = 9;
        Random rand = new Random();
        //max is the maximum and the min is our minimum which is 9 and 0
        return rand.nextInt(max) + min;  
        
    }
    
    
    //main method
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        String reference_string, output_filename;
        output_filename = "output.txt";
        int faults, frame, page_frames;
        page_frames = 7;
        
        PrintWriter writer = new PrintWriter(output_filename);
        
        writer.println("\n===============================================================\n");
        writer.println("\n====================== With Random string =====================\n");
        writer.println("\n===============================================================\n");
        
        for(frame = 1; frame <= page_frames; frame++)
        {
            reference_string = generateReferenceString(20);
            writer.println("\nFor " + frame + " page frames, and using string page reference string ["+ reference_string +" ]");
            //call FIFO page replacement algorithms
            faults = fifo(reference_string, frame);
            writer.println("\n\tFIFO had " + faults +  " page faults.");
            
            //call LRU page replacement algorithms
            faults = lru(reference_string, frame);
            writer.println("\n\tLRU had " + faults +  " page faults.");
            
            //call OPTIMAL page replacement algorithms
            faults = optimal(reference_string, frame);
            writer.println("\n\tOptimal had " + faults +  " page faults.");
        }
        //do the same procedure except use the following page-reference string instead of the random one
        reference_string = "0 7 0 1 2 0 8 9 0 3 0 4 5 6 7 0 8 9 1 2";  
        writer.println("\n===============================================================\n");
        writer.println("\nReference String[ " + reference_string + "] \n");
        writer.println("\n===============================================================\n");
        for(frame = 1; frame <= page_frames; frame++)
        {
            writer.println("\nFor " + frame + " page frames, and using string page reference string ["+ reference_string +" ]");
            //call FIFO page replacement algorithms
            faults = fifo(reference_string, frame);
            writer.println("\n\tFIFO had " + faults +  " page faults.");
            
            //call LRU page replacement algorithms
            faults = lru(reference_string, frame);
            writer.println("\n\tLRU had " + faults +  " page faults.");
            
            //call OPTIMAL page replacement algorithms
            faults = optimal(reference_string, frame);
            writer.println("\n\tOptimal had " + faults +  " page faults.");
        }
        //lastly do it with the page-reference string: 7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1
        reference_string = "7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1";
        writer.println("\n===============================================================\n");
        writer.println("\nReference String[ " + reference_string + "] \n");
        writer.println("\n===============================================================\n");
        for(frame = 1; frame <= page_frames; frame++)
        {
            writer.println("\nFor " + frame + " page frames, and using string page reference string ["+ reference_string +" ]"); 
            //call FIFO page replacement algorithms
            faults = fifo(reference_string, frame);
            writer.println("\n\tFIFO had " + faults +  " page faults.");

            //call LRU page replacement algorithms
            faults = lru(reference_string, frame);
            writer.println("\n\tLRU had " + faults +  " page faults.");
            
            //call OPTIMAL page replacement algorithms
            faults = optimal(reference_string, 3);
            writer.println("\n\tOptimal had " + faults +  " page faults.");
            
        }
        
        System.out.println("Please check " + output_filename + " file to see the output");
        writer.close();//close file writting 
    }
}
