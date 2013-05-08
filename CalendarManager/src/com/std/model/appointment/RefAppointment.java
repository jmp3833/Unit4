package com.std.model.appointment;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Date;
//import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;

public class RefAppointment extends AppointmentTemplate {
	
    private static final long serialVersionUID = -4331942306839501945L;
	
	/**
	 * compares two Appointments by their start dates
	 */
	public static final Comparator<RefAppointment> COMPARATOR_APPOINTMENT_START;
	
	/**
	 * compares two Appointments by their end dates
	 */
	public static final Comparator<RefAppointment> COMPARATOR_APPOINTMENT_END;
	
    static {
		// set the start comparator
		COMPARATOR_APPOINTMENT_START = new Comparator<RefAppointment>() {
			public int compare(RefAppointment o1, RefAppointment o2) {
				System.out.println(o1);
				System.out.println(o2);
				if (o1.getStartDate() != null && o2.getStartDate() != null ){
					int ret = o1.getStartDate().compareTo(o2.getStartDate());
					if(ret == 0 && !o1.equals(o2))
						ret++;
					return ret;
				}
				return 0;
				
			}
		};
		
		// set the end comparator
		COMPARATOR_APPOINTMENT_END = new Comparator<RefAppointment>() {
			public int compare(RefAppointment o1, RefAppointment o2) {
				int ret = o1.getEndDate().compareTo(o2.getEndDate());
				if(ret == 0 && !o1.equals(o2))
					ret++;
				return ret;
			}
		};
	}

	/**
	 * the start date of this particular appointment instance
	 */
	private Date startDate;
	
	
/*THESE METHODS ARE ABSTRACT IN APPOINTMENT TEMPLATE AND MUST BE OVERRIDDEN HERE (SUBCLASS)*/
	public Date getStartDate() {
		return startDate;
	}
	
	public void internalSetStartDate(Date date){
		if(date == null)
			throw new NullPointerException("date");
		if(!date.equals(this.startDate))
			this.setChanged();
		this.startDate = date;
	}

	public void setStartDate(Date date){
		internalSetStartDate(date);
		this.notifyObservers();
	}
	
	public Date getEndDate(){
		return new Date(startDate.getTime() + getDuration());
	}

	public void setEndDate(Date date){
		long duration = date.getTime() - startDate.getTime();
		setDuration(duration);
	}
	
	public DateRange getDateRange(){
		return new DateRange(startDate, getEndDate());
	}

	public void setDateRange(DateRange dr){
		if(dr == null)
			throw new NullPointerException("dr");
		long duration = dr.getDurationInMS();
		setDuration(duration);
		setStartDate(dr.getStartDate());
	}
	
	/* WE DONT NEED THESE HERE WITH TEMPLATE METHOD:	

	 
	public long getDuration() {
		return parent.getDuration();
	}
	
	public void setDuration(long duration){
		parent.setDuration(duration);
	}

	
	

	public String getLocation(){
		return parent.getLocation();
	}


	public void setLocation(String loc){
		parent.setLocation(loc);
	}

	
	public RecurrencePattern getPattern() {
		return parent.getPattern();
	}

	public void setPattern(RecurrencePattern pattern) {
		parent.setPattern(pattern);
	}

	public String getDescription() {
		return parent.getDescription();
	}


	public void setDescription(String description){
		parent.setDescription(description);
	}

	
	public String getTitle() {
		return parent.getTitle();
	}

	public void setTitle(String title){
		parent.setTitle(title);
	}

	/**
	 * Sets all the fields of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param apptTmple the new appointment fields
	 
	public void setFields(RefAppointment appt) {
		getTemplate().setFields(appt.getTemplate());
		setStartDate(appt.getStartDate());
		
		this.notifyObservers();
	}
		
		
	 * the template appointment that stores this appointment's description, etc.
	 
	private AppointmentTemplate parent;
	 
	public AppointmentTemplate getTemplate(){
		return parent;
	}

	private void setTemplate(AppointmentTemplate t){
		if(t == null)
			throw new NullPointerException();
		parent = t;
	}
    */

}
