package com.std.model.appointment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;

public abstract class AppointmentTemplate extends java.util.Observable implements Serializable {

	
	private void readObject(ObjectInputStream istream) throws ClassNotFoundException, IOException {
		internalSet((Date)istream.readObject());
	}
	
	
	private void writeObject(ObjectOutputStream ostream) throws IOException {
		ostream.writeObject(getStartDate());
	}
	
	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 8219757873492578630L;
	private String title;
	private String description;
    private String location;
	private long duration;
	private RecurrencePattern recPattern;
	private Date startDate;
	private Date endDate;
	private DateRange dr;
	
	
	public AppointmentTemplate(Date startDate, Date endDate)
	{
		getFields();
		setFields(title,description,location,duration,startDate,endDate,dr);
	}
	
/*Constructor Without Start Date*/
	public AppointmentTemplate()
	{
		getFields();
		setFields(title,description,location,duration,startDate,endDate,dr);
	}
	
	
	/**
	 * Gets all the values of the fields of the appointment
	 */
	public void getFields()
	{
		title = getTitle();
		description = getDescription();
		location = getLocation();
		duration = getDuration();
		recPattern = getPattern();
		startDate = getStartDate();
		endDate = getEndDate();
		dr = getDateRange();
		
	}
	/**
	 * Sets all the fields of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param apptTmple the new appointment fields
	 */
	
	public void setFields(String title, String description, String location, long duration, Date startDate, Date endDate, DateRange dr)
	{
		internalSetTitle(title);
		setDescription(description);
		internalSetDescription(description);
		setLocation(location);
		internalSetLocation(location);
		setPattern(recPattern);
		internalSetPattern(recPattern);
		internalSet(startDate);
		setEndDate(endDate);
		setDuration(duration);
		internalSetDuration(duration);
		setDateRange(dr);
		this.notifyObservers();
	}


	
	/*THE FOLLOWING MUST BE OVERRIDDEN BY THE REFAPPOINTMENT CLASS*/
	
	abstract Date getStartDate();

	abstract Date getEndDate();
	
	abstract void internalSet(Date date);

	abstract void setStartDate(Date date);

	abstract void setEndDate(Date date);
	
	abstract DateRange getDateRange();

	abstract void setDateRange(DateRange dr);
	
  /*END ABSTRACT METHODS*/
	

	/**
	 * @return the title of the appointment template object.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * @return the description of the appointment template object.
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * @return the location of the appointment template Object.
	 */
	public String getLocation(){
		return location;
	}

	/**
	 * @return the duration of the appointment template object in milliseconds.
	 */
	public long getDuration(){
		return duration;
	}

	/**
	 * @return the pattern object of this appointment template.
	 */
	public RecurrencePattern getPattern() {
		return recPattern;
	}
	

	/**
	 * Sets the title of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param title the new title of the appointment.
	 * @throws NullPointerException if title is null
	 */
	
	public void internalSetTitle(String title) {
		if(title == null)
			throw new NullPointerException("title");
		if(!title.equals(this.title))
			this.setChanged();
		this.title = title;
		this.notifyObservers();
	}

	/**
	 * Sets the title of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param title the new title of the appointment.
	 * @throws NullPointerException if title is null
	 */
	public void setTitle(String title) {
		internalSetTitle(title);
		
	}

	/**
	 * Sets the description of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param description the new description for the appointment
	 * @throws NullPointerException if description is null
	 */
	public void internalSetDescription(String description) {
		if(description == null)
			throw new NullPointerException("description");
		if(!description.equals(this.description))
			this.setChanged();
		this.description = description;
	}

	/**
	 * Sets the description of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param description the new description for the appointment
	 * @throws NullPointerException if description is null
	 */
	public void setDescription(String description) {
		internalSetDescription(description);
		this.notifyObservers();
	}

