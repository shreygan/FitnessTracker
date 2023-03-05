package JavaClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
 * @author shrey
 */

public class Calendar {
    
    // A final variable for the amount of milliseconds in a month
    private static final long mmInMonth = 2592000000L;
    
    // A final varialbe for the amount of milliseconds in a day
    private static final long mmInDay = 86400000L;
    
    private String day;
    private String month;
    private int date;
    private String time;
    private int year;
    
    private int daysInMonth;
    private int fWeekday;
    
    private Date d;
    private int monthInt;
    
    // A int variable for how many months back or forward the user is
    private static int dif;
    
    public Calendar() {
                
        // Setting t with how many milliseconds passed since the Epoch (00:00 January 1st, 1970 UTC)
        long t = System.currentTimeMillis();
        
        // Changing t based on the dif varialbe
        int i = dif;
        while (i != 0) {
            if (i < 0) {
                t -= mmInMonth;
                i++;
            } else {
                t += mmInMonth;
                i--;
            }
        }
        
        // Creating a new data with t
        d = new Date(t);
        
        // Initializing the rest of the variables in the class
        initializeData(d);
    }
    
    public String getMonth() {
        return month;
    }
    
    public int getFWeekday() {
        return fWeekday;
    }

    public String getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }    
    
    public int getMonthInt() {
        return monthInt;
    }
    
    public final void initializeData(Date d) {
        
        // Creating a Scanner with the date 
        Scanner sc = new Scanner(d.toString());
                             
        // Updating the class' date variable 
        this.d = d;
        
        day = sc.next();
        month = sc.next();
        date = sc.nextInt();
        time = sc.next();
        
        sc.next();
        
        year = sc.nextInt();
        
        setMonth();
        setDaysInMonth();
        setFirstWeekday();
    }
    
    public void setMonth() {
        
        // Setting the class' variable month based on the date passed in initializeData
        // Also setting monthInt here
        if (month.toLowerCase().equals("jan")) {
            month = "January";
            monthInt = 0;
        } else if (month.toLowerCase().equals("feb")) {
            month = "February";
            monthInt = 1;
        } else if (month.toLowerCase().equals("mar")) {
            month = "March";
            monthInt = 2;
        } else if (month.toLowerCase().equals("apr")) {
            month = "April";
            monthInt = 3;
        } else if (month.toLowerCase().equals("may")) {
            month = "May";
            monthInt = 4;
        } else if (month.toLowerCase().equals("jun")) {
            month = "June";
            monthInt = 5;
        } else if (month.toLowerCase().equals("jul")) {
            month = "July";
            monthInt = 6;
        } else if (month.toLowerCase().equals("aug")) {
            month = "August";
            monthInt = 7;
        } else if (month.toLowerCase().equals("sep")) {
            month = "September";
            monthInt = 8;
        } else if (month.toLowerCase().equals("oct")) {
            month = "October";
            monthInt = 9;
        } else if (month.toLowerCase().equals("nov")) {
            month = "November";
            monthInt = 10;
        } else {
            month = "December";
            monthInt = 11;
        }
    }
    
    public void setDaysInMonth() {
        
        // Setting the amount of days that occur in the current month
        if (month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July") || month.equals("August") || month.equals("October") || month.equals("December")) {
            daysInMonth = 31;
        } else if (month.equals("February") && (year % 4) == 0) {
            daysInMonth = 29;
        } else if (month.equals("February")) {
            daysInMonth = 28;
        }
    }
    
    public void setFirstWeekday() {
        // To set the first weekday in the month, to assist in printing the calendar on the page accurately
        
        // Setting how many days back the first weekday is from the current date
        int fCDif = date - 1;

        // Creating a variable for the molliseconds of the current date
        long m = d.getTime();
        
        // Setting the date of the first weekday in the month, by subtracting the amount of days to the first weekdau
        Date fD = new Date(m - (mmInDay * fCDif));

        // Setting the first weekday's date in a scanner
        Scanner scFD = new Scanner(fD.toString());

        // Getting the first weekday
        String fW = scFD.next();
        
        // Setting the first weekday as an int
        if (fW.equals("Mon")) {
                fWeekday = 0;
            } else if (fW.equals("Tue")) {
                fWeekday = 1;
            } else if (fW.equals("Wed")) {
                fWeekday = 2;
            } else if (fW.equals("Thu")) {
                fWeekday = 3;
            } else if (fW.equals("Fri")) {
                fWeekday = 4;
            } else if (fW.equals("Sat")) {
                fWeekday = 5;
            } else if (fW.equals("Sun")) {
                fWeekday = 6;
            }        
    }
        
    // Method to move one month back
    public void monthLeft() {
        
        // Changing the dif variable one lower (as we are moving back one month)
        dif--;
        
        // Get the milliseconds in the current date
        long t = d.getTime();
                        
        // Creating a new date variable, moved one month back
        d = new Date(t - mmInMonth);
        
        // Initializing all the data in the class around the new data
        initializeData(d);
    }
    
    // Method to move one month forward
    public void monthRight() {
        
        // Changing the dif variable one higher (as we are moving forward one month)
        dif++;
        
        // Get the milliseconds in the current date
        long t = d.getTime();
                        
        // Creating a new date variable, moved one month forward
        d = new Date(t + mmInMonth);
        
        // Initializing all the data in the class around the new data
        initializeData(d);
    }
    
    // Method to get the current month, unimpacted by moving the calendar to different months
    public String getCurrentMonth() {
        
        // Getting a date object for the current date
        Date cD  = new Date();
        
        // Putting the current date into a scannner to analyze it
        Scanner sc = new Scanner(cD.toString());
        
        sc.next();
        
        // Getting the current month
        String m = sc.next();
        
        // Converting the month to full length
        if (m.toLowerCase().equals("jan")) {
            m = "January";
        } else if (m.toLowerCase().equals("feb")) {
            m = "February";
        } else if (m.toLowerCase().equals("mar")) {
            m = "March";
        } else if (m.toLowerCase().equals("apr")) {
            m = "April";
        } else if (m.toLowerCase().equals("may")) {
            m = "May";
        } else if (m.toLowerCase().equals("jun")) {
            m = "June";
        } else if (m.toLowerCase().equals("jul")) {
            m = "July";
        } else if (m.toLowerCase().equals("aug")) {
            m = "August";
        } else if (m.toLowerCase().equals("sep")) {
            m = "September";
        } else if (m.toLowerCase().equals("oct")) {
            m = "October";
        } else if (m.toLowerCase().equals("nov")) {
            m = "November";
        } else {
            m = "December";
        }
        
        // Returning the current month
        return m;
    }
    
    // Method to print exercises on the calendar
    public ArrayList<String> printExercise(boolean b, String user, int tYear, int tMonth, int tDay) {
        
        // Creating the CreateExercises class
        CreateExercises e = new CreateExercises();
        
        // Getting the list of the indicies associated with the user
        ArrayList<Integer> temp = e.getIndiciesList();

        // Seeing if this is the first time getting called
        if (b) {
            e.addExistingData();
        }
        
        // Setting a variable for how many times the loop should run
        int loops = temp.size();

        // Creating an ArrayList for the exercise names
        ArrayList<String> exercises = new ArrayList<>();
        
        for (int i = 0; i < loops; i++) {
            
            // Calling CreateExercises' method printExercise to get all information related to the exercise
            Scanner sc = new Scanner(e.printExercise(user, temp.get(0)));

            // Storing only the information that is needed 
            sc.nextInt();

            // Storing the date of the exercise
            String eDate = sc.next();
            
            sc.next();
            
            // Storing the name of the exercise
            String exercise = sc.next();

            // Creating a Date object for the exercise date
            java.sql.Date dD = java.sql.Date.valueOf(eDate);

            // USing java.util.Calendar to get the date information surrounding the exercise' date
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(dD);
            
            // Getting the month, day, and year of the exercise' date
            int eMonth = cal.get(java.util.Calendar.MONTH);
            int eDay = cal.get(java.util.Calendar.DAY_OF_MONTH);
            int eYear = cal.get(java.util.Calendar.YEAR);

            // Seeing if the exercise date matches the current day
            if (eYear == tYear) {
                if (eMonth == tMonth) {
                    if (eDay == tDay) {
                        exercises.add(exercise);
                    }
                }
            }
        }
        
        // Returning the ArrayList of the exercises that are on that day
        return exercises;
    }
}
