package com.code_help.kryp;

/**
 * 1. To try it out, download the contents of dist (prog02.jar and
 * csc220.txt) to your desktop (or home directory).  Run
 * <p>
 * java -jar prog02.jar
 * <p>
 * or double click on it.
 * <p>
 * Look up Collin, Victor, and your own name (which won't be there!).
 * <p>
 * Add your name to the directory and look it up again.
 * <p>
 * Change the email associated with your name to your personal email
 * and look it up again.
 * <p>
 * Remove your name and look it up again.
 * <p>
 * 2. Go into doc in the browser and look at the documentation for the
 * PhoneDirectory interface and the UserInterface interface.
 * <p>
 * 3. For everything you did in #1, decide which methods in #2 it is
 * calling.  Discuss.
 * <p>
 * 4. In IntelliJ, create a new prog02 package in your csc220 project src
 * folder and download the source files from the src/prog02 web
 * directory into src/prog02 directory.  Reload.
 * <p>
 * 5. Download dist/csc220.txt into your project directory, usually
 * IdeaProjects/csc220.  Reload the project so it appears in IntelliJ.
 * <p>
 * 6. Implement processCommands in Main.  I did case 1, partially, in class:
 * <p>
 * name = ui.getInfo("Enter the name ");
 * number = pd.lookupEntry(name);
 * ui.sendMessage(name + " has number " + number);
 * <p>
 * This should work fine if everything is normal.  Look up Victor.
 * <p>
 * What if the user tries to CANCEL when asked to enter the name?  Fix.
 * <p>
 * What if the user enters a blank name?  A blank name is NOT allowed.
 * (However, a blank phone number IS allowed.)  Fix.
 * <p>
 * What if the name isn't in the directory?  (Check the documentation
 * for lookupEntry.)
 * <p>
 * Do the other cases.  DO NOT CALL lookupEntry in any of the other
 * cases.
 * <p>
 * Have a TA check off your work when you are finished.
 * <p>
 * <p>
 * HOMEWORK
 * <p>
 * 7. If the directory is changed, set changed=true.
 * <p>
 * If save is selected, set changed=false.
 * <p>
 * If the user selects exit, check if changed is true.  If so, ask the
 * user if they really want to exit without saving.  Give the choices
 * YES or NO.  Only exit if the user selects YES.
 * <p>
 * <p>
 * For the rest of your homework, you will implement SortedPD which
 * stores the entries in order by name and uses binary search to find
 * entries.
 * <p>
 * 8. Modify Main so it uses a SortedPD instead of an ArrayBasedPD.
 * <p>
 * Copy the methods add, find, found, and remove from ArrayBasedPD to the
 * SortedPD class, including their Javadoc comments.
 * <p>
 * Visit http://www.cs.miami.edu/~vjm/csc220/prog02/dist/csc220.txt
 * and save this file to your workspace/csc220 directory.  Visit
 * csc220.txt in IntelliJ.  You should see
 * <p>
 * Victor
 * vjm@miami.edu
 * Collin
 * cdc104@miami.edu
 * Katarzyna
 * kwp14@miami.edu
 * Ross
 * rcs208@earth.miami.edu
 * Zahra
 * zahra.ahmad@miami.edu
 * Jack
 * jam771@miami.edu
 * <p>
 * Run Main, and tell the program to remove Collin and save.  Reload
 * the csc220.txt window in IntelliJ.  You should see
 * <p>
 * Victor
 * vjm@miami.edu
 * Jack
 * jam771@miami.edu
 * Katarzyna
 * kwp14@miami.edu
 * Ross
 * rcs208@earth.miami.edu
 * Zahra
 * zahra.ahmad@miami.edu
 * <p>
 * Why?  Because the way it removes Collin is by copying the last
 * entry (Jack) into the same location (and decrementing size to Jack
 * does not appear twice).
 * <p>
 * <p>
 * 9. Exit the program.  Restore csc220.txt by saving it from the browser
 * again and refreshing the window in IntelliJ.  Modify remove (in
 * SortedPD) so instead of copying entry [size-1] on top of entry
 * [index] it moves entries [index+1] to [size-1] each back one.  Now
 * when you run Main, remove Collin, and save, and refresh csc220.txt,
 * you should see
 * <p>
 * Victor
 * vjm@miami.edu
 * Katarzyna
 * kwp14@miami.edu
 * Ross
 * rcs208@earth.miami.edu
 * Zahra
 * zahra.ahmad@miami.edu
 * Jack
 * jam771@miami.edu
 * <p>
 * Do not modify add, find, or found until you have this working.
 * <p>
 * <p>
 * 10. add adds a new entry at [index] by first copying [index] to [size]
 * and then putting the new entry at [index].  Modify it so it moves
 * all the entries from [index] to [size-1] forward one index before
 * putting the new entry at [index].
 * <p>
 * Test add by (temporarily) setting index=0 at the top of add instead
 * of using the value it is given.  Run the program and save the
 * directory.  Look at csc220.txt.  Add a new entry (like your name
 * and email).  Save and look at csc220.txt.  Did the new entry end up
 * first in the file?
 * <p>
 * Now try setting index=size/2 and running the same test.  Does the
 * new entry end up in the right place?
 * <p>
 * Once you are sure add is working, remove the temporary line
 * index=size/2.
 * <p>
 * <p>
 * 11. We are going to be modifying find so it returns the index where a
 * new entry should go if it is not there.
 * <p>
 * Collin
 * cdc104@miami.edu
 * Jack
 * jam771@miami.edu
 * Katarzyna
 * kwp14@miami.edu
 * Ross
 * rcs208@earth.miami.edu
 * Victor
 * vjm@miami.edu
 * Zahra
 * zahra.ahmad@miami.edu
 * <p>
 * For example, find("Mary") will return 3 instead of 6.
 * <p>
 * Modify found so it returns false if the index is valid but the name
 * is not at that index.
 * <p>
 * <p>
 * 12. Now it is time to change find so it uses binary search.  Do we
 * need to sort the input file?  No!  Why not?  Object oriented
 * programming.  The loadData method in ArrayBasedPD will call
 * addOrChangeEntry which will call *your* add method to add each
 * entry.  addOrChangeEntry also calls *your* find method.  Your find
 * method will tell your addOrChangeEntry method where to put the
 * entries so it will be sorted.  If this makes your head explode,
 * don't worry about it.  Just trust the fact that if every method does
 * its job properly, the whole thing will work!
 * <p>
 * find needs two variables, low and high, initially 0 and size.  I
 * chose those names because low it is at [0] but it definitely is
 * not at [size].
 * <p>
 * The following is a true statement (NOT code you implement):
 * <p>
 * If i < low and [i] is a valid entry, its key is < name.
 * <p>
 * If j >= high and [j] is a valid entry, its key is >= name.
 * <p>
 * This is true at the start because this is NO valid i < 0 and NO
 * valid j >= size.
 * <p>
 * The following IS code you implement:
 * <p>
 * While low is less than high:
 * <p>
 * Calculate middle, halfway between low and high.
 * <p>
 * If the entry at [middle] is < name, then we know that entries [0]
 * to [middle] are all < name.  So we can safely set
 * low = middle + 1.
 * <p>
 * Else the entry at [middle] must be >= name, se we know the entries
 * from [middle] to [size-1] are >= name.  So we can set high = middle.
 * <p>
 * When the loop is done, low will equal high.  That means the entry
 * at [high] is is the lowest entry that is >= name.  (Why?)  If name is
 * in the directory, it has to be at index [high].  If the name is not
 * there, that is where we should put name.  So what should we return?
 * (Look at the documentation for find.)
 * <p>
 * Test your program.  Restore csc220.txt from the browser, run Main,
 * change my address to vjm@cs.miami.edu, save, and refresh the
 * csc220.txt window in IntelliJ.  You should see.
 * <p>
 * Collin
 * cdc104@miami.edu
 * Jack
 * jam771@miami.edu
 * Katarzyna
 * kwp14@miami.edu
 * Ross
 * rcs208@earth.miami.edu
 * Victor
 * vjm@cs.miami.edu
 * Zahra
 * zahra.ahmad@miami.edu
 * <p>
 * <p>
 * 13. I will put a folder named test into your shared OneDrive folder.
 * <p>
 * Link it into your project folder like you did src.
 * <p>
 * In IntelliJ, reload csc220.
 * <p>
 * Rightclick on the test folder, "Mark Directory as >",
 * select "Test Sources Root".
 * <p>
 * Open (double-click) test/prog02/SortedPDTest.
 * <p>
 * Hover over red junit (in import line) and ask IntelliJ to install the library.
 * <p>
 * Ditto the red jupiter.
 * <p>
 * Click the green triangle next to "class SortedPDTest".
 * <p>
 * Did you pass all the tests?
 */
