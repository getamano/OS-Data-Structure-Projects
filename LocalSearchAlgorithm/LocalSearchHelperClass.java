/*
 * Local Search Algorithm (LSA)
 * Application for allocating courses to Semester using LSA
 * 
 * A helper class for the project 
 *
 */
package localsearchalgorithmproject;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

public class LocalSearchHelperClass 
{
    //method to compare courses 
    public static CourseComparator getCourseComparator() 
    {
        return new CourseComparator();
    }

    //method to show out put in summary format
    void showSummary(List<Semester> semesterList, PrintWriter writer)  
    {
        semesterList.forEach(semester -> {
            System.out.print(semester.getSemesterSeason() + ": ");
            writer.print(semester.getSemesterSeason() + ": ");
            printCourses(semester, writer);
        });
    }

    private void printCourses(Semester semester, PrintWriter writer) 
    {
        semester.getCoursers().forEach(course -> {System.out.print(course.getSubjectCode() + ", "); writer.print(course.getSubjectCode() + ", ");} );
        writer.println(" ");
        System.out.println(" ");
    }
    
    
    private void printCoursesList(Semester semester) 
    {
        semester.getCoursers().forEach(course -> System.out.print(course.getSubjectCode() + " "));
    }

    //method to show out put in verbose format
    void showVerbose(List<Semester> semesterCourses, PrintWriter writer) 
    {
        semesterCourses.forEach(semester -> {
            semester.getCoursers().forEach(course -> {System.out.print(course.getSubjectCode() + " "); writer.print(course.getSubjectCode() + " ");});
            
        });
        writer.print("\n-------------------------------------------------------------------------------\n");
        System.out.print("\n-------------------------------------------------------------------------------\n");
        
        for(int i = 0; i< semesterCourses.size(); i++)
        {
            semesterCourses.forEach(semester -> {
                semester.getCoursers().forEach(course -> {System.out.print(semester.getSemesterNumber() + "   " ); writer.print(semester.getSemesterNumber() + "   " );});
            });
            writer.print("\n");
            System.out.print("\n");
        }
    }

    public List<Semester> allocateCoursesToSemester(List<Semester> semesters, List<Course> courses) 
    {
        //sort the list
        courses.sort(getCourseComparator());
        semesters.forEach((semester) -> {
            //allocate the courses
            List<Course> copyList = new ArrayList<>();
            copyList.addAll(courses);
            boolean flag = true;
            for (Course course : copyList) 
            {
                //check if semester is full
                if (!semester.isFull()) 
                {
                    if (course.getSubjectCode() == 499) 
                    {
                        Optional<Semester> optional = semesters.stream().filter(semester1 ->
                                (semester1.getSemesterSeason()==SemesterSeason.SUMMER && !semester1.isFull())).findAny();
                        if(optional.isPresent())
                        {
                            flag =false;
                            //course.setSemester(semester.getSemesterNumber());
                            optional.get().getCoursers().add(course);
                            courses.remove(course);
                        }
                    }
                    if (course.getSubjectCode() == 492 &
                            (semester.getSemesterSeason() == SemesterSeason.AUTUMN || semester.getSemesterSeason() == SemesterSeason.SPRING)) 
                    {
                        //course.setSemester(semester.getSemesterNumber());
                        semester.getCoursers().add(course);
                        courses.remove(course);
                    }
                    if(flag) 
                    {
                        semester.getCoursers().add(course);
                        courses.remove(course);
                    }
                }
            }
        });
        return semesters;
    }

    void showAllCourses() 
    {
        getListOfCourses().forEach(
                course -> {
                    System.out.print(course.getCourse() + ": " + course.getSubjectCode() + "  ");
                }
        );
        System.out.println("\n\n");
    }

    /**
     * Course comparator for sorting, compares the subject code and returns -1 if course subject code is less than the next course
     * returns 0 if the course is not less than the next course
     */
    private static class CourseComparator implements Comparator<Course>, Serializable 
    {
        @Override
        public int compare(Course course1, Course course2) 
        {
            //comparing each courses with id
            if (course1.getSubjectCode() < course2.getSubjectCode()) {
                return -1;
            }
            if (course1.getSubjectCode() == course2.getSubjectCode()) {
                return 0;
            }
            return 1;
        }
    }


