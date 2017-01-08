package model;

import java.util.Date;

public class Incident {

    private int codeIncident;
    private String description;
    private int codeStation;
    private String detectionDate;
    private String closingPeriod;
    private String closingDate;
    private int foundEmployee;
    private int closeEmployee;

    private Stations station;
    private Employee employeeFound;
    private Employee employeeClose;

    public void setCodeIncident(int codeIncident) {
        this.codeIncident = codeIncident;
    }

    public int getCodeIncident() {
        return codeIncident;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCodeStation(int codeStation) {
        this.codeStation = codeStation;
    }

    public int getCodeStation() {
        return codeStation;
    }

    public void setDetectionDate(String detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getDetectionDate() {
        return detectionDate;
    }

    public void setClosingPeriod(String closingPeriod) {
        this.closingPeriod = closingPeriod;
    }

    public String getClosingPeriod() {
        return closingPeriod;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setFoundEmployee(int foundEmployee) {
        this.foundEmployee = foundEmployee;
    }

    public int getFoundEmployee() {
        return foundEmployee;
    }

    public void setCloseEmployee(int closeEmployee) {
        this.closeEmployee = closeEmployee;
    }

    public int getCloseEmployee() {
        return closeEmployee;
    }

    public Stations getStation() {
        return station;
    }

    public void setStation(Stations station) {
        this.station = station;
    }

    public Employee getEmployeeFound() {
        return employeeFound;
    }

    public void setEmployeeFound(Employee employeeFound) {
        this.employeeFound = employeeFound;
    }

    public Employee getEmployeeClose() {
        return employeeClose;
    }

    public void setEmployeeClose(Employee employeeClose) {
        this.employeeClose = employeeClose;
    }

}
