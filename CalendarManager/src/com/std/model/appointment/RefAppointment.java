package com.std.model.appointment;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Date;
//import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;

public class RefAppointment extends AppointmentTemplate {
	
	public Date startDate;
	public Date endDate;
	
    public RefAppointment(Date startDate, Date endDate) {
		super(startDate, endDate);
		
	}
    
    public RefAppointment(){
       
    }

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
				System.out.println(o1);
				System.out.println(o2);
				if(o1.getEndDate() != null && o2.getEndDate() != null){
					int ret = o1.getEndDate().compareTo(o2.getEndDate());
					if(ret == 0 && !o1.equals(o2))
						ret++;
					return ret;
					}
					return 0;
				}
		};
	}

	
	
/*THESE METHODS ARE ABSTRACT IN APPOINTMENT TEMPLATE AND MUST BE OVERRIDDEN HERE (SUBCLASS)*/
	public Date getStartDate() {
		startDate = new Date();
		return startDate;
	}
	
	public void internalSet(Date date){
		System.out.println("Im Being called, the date is:" + date);
		if(date == null)
			throw new NullPointerException("date");
		if(!date.equals(this.startDate))
			this.setChanged();
		this.startDate = date;
	}

	public void setStartDate(Date date){
		internalSet(date);
		this.notifyObservers();
	}
	
	public Date getEndDate(){
		endDate = new Date(startDate.getTime() + getDuration());
		System.out.println(endDate);
		return endDate;
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
}
