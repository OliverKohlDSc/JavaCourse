package gmbh.conteco;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// How can the existence of this class be justified
public class TimeSheetEntry {

	public TimeSheetEntry() { }
	
	public TimeSheetEntry(String task, LocalDate date, LocalTime start, LocalTime end, String notes) {
		this.setTask(task);
		this.setDate(date);
		this.setStart(start);
		this.setEnd(end);
		this.setNotes(notes);
		this.setDuration(Duration.ofSeconds(ChronoUnit.SECONDS.between(start, end)));
	}

	public TimeSheetEntry(String task, LocalDate date, LocalTime start, Duration duration, String notes) {
		this.setTask(task);
		this.setDate(date);
		this.setStart(start);
		this.setNotes(notes);
		this.setEnd(start.plus(duration.toSeconds(), ChronoUnit.SECONDS));
	}
	
	public StringProperty taskProperty() {
		if (task == null)
			task = new SimpleStringProperty(this, "task");
	
		return task;
	}
	
	public String getTask() {
		return taskProperty().get();
	}

	public void setTask(String task) {
		this.taskProperty().set(task);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		if (date == null)
			date = new SimpleObjectProperty<LocalDate>(this, "date");
	
		return date;
	}
	
	public LocalDate getDate() {
		return this.dateProperty().get();
	}

	public void setDate(LocalDate date) {
		this.dateProperty().set(date);
	}

	public ObjectProperty<LocalTime> startProperty() {
		if (start == null)
			start = new SimpleObjectProperty<LocalTime>(this, "start");
	
		return start;
	}
	
	public LocalTime getStart() {
		return this.startProperty().get();
	}

	public void setStart(LocalTime start) {
		this.startProperty().set(start);
	}
	
	public ObjectProperty<LocalTime> endProperty() {
		if (end == null)
			end = new SimpleObjectProperty<LocalTime>(this, "end");
	
		return end;
	}
	
	public LocalTime getEnd() {
		return this.endProperty().get();
	}

	public void setEnd(LocalTime end) {
		this.endProperty().set(end);
	}
	
	public ObjectProperty<Duration> durationProperty() {
		if (duration == null)
			duration = new SimpleObjectProperty<Duration>(this, "duration");
	
		return duration;
	}
	
	public Duration getDuration() {
		return this.durationProperty().get();
	}

	public void setDuration(Duration duration) {
		this.durationProperty().set(duration);
	}

	public StringProperty notesProperty() {
		if (this.notes == null)
			this.notes = new SimpleStringProperty(this, "notes");
		
		return this.notes;
	}
	
	public String getNotes() {
		return this.notesProperty().get();
	}

	public void setNotes(String notes) {
		this.notesProperty().set(notes);
	}

	private StringProperty task;
	
	private ObjectProperty<LocalDate> date;
	
	private ObjectProperty<LocalTime> start;
	
	private ObjectProperty<LocalTime> end;
	
	private ObjectProperty<Duration> duration;
	
	private StringProperty notes;
}