/**
 * The DateTime class represents a date and time with day, month, year, hour, and minute components.
 * It extends the Date class and provides methods to initialize, modify, and retrieve the date and time,
 * as well as compare DateTime objects for equality and calculate hash codes.
 */
public class DateTime extends Date{
   protected int hour, minute;

    /**
     * Constructs a new DateTime instance with the specified year, month, day, hour, and minute.
     * If any value is out of the valid range, it is set to a default value.
     *
     * @param year   the year component of the date
     * @param month  the month component of the date
     * @param day    the day component of the date
     * @param hour   the hour component of the time
     * @param minute the minute component of the time
     */
   public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.hour=hour;
        if(hour<0 || hour>23) {
            this.hour = 0;
        }
        this.minute=minute;
        if(minute<0 || minute>59) {
            this.minute = 0;
        }
    }

    /**
     * Sets the hour component of the time to the specified value.
     * If the value is out of the valid range, it is set to a default value.
     *
     * @param hour2 the new value for the hour component
     */
    public void setHour (int hour2){

        if(hour2<0 || hour2>23) {
            this.hour = 0;
        }
        else { this.hour=hour2;}
    }

    /**
     * Sets the minute component of the time to the specified value.
     * If the value is out of the valid range, it is set to a default value.
     *
     * @param minute2 the new value for the minute component
     */
    public void setMinute(int minute2) {

        if(minute2<0 || minute2>59) {
            this.minute=0;
        }
        else {this.minute=minute2;}
    }


    /**
     * Returns a string representation of the date and time in the format "day/month/year hour:minute".
     *
     * @return the string representation of the date and time
     */
    @Override
    public String toString() {
        String ho;
        String mi;
        if(this.hour<10){
            ho="0"+this.hour;
        }
        else {
            ho=String.valueOf(this.hour);
        }
        if(this.minute<10){
            mi="0"+this.minute;
        }
        else {
            mi=String.valueOf(this.minute);
        }
        return (super.toString()+" "+ho+":"+mi);
    }


    /**
     * Compares this DateTime object to the specified object for equality.
     * Two DateTime objects are considered equal if they have the same day, month, year, hour, and minute.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null) {
            return false;
        }
        if(!(this.hashCode()==obj.hashCode())||!(obj instanceof DateTime)){
            return false;
        }
        DateTime dt2=(DateTime) obj;
        return (this.day == dt2.day && this.month == dt2.month && this.year == dt2.year && this.hour == dt2.hour && this.minute == dt2.minute);
    }


    /**
     * Returns the hash code value for this DateTime object.
     * The hash code is calculated based on the day, month, year, hour, and minute values.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return super.hashCode()+(hour+1)*60+(minute+1);
    }
}
