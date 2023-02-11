package com.code_help.kryp;

public class KryptinLab {

    public static void main(String[] args) {
        ArrayBasedPD arrayBasedPD = new ArrayBasedPD();
        System.out.println(arrayBasedPD.find("M"));
        System.out.println(arrayBasedPD.find("Ma"));
        System.out.println(arrayBasedPD.find("Maa"));
        System.out.println(arrayBasedPD.find("Maaa"));
        System.out.println(arrayBasedPD.find("Maaaaa"));
        System.out.println(arrayBasedPD.find("A"));
        System.out.println(arrayBasedPD.find("Ass"));
        System.out.println(arrayBasedPD.find("Ba"));
        System.out.println(arrayBasedPD.find("Bad"));

    }
}

class  ArrayBasedPD {
    DirectoryEntry[] theDirectory = new DirectoryEntry[100];

    public ArrayBasedPD() {
        theDirectory[0] = new DirectoryEntry("Ass", "2");
        theDirectory[1] = new DirectoryEntry("Ass", "2");
        theDirectory[2] = new DirectoryEntry("Ass", "2");
        theDirectory[3] = new DirectoryEntry("Ass", "2");
        theDirectory[4] = new DirectoryEntry("Ba", "2");
        theDirectory[5] = new DirectoryEntry("Bad", "2");
        theDirectory[6] = new DirectoryEntry("Ma", "1");
        theDirectory[7] = new DirectoryEntry("Maaa", "2");
    }
    int size = 8;
    protected  boolean found(int index, String name) {
        return index < size && theDirectory[index].getName().equals(name);
    }

    protected  int find(String name) {
        int first = 0;
        int last = size-1;
        while (first <= last) {
            int mid = first + ((last-first) / 2);
            if (theDirectory[mid].getName().compareTo(name) < 0) {
                first = mid + 1;
            } else if (theDirectory[mid].getName().compareTo(name) > 0) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return size;
    }
}

class DirectoryEntry {
    /**
     * The name of the indiviual represented in the directory.
     */
    private String name;

    /**
     * The home number for this individual.
     */
    private String number;

    /**
     * Creates a new instance of DirectoryEntry
     *
     * @param name   Name of Person
     * @param number Phone number of Person
     */
    public DirectoryEntry(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Retrieves the name
     *
     * @return the name of the individual
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number
     *
     * @return the number of the individual
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number to a different value.
     *
     * @param number the new number to set it to
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
