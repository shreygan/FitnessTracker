package JavaClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
 * @author shrey
 */

public class Calendar {
    
    // Amount of milliseconds in a month
    private static final long MMINMONTH = 2592000000L;
    
    // Amount of milliseconds in a day
    private static final long MMINDAY = 86400000L;
    
    private String day;
    private String month;
    private int date;
    private String time;
    private int year;
    
    private int daysInMonth;
    private int fWeekday;
    
    private Date d;
    private int monthInt;
    
    // How many months back or forward the user is
    private static int dif;
    
    public Calendar() {                
        long t = System.currentTimeMillis();
        
        // Changing t based on the dif varialbe
        int i = dif;
        while (i != 0) {
            if (i < 0) {
                t -= MMINMONTH;
                i++;
            } else {
                t += MMINMONTH;
                i--;
            }
        }
        
        d = new Date(t);
        
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
        Scanner sc = new Scanner(d.toString());
                             
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
        switch (month.toLowerCase()) {
            case "jan":
                month = "January";
                monthInt = 0;
                break;
            case "feb":
                month = "February";
                monthInt = 1;
                break;
            case "mar":
                month = "March";
                monthInt = 2;
                break;
            case "apr":
                month = "April";
                monthInt = 3;
                break;
            case "may":
                month = "May";
                monthInt = 4;
                break;
            case "jun":
                month = "June";
                monthInt = 5;
                break;
            case "jul":
                month = "July";
                monthInt = 6;
                break;
            case "aug":
                month = "August";
                monthInt = 7;
                break;
            case "sep":
                month = "September";
                monthInt = 8;
                break;
            case "oct":
                month = "October";
                monthInt = 9;
                break;
            case "nov":
                month = "November";
                monthInt = 10;
                break;
            default:
                month = "December";
                monthInt = 11;
                break;
        }
    }
    
    public void setDaysInMonth() {        
        if (month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July") || month.equals("August") || month.equals("October") || month.equals("December")) {
            daysInMonth = 31;
        } else if (month.equals("February") && (year % 4) == 0) {
            daysInMonth = 29;
        } else if (month.equals("February")) {
            daysInMonth = 28;
        }
    }
    
    public void setFirstWeekday() {
        // Sets the first weekday in the month, to assist in printing the calendar on the page accurately
        
        // Setting how many days back the first weekday is from the current date
        int fCDif = date - 1;

        // Creating a variable for the milliseconds of the current date
        long m = d.getTime();
        
        // Setting the date of the first weekday in the month, by subtracting the amount of days to the first weekday
        Date fD = new Date(m - (MMINDAY * fCDif));

        Scanner scFD = new Scanner(fD.toString());

        // Getting the first weekday
        String fW = scFD.next();
        
        // Setting the first weekday as an int
        switch (fW) {
            case "Mon":
                fWeekday = 0;
                break;
            case "Tue":
                fWeekday = 1;
                break;
            case "Wed":
                fWeekday = 2;
                break;
            case "Thu":
                fWeekday = 3;
                break;
            case "Fri":
                fWeekday = 4;
                break;
            case "Sat":
                fWeekday = 5;
                break;
            case "Sun":
                fWeekday = 6;
                break;        
            default:
                break;
        }
    }
        
    // Method to move one month back
    public void monthLeft() {
        // Changing the dif variable one lower (as we are moving back one month)
        dif--;
        
        long t = d.getTime();
                        
        // Creating a new date variable, moved one month back
        d = new Date(t - MMINMONTH);
        
        // Initializing all the data in the class around the new data
        initializeData(d);
    }
    
    // Method to move one month forward
    public void monthRight() {        
        dif++;
        
        long t = d.getTime();
                        
        // Creating a new date variable, moved one month forward
        d = new Date(t + MMINMONTH);
        
        // Initializing all the data in the class around the new data
        initializeData(d);
    }
    
    // Method to get the current month, unimpacted by moving the calendar to different months
    public String getCurrentMonth() {        
        Date cD  = new Date();        
        Scanner sc = new Scanner(cD.toString());
        
        sc.next();
        
        // Getting the current month
        String m = sc.next();
        
        // Converting the month to full length
        switch (m.toLowerCase()) {
            case "jan":
                m = "January";
                break;
            case "feb":
                m = "February";
                break;
            case "mar":
                m = "March";
                break;
            case "apr":
                m = "April";
                break;
            case "may":
                m = "May";
                break;
            case "jun":
                m = "June";
                break;
            case "jul":
                m = "July";
                break;
            case "aug":
                m = "August";
                break;
            case "sep":
                m = "September";
                break;
            case "oct":
                m = "October";
                break;
            case "nov":
                m = "November";
                break;
            default:
                m = "December";
                break;
        }
        
        return m;
    }
    
    // Method to print exercises on the calendar
    public ArrayList<String> printExercise(boolean b, String user, int tYear, int tMonth, int tDay) {
        ArrayList<Integer> temp = CreateExercises.getIndiciesList();

        // Seeing if this is the first time getting called
        if (b) {
            CreateExercises.addExistingData();
        }
        
        int loops = temp.size();

        ArrayList<String> exercises = new ArrayList<>();
        
        for (int i = 0; i < loops; i++) {            
            Scanner sc = new Scanner(CreateExercises.printExercise(user, temp.get(0)));

            sc.nextInt();
            String eDate = sc.next();
            
            sc.next();            
            String exercise = sc.next();

            java.sql.Date dD = java.sql.Date.valueOf(eDate);

            // Getting the date information surrounding the exercise' date
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(dD);
            
            int eMonth = cal.get(java.util.Calendar.MONTH);
            int eDay = cal.get(java.util.Calendar.DAY_OF_MONTH);
            int eYear = cal.get(java.util.Calendar.YEAR);

            if (eYear == tYear) {
                if (eMonth == tMonth) {
                    if (eDay == tDay) {
                        exercises.add(exercise);
                    }
                }
            }
        }
        
        return exercises;
    }
}
