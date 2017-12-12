/*
 * Date: 11/20/2017
 * Description: A program that implements disk scheduling  replacement algorithms FIFO, LRU and Optimal
 */
package diskschedulingalgorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiskSchedulingTest {
    
    //define private variables for the program
    private final int[] referenceString;
    private final int headStart;
    private final int maxNumCylinders;
    private int totalHeadMovement;

    //difine final private variable for input/output files
    private static final String INPUT_FILENAME = "Asg5Data.txt";    
    private static final String OUTPUT_FILENAME = "output_file.txt";

    //constractor to initialize values 
    public DiskSchedulingTest(int max, int[] rString, int head)
    {
            this.referenceString = new int[rString.length];
            System.arraycopy(rString, 0, referenceString,0, rString.length);
            this.headStart = head;
            this.maxNumCylinders = max;
            this.totalHeadMovement = 0;
    }
    
    /* 
    * Method for FCFS disk scheduling algorithm
    * returns the total head movment for FCFS algorithm
    */
    public int FCFS()
    {
        totalHeadMovement += Math.abs(referenceString[0] - headStart);
        for(int i=1;i<referenceString.length;i++)
        {
            totalHeadMovement += Math.abs(referenceString[i] - referenceString[i-1]);
        }
        return totalHeadMovement;
    }
    
    /* 
    * Method for SSTF disk scheduling algorithm
    * returns the total head movment for SSTF algorithm
    */
    public int SSTF()
    {
        int pos;
        int currentPos = this.headStart;
        totalHeadMovement = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<this.referenceString.length; i++){
            list.add(this.referenceString[i]);
        }
        for(int i=0; i<this.referenceString.length; i++){
            pos = this.findNearestPos(list, currentPos);
            totalHeadMovement += Math.abs(currentPos-pos);
            currentPos = pos;
        }
        return totalHeadMovement;
    }
    
    /* 
    * Method to find the nearst element for SSTF
    * returns the smallest array value 
    */
    private int findNearestPos(ArrayList<Integer> list, int currentPos)
    {
        int minDistance = Math.abs(currentPos-list.get(0));
        int distance;
        int pos = 0;
        int counter = 0;
        for(int i : list){
            distance = Math.abs(currentPos-i);
            if(distance<minDistance){
                minDistance = distance;
                pos = counter;
            }
            counter++;
        }
        int ret = list.get(pos);
        list.remove(pos);
        return ret;
    }
    
    /* 
    * Method for SCAN disk scheduling algorithm
    * returns the total head movment for SCAN algorithm
    */
    public int SCAN() 
    {
        totalHeadMovement = 0; //reset the counter to zero
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.referenceString.length; i++) {
            list.add(this.referenceString[i]);
        }
        int direction = -1;
        int currentPos = this.headStart; //set the currentPos to headStart 
         //loop through the list array to get all the values 
        while (!list.isEmpty()) 
        {
            currentPos += direction;
            totalHeadMovement++;
            if (list.contains(currentPos)) {
                list.remove(new Integer(currentPos));
            }
            if (currentPos == 0 || currentPos == this.maxNumCylinders) 
            {
                direction = -direction;
            }
        }
        return totalHeadMovement;
    }
    
    /* 
    * Method for LOOK disk scheduling algorithm
    * returns the total head movment for LOOK algorithm
    */
    public int LOOK() {
        int max = this.max(this.referenceString);
        int min = this.min(this.referenceString);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.referenceString.length; i++) {
            list.add(this.referenceString[i]);
        }

        int direction = -1;
        int currentPos = this.headStart;
        int sum = 0;
        //loop through the list array to get all the values 
        while (!list.isEmpty()) {
            currentPos += direction;
            sum++;
            if (list.contains(currentPos)) {
                list.remove(new Integer(currentPos));
            }
            if (currentPos == min || currentPos == max) {
                direction = -direction;
            }
        }
        return sum;
    }
    
    /* 
    * Method to get the smallest array element value
    * returns the smallest array value 
    */
    public int min(int[] array) {
        int min = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    /* 
    * Method to get the biggest array element value
    * returns the biggest array value 
    */
    public int max(int[] array) 
    {
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
    
    /* 
    * Method for CSCAN disk scheduling algorithm
    * returns the total head movment for CSCAN algorithm
    */
    public int CSCAN()
    {
        ArrayList<Integer> list = new ArrayList<>();
        int[] distance = new int[this.maxNumCylinders];
        int[] distance2 = new int[this.maxNumCylinders];
        for(int i=0; i<this.referenceString.length; i++){
            if(this.referenceString[i] != 0)
                list.add(this.referenceString[i]);
        }
        list.add(0, 0);
        list.add(1, this.headStart);
        list.add(list.size()-1, this.maxNumCylinders -1);
        int headMovement = 0, counter = 0;
        
        Collections.sort(list);
        while(!list.isEmpty())
        {
            if(list.contains(counter))
            {
                distance[counter] = counter;
                list.remove(new Integer(counter));
            }
            if(counter == this.maxNumCylinders)
                break;
            counter += 1;
        }
        counter = 0;
        for(int i = this.headStart; i > 0 ; i--)
        {
            if(distance[i] !=0 )
            {
                distance2[counter] = distance[i];
                counter++;
            }
        }
        
        for(int i = 0; i < counter ; i++)
        {
            int a = (distance2[i] - distance2[i + 1]);
            headMovement += (distance2[i] - distance2[i + 1]);
            System.out.println(distance2[i] + "-" + distance2[i + 1] + "=" +a);
        }
           
        
        counter = 0;
        for(int i= this.maxNumCylinders - 1 ; i > this.headStart ; i--)
        {
            if(distance[i] != 0)
            {
                distance2[counter] = distance[i];
                counter++;
            }
        }
        
        for(int i = 0; i < counter - 1 ; i++)
            headMovement += (distance2[i] - distance2[i + 1]);
        
        headMovement += this.maxNumCylinders - 1;
        return headMovement;
    }
    
    /* 
    * Method for CLOOK disk scheduling algorithm
    * returns the total head movment for CLOOK algorithm
    */
    public int CLOOK()
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<this.referenceString.length; i++){
            list.add(this.referenceString[i]);
        }
        int headMovement = 0;
        int currentPos, operand;
        int counter = 0;
        int[] distance = new int[this.maxNumCylinders];
        int[] distance2 = new int[this.maxNumCylinders];
        
        currentPos = this.headStart;
        list.add(0, currentPos);
        Collections.sort(list);
        
        while(!list.isEmpty())
        {
            if(list.contains(counter))
            {
                distance[counter] = counter;
                list.remove(new Integer(counter));
            }
            if(counter == this.maxNumCylinders)
                break;
            counter += 1;
            currentPos += 1;
        }
        counter = 0; //reset the counter
        for(int i= this.headStart; i > 0; i--)
        {
            
            if(distance[i] != 0)
            {
                distance2[counter] = distance[i];
                counter++;
            }
               
        }
        for(int i = 0; i < counter; i++)
        {
            if(distance2[i + 1] != 0)
            {
                headMovement += (distance2[i] - distance2[i + 1]);
            }
        }
        counter = 0;
        for(int i= this.headStart; i < this.maxNumCylinders; i++)
        {
            if(distance[i] != 0)
            {
                distance2[counter] = distance[i];
                counter++;
            }
               
        }
        for(int i = 0; i < counter; i++)
        {
            if(distance2[i] != this.headStart)
            {
                if(distance2[i + 1] == 0)
                {
                    operand = min(this.referenceString);
                }
                else
                {
                    operand = distance2[i + 1];
                }
                headMovement += Math.abs(operand - distance2[i]);
                
            }
        }
        return headMovement;
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numCylinders, headStart;
        String line;
        String [] splitedLine;
        List<String> numberOfCylinders = new ArrayList<>();
        List<String> cylinderValues = new ArrayList<>();
        List<String> headStartValues = new ArrayList<>();

        BufferedReader bReader;

        try {
            
            //define file read and write object
            bReader = new BufferedReader(new FileReader(INPUT_FILENAME));
            PrintWriter writer = new PrintWriter(OUTPUT_FILENAME);
            int position = 0;
            while ((line = bReader.readLine()) != null) 
            {
                switch (position) 
                {
                    case 0:
                        numberOfCylinders.add(line);
                        break;
                    case 1:
                        headStartValues.add(line);
                        break;
                    case 2:
                        cylinderValues.add(line);
                        break;
                } // end of switch
                position++;
                if(position == 3)
                    position = 0; // reset the position
            } // end of while loop
            
            for (int i = 0; i < numberOfCylinders.size(); i++) 
            {
                //get the first line as numCylinders value
                numCylinders = Integer.parseInt(numberOfCylinders.get(i));
                //get the second line as headStart value
                headStart = Integer.parseInt(headStartValues.get(i));
                //get the third line as string reference value and split by space to get all the value to array
                splitedLine = cylinderValues.get(i).split("\\s+");
                int cylinders[] = new int[splitedLine.length];
                writer.print("String being used: [ ");
                for (int j = 0; j < splitedLine.length; j++) 
                {
                    cylinders[j] = Integer.parseInt(splitedLine[j]);
                    writer.print(cylinders[j] + " ");
                }
                writer.print("]\n");
                //inistantiate the class with all required values 
                DiskSchedulingTest stdClass = new DiskSchedulingTest(numCylinders, cylinders, headStart);
                writer.println("For FCFS, the total head movement was " + stdClass.FCFS() + " cylinders.");
                writer.println("For SSTF, the total head movement was " + stdClass.SSTF() + " cylinders.");
                writer.println("For SCAN, the total head movement was " + stdClass.SCAN() + " cylinders.");
                writer.println("For CSCAN, the total head movement was " + stdClass.CSCAN() + " cylinders.");
                writer.println("For LOOK, the total head movement was " + stdClass.LOOK() + " cylinders.");
                writer.println("For CLOOK, the total head movement was " + stdClass.CLOOK() + " cylinders.");
                writer.println("\n");
                
            }
            System.out.println("Please check " + OUTPUT_FILENAME + " file to see the output");
            writer.close();

        } 
        catch (IOException | NumberFormatException e) {
            System.out.print("Error" + e.getMessage());
        }
    }

}
