package pharmacy.web.warehouse.model.dto;

import pharmacy.common.model.DrugInf;

public class MiniSetDto extends DrugInf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int psum;
	private int minimunVal;
	public MiniSetDto(int psum, int minimunVal) {
		super();
		this.psum = psum;
		this.minimunVal = minimunVal;
	}
	public MiniSetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPsum() {
		return psum;
	}
	public void setPsum(int psum) {
		this.psum = psum;
	}
	public int getMinimunVal() {
		return minimunVal;
	}
	public void setMinimunVal(int minimunVal) {
		this.minimunVal = minimunVal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
