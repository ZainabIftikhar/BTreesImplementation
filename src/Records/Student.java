package Records;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public final class Student {
    private final int RollNo;
    String Name;
    Vector CTaken;

    public Student(String record) {
        CTaken = new Vector(50);
        String[] tokens = record.split(",");
        RollNo = Integer.valueOf(tokens[0]);
        Name = tokens[1];
        for (int i = 2; i < tokens.length; i++){
            String[] tokens2 = tokens[i].split(" ");
            addCourse(tokens2[0], tokens2[1]);
        }
    }
    
    private class CourseTaken{
        String course;
        String grade;

        public CourseTaken(String course, String grade) {
            this.course = course;
            this.grade = grade;
        }
    }
    
    public Student(int RollNo, String name){
        this.RollNo = RollNo;
        this.Name = name;
        CTaken = new Vector(50);
    }
    
    public void addCourse(String course, String grade){
        CTaken.add(new CourseTaken(course,grade));
    }
    
    public void printRecord(){
        System.out.println("Student Record");
        System.out.println("Roll No : " + RollNo);
        System.out.println("Name : " + Name);
        for (int i=0; i < CTaken.size(); i++)
            System.out.println("Course Taken : " + ((CourseTaken)CTaken.get(i)).course + " Grade : " + ((CourseTaken)CTaken.get(i)).grade);
        
    }
    
    public int getRollNo(){
        return RollNo;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
    
    public void setGrade(String Course,String Grade){
        for (int i = 0; i < CTaken.size(); i++){
            if (((CourseTaken)CTaken.get(i)).course.equals(Course))
                ((CourseTaken)CTaken.get(i)).grade = Grade;
        }
    }
    
    public void setCourse(String oldCourse,String newCourse){
        for (int i = 0; i < CTaken.size(); i++){
            if (((CourseTaken)CTaken.get(i)).course.equals(oldCourse))
                ((CourseTaken)CTaken.get(i)).course = newCourse;
        }
    }
    
    public void saveRecords(BufferedWriter bw) throws IOException{
        bw.append(String.valueOf(RollNo));
        bw.append("," + Name);
        
        for (int i=0; i < CTaken.size(); i++)
            bw.append("," + ((CourseTaken)CTaken.get(i)).course + " " + ((CourseTaken)CTaken.get(i)).grade);
        bw.newLine();
    }
}
