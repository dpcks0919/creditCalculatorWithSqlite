public class Person {
    private int num;
    private String name;
    private int korScore;
    private int engScore;
    private int mathScore;
    private String grade;
    private String regDate;

    public Person() {

    }

    public Person(int num, String name, int korScore, int engScore, int mathScore, String grade, String regDate) {
        this.num = num;
        this.name = name;
        this.korScore = korScore;
        this.engScore = engScore;
        this.mathScore = mathScore;
        this.grade = grade;
        this.regDate = regDate;
    }

    private int sum(){
        return this.korScore + this.engScore + this.mathScore;
    }

    @Override
    public String toString() {
        return this.num+1 + ". " + this.name + " " +  this.korScore + "  " + this.engScore + "  " + this.mathScore + "   " + sum() + "  " + sum()/3 + "   "  + this.grade + "     "  +this.regDate ;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorScore() {
        return korScore;
    }

    public void setKorScore(int korScore) {
        this.korScore = korScore;
    }

    public int getEngScore() {
        return engScore;
    }

    public void setEngScore(int engScore) {
        this.engScore = engScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}