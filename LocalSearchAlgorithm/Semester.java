/*
 * Local Search Algorithm (LSA)
 * Application for allocating courses to Semester using LSA
 * 
 * A Semester class for the project. All Semester information goes here
 *
 */
package localsearchalgorithmproject;

import java.util.ArrayList;
import java.util.List;

public class Semester 
{
    private SemesterSeason semesterSeason;
    private int semesterNumber;
    private List<Course> coursers = new ArrayList<>();

    public SemesterSeason getSemesterSeason() {
        return semesterSeason;
    }

    public void setSemesterSeason(SemesterSeason semesterSeason) {
        this.semesterSeason = semesterSeason;
    }

    public int getSemesterNumber() 
    {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) 
    {
        this.semesterNumber = semesterNumber;
    }

    public List<Course> getCoursers() 
    {
        return coursers;
    }

    public void setCoursers(List<Course> coursers) 
    {
        this.coursers = coursers;
    }

    public boolean isFull() {
        return this.getCoursers().size()==3;
    }
}
