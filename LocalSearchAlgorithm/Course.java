/*
 * Local Search Algorithm (LSA)
 * Application for allocating courses to Semester using LSA
 * 
 * A Course class for the project. Course information goes here
 *
 */
package localsearchalgorithmproject;

public class Course 
{
    private CourseType course;
    private int subjectCode;
    private int semester;

    public CourseType getCourse() {
        return course;
    }

    public void setCourse(CourseType course) {
        this.course = course;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) 
    {
        this.subjectCode = subjectCode;
    }

    public int getSemester() 
    {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