public class Lab {
    public static void main(String[] args) {
        System.out.println(find("Ma"));
        System.out.println(find("Maa"));
        System.out.println(find("Baaa"));
//        System.out.println(find("maaa"));
    }
    /**
     *
     *   For example, find("Mary") will return 3 instead of 6.
     *
     *   Modify found so it returns false if the index is valid but the name
     *   is not at that index.
     *
     *
     */

    /**
     * 11. We are going to be modifying find so it returns the index where a
     * new entry should go if it is not there.
     * <p>
     * Collin
     * cdc104@miami.edu
     * Jack
     * jam771@miami.edu
     * Katarzyna
     * kwp14@miami.edu
     * Ross
     * rcs208@earth.miami.edu
     * Victor
     * vjm@miami.edu
     * Zahra
     * zahra.ahmad@miami.edu
     */


    static DirectoryEntry[] theDirectory = new DirectoryEntry[100];

    static {
        theDirectory[0] = new DirectoryEntry("Ass", "2");
        theDirectory[1] = new DirectoryEntry("Ass", "2");
        theDirectory[2] = new DirectoryEntry("Ass", "2");
        theDirectory[3] = new DirectoryEntry("Ass", "2");
        theDirectory[4] = new DirectoryEntry("Ba", "2");
        theDirectory[5] = new DirectoryEntry("Bad", "2");
        theDirectory[6] = new DirectoryEntry("Ma", "1");
        theDirectory[7] = new DirectoryEntry("Maaa", "2");


    }

