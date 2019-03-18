package com.avob.openadr.server.common.vtn.models.demandresponseevent;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "demandresponseeventsignal")
public class DemandResponseEventSignal {
	/**
	 * Autogenerated unique id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String signalName;
	private String signalType;
	private String unitType;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<DemandResponseEventSignalInterval> intervals;
	private Float currentValue;

	/**
	 * Related event
	 */
	@ManyToOne
	@JoinColumn(name = "demandresponseevent_id")
	private DemandResponseEvent event;

	public String getSignalName() {
		return signalName;
	}

	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}

	public String getSignalType() {
		return signalType;
	}

	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public List<DemandResponseEventSignalInterval> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<DemandResponseEventSignalInterval> intervals) {
		this.intervals = intervals;
	}

	public Float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Float currentValue) {
		this.currentValue = currentValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DemandResponseEvent getEvent() {
		return event;
	}

	public void setEvent(DemandResponseEvent event) {
		this.event = event;
	}
}
