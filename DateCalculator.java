public class DateCalculator {

    /**
     * add specified number of days to a date.
     * done in recursion, until we're reaching to adding 0 days.
     *
     * @param date- the date we're adding to.
     * @param num-  number of days we're adding.
     * @return new date after the addition.
     */
    public static Date addToDate(Date date, int num) {
        if (num == 0) return date;
        if (num < 0)
        {
            if (date.getDay() == 1) //needing to switch month
            {
                if ((date.getMonth() == 1)) {// if we're trying to go a year back
                    return addToDate(new Date(31, 12, date.getYear() - 1), num + 1);
                }

                else {

                    //to determine number of days in the month before we will send some day (we choose 1) in it to the
                    // relevant function
                    return addToDate(new Date
                            (find_days_in_month(new Date(1, date.getMonth() - 1, date.getYear()))
                                    , date.getMonth() - 1,
                                    date.getYear()), num + 1);
                }

            }
            else { //subtracting as many days as possible in once, trying to reach the 1'st in the current month
                int num_of_days_to_subtract = (date.getDay()-1+num > 0) ? num:date.getDay()-1;
                return addToDate(new Date(date.getDay() - num_of_days_to_subtract
                        , date.getMonth(), date.getYear()), num + num_of_days_to_subtract);
            }
        }

        else //num>0
        {
            if (date.getDay() == find_days_in_month(date)) //needing to switch month
            {
                if ((date.getMonth() == 12)) // if we're trying to go a year forward
                    return addToDate(new Date(1, 1, date.getYear() + 1), num - 1);

                else

                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
            else {//adding as many days as possible in once, trying to reach the last day in the current month
                int num_of_days_to_add =  (find_days_in_month(date)- date.getDay()-num > 0) ?
                        num:find_days_in_month(date)-date.getDay();
                return addToDate(new Date(date.getDay() + num_of_days_to_add
                        , date.getMonth(), date.getYear()), num - num_of_days_to_add);
            }

        }
    }


    /**
     * checks if year leap according to the described rules.
     * @param year- the checked year
     * @return true if the year is leap, false otherwise.
     */

    public static boolean is_leap(int year)
    {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }


    /**
     * helping method, finds amount of days in a month according to the described rules.
     * @param date- the date we are trying to determine about it's month.
     * @return number of days in this month.
     */
    public static int find_days_in_month(Date date)
    {
        if (date.getMonth()==1){return 31;}
        if ((date.getMonth()==2) && (is_leap(date.getYear()))){
            return 29;}
        if ((date.getMonth()==2) && (!is_leap(date.getYear()))){return 28;}
        if (date.getMonth()==3){return 31;}
        if (date.getMonth()==4){return 30; }
        if (date.getMonth()==5){return 31;}
        if (date.getMonth()==6){return 30;}
        if (date.getMonth()==7){return 31;}
        if (date.getMonth()==8){return 31;}
        if (date.getMonth()==9){return 30;}
        if (date.getMonth()==10){return 31;}
        if (date.getMonth()==11){return 30;}
        if (date.getMonth()==12){return 31;}
        return 0;

    }
}