    protected static boolean found(int index, String name) {

        return index < size && theDirectory[index].getName().equals(name);
    }

    /**
     * Assumption made
     * The element are added in random order
     *
     * @param name
     * @return
     */

    static int size = 8;

    protected static int find(String name) {
//        int first = 0;
//        int last = size;
//        while (first <= last) {
//            int mid = first + (last-first) / 2;
//            if (theDirectory[mid].getName().equals(name)) {
//                return mid;
//            } else if (theDirectory[mid].getName().charAt(0) < name.charAt(0)) {
//                first = mid + 1;
//            } else {
//                last = mid - 1;
//            }
//        }
//        return -1;
//        int first = 0;
//        int last = size-1;
//        while (first <= last) {
//            int mid = first + ((last-first) / 2);
//            if (theDirectory[mid].getName().compareTo(name) < 0) {
//                first = mid + 1;
//            } else if (theDirectory[mid].getName().compareTo(name) > 0) {
//                last = mid - 1;
//            } else {
//                return mid;
//            }
//        }
//        return size;
        for (int i = 0; i < size-1; i++) {
            final String dName = theDirectory[i].getName();
            // Match only the first character and then check the rest
            if ((int)dName.toUpperCase().charAt(0) + (int)dName.toLowerCase().charAt(0) < (int)name.toUpperCase().charAt(0) + (int)name.toLowerCase().charAt(0)) {
                int charIndex = 0;
                for (int j = i; j < size; j++) {
                    if (theDirectory[j].getName().length() == name.length()) {
                        if (theDirectory[j].getName().charAt(charIndex) == name.charAt(charIndex)) {
                            String cname = theDirectory[charIndex].getName();
                            for (int k = charIndex; k < theDirectory[j].getName().length(); k++) {
                                if (cname.charAt(charIndex) != name.charAt(charIndex)) break;
                                charIndex += 1;
                                if (charIndex >= name.length()) return j;
                            }
                        }
                    } else {

                    }
                }
            } else if (theDirectory[i].getName().charAt(0) > name.charAt(0)) {

            }
        }
        return size;
    }

    public static class DirectoryEntry {
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

}