	/**
	 * Sets the location of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param location the new location for the appointment
	 * @throws NullPointerException if loc is null
	 */
	public void internalSetLocation(String loc) {
		if(loc == null)
			throw new NullPointerException("loc");
		if(!loc.equals(this.location))
			this.setChanged();
		this.location = loc;
	}

	/**
	 * Sets the location of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param loc the new location for the appointment
	 * @throws NullPointerException if loc is null
	 */
	public void setLocation(String loc) {
		internalSetLocation(loc);
		this.notifyObservers();
	}
	
	/**
	 * Sets the duration of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param duration the new duration for the appointment in milliseconds
	 * @throws IllegalArgumentException if duration is less than 0
	 */
	public void internalSetDuration(long duration) {
		if(duration < 0)
			throw new IllegalArgumentException("duration is less than 0");
		if(duration != this.duration)
			this.setChanged();
		this.duration = duration;
	}

	/**
	 * Sets the duration of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param duration the new duration for the appointment in milliseconds
	 * @throws IllegalArgumentException if duration is less than 0
	 */
	public void setDuration(long duration) {
		internalSetDuration(duration);
		this.notifyObservers();
	}

	/**
	 * Sets the pattern of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param pattern the new pattern for the appointment
	 */
	public void internalSetPattern(RecurrencePattern pattern) {
		if(pattern == null ? this.recPattern != null : !pattern.equals(this.recPattern))
			this.setChanged();
		this.recPattern = pattern;
	}

	/**
	 * Sets the pattern of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param pattern the new pattern for the appointment
	 */
	public void setPattern(RecurrencePattern pattern) {
		internalSetPattern(pattern);
		this.notifyObservers(recPattern);
	}
	
	
	/* ORIGINAL METHODS:
	final void setFields(String title, String description, String location, long duration, Date startDate, Date endDate, DateRange dr) {
		internalSetTitle(title);
		internalSetDescription(description);
		internalSetLocation(location);
		internalSetDuration(duration);
		internalSetPattern(getPattern());
		internalSetStartDate(startDate);
		setStartDate(startDate);
		setEndDate(endDate);
		setDateRange(dr);
		this.notifyObservers();
	}
    */
	

	/* ORIGINAL METHODS
	public Object clone() {
		AppointmentTemplate ret = new AppointmentTemplate("", "", "", 0);
		ret.setFields(this);
		return ret;
	}
	
	/**
	 * creates a new AppointmentTemplate
	 * 
	 * @param title Title of the appointment.
	 * @param description Description of the appointment.
	 * @param location Location of the appointment.
	 * @param duration Duration of the appointment in milliseconds.
	 
	public AppointmentTemplate(String title, String description, String location, long duration, Date startDate) {
		super();
		internalSetTitle(title);
		internalSetDescription(description);
		internalSetLocation(location);
		internalSetDuration(duration);
		internalSetStartDate(startDate);
		recPattern = null;
		
		this.notifyObservers();
	}
*/
	
	
	/*
	/**
	 * Loads the object from an object input stream
	 * 
	 * @param istream the object stream to be reading from
	 * @throws ClassNotFoundException if there is a casting error
	 * @throws IOException an I/O exception of some sort has occurred
	 
	
	private void readObject(ObjectInputStream istream) throws ClassNotFoundException, IOException {
		internalSetTitle((String)istream.readObject());
		internalSetDescription((String)istream.readObject());
		internalSetLocation((String)istream.readObject());
		internalSetDuration(istream.readLong());
		internalSetPattern((RecurrencePattern)istream.readObject());
	}
	
	/**
	 * Saves the object to an object output stream
	 * 
	 * @param ostream the object stream to be writing to
	 * @throws IOException an I/O exception of some sort has occurred
	 
	private void writeObject(ObjectOutputStream ostream) throws IOException {
		ostream.writeObject(getTitle());
		ostream.writeObject(getDescription());
		ostream.writeObject(getLocation());
		ostream.writeLong(getDuration());
		ostream.writeObject(getPattern());
	}
	*/
}
