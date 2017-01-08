package model;

public class Employee {

    private int codeEmployee;
    private String fio;
    private int codeCex;
    private String position;
    private String employmentDate;

    public int getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(int codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getCodeCex() {
        return codeCex;
    }

    public void setCodeCex(int codeCex) {
        this.codeCex = codeCex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }
}
