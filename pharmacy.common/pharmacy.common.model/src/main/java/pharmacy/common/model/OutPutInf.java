package pharmacy.common.model;

import java.io.Serializable;

public class OutPutInf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int torId;
	private int drugId;
	private int uid;
	private String torTime;
	private String operation;
	private int amount;
	public OutPutInf(int torId, int drugId, int uid, String torTime, String operation, int amount) {
		super();
		this.torId = torId;
		this.drugId = drugId;
		this.uid = uid;
		this.torTime = torTime;
		this.operation = operation;
		this.amount = amount;
	}
	public OutPutInf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTorId() {
		return torId;
	}
	public void setTorId(int torId) {
		this.torId = torId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTorTime() {
		return torTime;
	}
	public void setTorTime(String torTime) {
		this.torTime = torTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}