    /**
     * gets all the list of courses that will be taken for CS degree
     * @return course list 
     */
    public List<Course> getListOfCourses() 
    {
        List<Course> courseList = new ArrayList<>();

        Course math120 = new Course();
        math120.setCourse(CourseType.MATH);
        math120.setSubjectCode(120);
        courseList.add(math120);

        Course math210 = new Course();
        math210.setCourse(CourseType.MATH);
        math210.setSubjectCode(210);
        courseList.add(math210);

        Course math215 = new Course();
        math215.setCourse(CourseType.MATH);
        math215.setSubjectCode(215);
        courseList.add(math215);

        Course ics140 = new Course();
        ics140.setSubjectCode(140);
        ics140.setCourse(CourseType.ICS);
        courseList.add(ics140);

        Course ics141 = new Course();
        ics141.setSubjectCode(141);
        ics141.setCourse(CourseType.ICS);
        courseList.add(ics141);

        Course ics232 = new Course();
        ics232.setSubjectCode(232);
        ics232.setCourse(CourseType.ICS);
        courseList.add(ics232);

        Course ics240 = new Course();
        ics240.setSubjectCode(240);
        ics240.setCourse(CourseType.ICS);
        courseList.add(ics240);

        Course ics311 = new Course();
        ics311.setSubjectCode(311);
        ics311.setCourse(CourseType.ICS);
        courseList.add(ics311);

        Course ics340 = new Course();
        ics340.setSubjectCode(340);
        ics340.setCourse(CourseType.ICS);
        courseList.add(ics340);

        Course ics365 = new Course();
        ics365.setSubjectCode(365);
        ics365.setCourse(CourseType.ICS);
        courseList.add(ics365);

        Course ics372 = new Course();
        ics372.setSubjectCode(372);
        ics372.setCourse(CourseType.ICS);
        courseList.add(ics372);

        Course ics440 = new Course();
        ics440.setSubjectCode(440);
        ics440.setCourse(CourseType.ICS);
        courseList.add(ics440);

        Course ics460 = new Course();
        ics460.setSubjectCode(460);
        ics460.setCourse(CourseType.ICS);
        courseList.add(ics460);

        Course ics462 = new Course();
        ics462.setSubjectCode(462);
        ics462.setCourse(CourseType.ICS);
        courseList.add(ics462);

        Course ics490 = new Course();
        ics490.setSubjectCode(490);
        ics490.setCourse(CourseType.ICS);
        courseList.add(ics490);

        Course ics492 = new Course();
        ics492.setSubjectCode(492);
        ics492.setCourse(CourseType.ICS);
        courseList.add(ics492);

        Course ics499 = new Course();
        ics499.setSubjectCode(499);
        ics499.setCourse(CourseType.ICS);
        courseList.add(ics499);

        Course liberal998 = new Course();
        liberal998.setCourse(CourseType.LIBERAl_STUDY);
        liberal998.setSubjectCode(998);
        courseList.add(liberal998);

        Course liberal999 = new Course();
        liberal999.setCourse(CourseType.LIBERAl_STUDY);
        liberal999.setSubjectCode(999);
        courseList.add(liberal999);

        return courseList;
    }

    /**
     * @return {@link List} {@link Semester}
     */
    public List<Semester> getSemesterList() 
    {
        List<Semester> semesters = new ArrayList<>();
        Semester semester1 = new Semester();
        semester1.setSemesterSeason(SemesterSeason.SUMMER);
        semester1.setSemesterNumber(getRand());

        Semester semester2 = new Semester();
        semester2.setSemesterSeason(SemesterSeason.AUTUMN);
        semester2.setSemesterNumber(getRand());

        Semester semester3 = new Semester();
        semester3.setSemesterSeason(SemesterSeason.SPRING);
        semester3.setSemesterNumber(getRand());

        Semester semester4 = new Semester();
        semester4.setSemesterNumber(getRand());
        semester4.setSemesterSeason(SemesterSeason.SUMMER);

        Semester semester5 = new Semester();
        semester5.setSemesterSeason(SemesterSeason.AUTUMN);
        semester5.setSemesterNumber(getRand());

        Semester semester6 = new Semester();
        semester6.setSemesterSeason(SemesterSeason.SPRING);
        semester6.setSemesterNumber(getRand());

        Semester semester7 = new Semester();
        semester7.setSemesterSeason(SemesterSeason.SUMMER);
        semester7.setSemesterNumber(getRand());

        semesters.add(semester1);
        semesters.add(semester2);
        semesters.add(semester3);
        semesters.add(semester4);
        semesters.add(semester5);
        semesters.add(semester6);
        semesters.add(semester7);

        return semesters;
    }
    
    //get random number b/n 1-7
    public int getRand()
    {
        int min = 1;        
        int max = 7;
        int randomNum;
        Random rn = new Random();
        int range = max - min + 1;
        randomNum =  rn.nextInt(range) + min;
        return randomNum;

    }
    
}
