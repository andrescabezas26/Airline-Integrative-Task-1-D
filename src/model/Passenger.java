package model;

public class Passenger {

    private String name;
    private String id;
    private int row;
    private String chair;
    private boolean firstClass;
    private boolean pregnant;
    private boolean oldAge;
    private int accumulatedMiles;
    private int priorityBoarding;
    private int priorityDisembarking;

    public Passenger(String id, String name, int row, String chair, boolean firstClass, boolean pregnant,
            boolean oldAge, int accumulatedMiles) {
        this.name = name;
        this.id = id;
        this.row = row;
        this.chair = chair;
        this.firstClass = firstClass;
        this.pregnant = pregnant;
        this.oldAge = oldAge;
        this.accumulatedMiles = accumulatedMiles;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + "\nRow: " + row + "\nChair: " + chair + "\nFirstClass: " + firstClass
                + "\nPregnant: " + pregnant + "\nOldAge: " + oldAge + "\nAccumulatedMilles: " + accumulatedMiles + "\nPriorityBoarding: " + priorityBoarding;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return int return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return String return the chair
     */
    public String getChair() {
        return chair;
    }

    /**
     * @param chair the chair to set
     */
    public void setChair(String chair) {
        this.chair = chair;
    }

    /**
     * @return boolean return the firstClass
     */
    public boolean isFirstClass() {
        return firstClass;
    }

    /**
     * @param firstClass the firstClass to set
     */
    public void setFirstClass(boolean firstClass) {
        this.firstClass = firstClass;
    }

    /**
     * @return boolean return the pregnant
     */
    public boolean isPregnant() {
        return pregnant;
    }

    /**
     * @param pregnant the pregnant to set
     */
    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    /**
     * @return boolean return the oldAge
     */
    public boolean isOldAge() {
        return oldAge;
    }

    /**
     * @param oldAge the oldAge to set
     */
    public void setOldAge(boolean oldAge) {
        this.oldAge = oldAge;
    }

    /**
     * @return int return the priorityBoarding
     */
    public int getPriorityBoarding() {
        return priorityBoarding;
    }

    /**
     * @param priorityBoarding the priorityBoarding to set
     */
    public void setPriorityBoarding(int priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }


    /**
     * @return int return the priorityDisembarking
     */
    public int getPriorityDisembarking() {
        return priorityDisembarking;
    }

    /**
     * @param priorityDisembarking the priorityDisembarking to set
     */
    public void setPriorityDisembarking(int priorityDisembarking) {
        this.priorityDisembarking = priorityDisembarking;
    }

    /**
     * @return int return the accumulatedMiles
     */
    public int getAccumulatedMiles() {
        return accumulatedMiles;
    }

    /**
     * @param accumulatedMiles the accumulatedMiles to set
     */
    public void setAccumulatedMiles(int accumulatedMiles) {
        this.accumulatedMiles = accumulatedMiles;
    }

}