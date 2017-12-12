/*
 * Local Search Algorithm (LSA)
 * Application for allocating courses to Semester using LSA
 *  
 * A Test class for the application
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class LocalSearchAlgorithmProjectTest 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
    {
        
        String menu;
        LocalSearchHelperClass localSearchUtility = new LocalSearchHelperClass();
        //write outout to file as well
        String OUTPUT_FILE_NAME = "output_file.txt";
        PrintWriter writer = new PrintWriter(OUTPUT_FILE_NAME);
        writer.println("Local Search Algorithm");
        System.out.println("Local Search Algorithm");
        writer.println("Application for allocating courses to Semester using Local search Algorithm");
        System.out.println("Application for allocating courses to Semester using Local search Algorithm");
        writer.println("Courses: ");
        
        System.out.println("Courses: ");
        //show all possible courses
        localSearchUtility.showAllCourses();
      
        //Scanner object to acceprt user input 
        Scanner scanner = new Scanner(System.in);
        List<Semester> updatedSemesterList = null;
        
        //show menu until e - exit option is entered
        do
        {
            System.out.println("=== MENU ===");
            System.out.println("s. Summary");
            System.out.println("v. Verbose");        
            System.out.println("e. Exit\n");
            menu = scanner.nextLine().toLowerCase();// conver input to lower case
            switch (menu) 
            {
                case "s":
                    //Allocate course to semester 
                    updatedSemesterList = localSearchUtility.allocateCoursesToSemester(localSearchUtility.getSemesterList(),
                                    localSearchUtility.getListOfCourses());
                    writer.print("====Summary===\n");
                    localSearchUtility.showSummary(updatedSemesterList, writer);
                    writer.close();
                    break;
                case "v":
                    writer.print("====Verbose===\n");
                    //Allocate course to semester 
                    updatedSemesterList = localSearchUtility.allocateCoursesToSemester(localSearchUtility.getSemesterList(),
                                    localSearchUtility.getListOfCourses());
                    localSearchUtility.showVerbose(updatedSemesterList, writer);
                    writer.close();
                    break;
                case "e":
                    break;
                default:
                    //show valid options to the user
                    System.out.println("Choose s, v, e");
                    break;
            }
        }while(!"e".equals(menu)); //exit if e entered
        
        
    }
}
