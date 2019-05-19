package serializable;

import java.io.Serializable;

public class Student extends Person implements Serializable {
  private float score;
  private int grade;

  public Student(float score, int grade) {
    super("siege", 25, "male");
    System.out.println("Student子类构造器," + "score:" + score + ",grade:" + grade);
    this.score = score;
    this.grade = grade;
  }

  public float getScore() {
    return score;
  }

  public int getGrade() {
    return grade;
  }
}
