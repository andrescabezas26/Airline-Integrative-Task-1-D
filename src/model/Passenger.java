package model;

public class Passenger {

    private String name;
    private String id;
    private int row;
    private String chair;
    private boolean firstClass;
    private boolean pregnant;
    private boolean oldAge;

    public Passenger(String name, String id, int row, String chair, boolean firstClass, boolean pregnant,
            boolean oldAge) {
        this.name = name;
        this.id = id;
        this.row = row;
        this.chair = chair;
        this.firstClass = firstClass;
        this.pregnant = pregnant;
        this.oldAge = oldAge;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getChair() {
        return this.chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public boolean isFirstClass() {
        return this.firstClass;
    }

    public boolean getFirstClass() {
        return this.firstClass;
    }

    public void setFirstClass(boolean firstClass) {
        this.firstClass = firstClass;
    }

    public boolean isPregnant() {
        return this.pregnant;
    }

    public boolean getPregnant() {
        return this.pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isOldAge() {
        return this.oldAge;
    }

    public boolean getOldAge() {
        return this.oldAge;
    }

    public void setOldAge(boolean oldAge) {
        this.oldAge = oldAge;
    }

}