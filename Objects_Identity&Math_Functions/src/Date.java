/**
 * The Date class represents a date with day, month, and year components.
 * It provides methods to initialize, modify, and retrieve the date,
 * as well as compare dates for equality and calculate hash codes.
 */
public class Date {
    protected int day, month, year;

    /**
     * Constructs a new Date instance with the specified year, month, and day.
     * If any value is out of the valid range, it is set to a default value.
     *
     * @param year  the year component of the date
     * @param month the month component of the date
     * @param day   the day component of the date
     */
    public Date(int year, int month, int day) {
        this.day = day;
        if(day>31 || day<1){
            this.day=1;
        }
        this.month=month;
        if(month>12 || month<1){
            this.month=1;
        }
         this.year=year;
        if(year<-3999 || year>3999){
            this.year=0;
        }
    }

    /**
     * Sets the month component of the date to the specified value.
     * If the value is out of the valid range, it is set to a default value.
     *
     * @param month2 the new value for the month component
     */
    public void setMonth(int month2) {

        if(month2>12 || month2<1){
          this.month= 1;
        }
        else {this.month=month2;}
    }


    /**
     * Returns a string representation of the date in the format "day/month/year".
     *
     * @return the string representation of the date
     */
    @Override
    public String toString() {

        String da;
        String mo;
        String ye;

        if(this.day<10){
            da="0"+this.day;
        }
        else {
            da=String.valueOf(this.day);
        }
        if(this.month<10){
            mo="0"+this.month;
        }
        else {
            mo=String.valueOf(this.month);
        }
        if(this.year<1000 && this.year > 99 || this.year < -99 && this.year > -1000){
            ye="0"+this.year;
        }
        else if(this.year<100 && this.year > 9 || this.year < -9 && this.year > -100){
            ye="00"+this.year;
        }
        else if(this.year<10 && this.year >=0 || this.year <= 0 && this.year > -10){
            ye="000"+this.year;
        }
        else {
            ye=String.valueOf(this.year);
        }
        return (da+"/"+mo+"/"+ye);
    }


    /**
     * Compares this Date object to the specified object for equality.
     * Two Date objects are considered equal if they have the same day, month, and year.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
    if(obj==null){
        return false;
    }
        if(!(this.hashCode()==obj.hashCode())||!(obj instanceof Date)) {
            return false;
        }
        Date d2=(Date)obj;
        return(this.day==d2.day && this.month==d2.month && this.year==d2.year);

    }


    /**
     * Returns the hash code value for this Date object.
     * The hash code is calculated based on the day, month, and year values.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return this.day*1440+this.month*44640+this.year*535680;
    }
}