package pharmacy.web.store.model;

import java.util.List;

import pharmacy.web.store.model.dto.DrugWithdrawingInfDto;

public class DrugWithdrawingMessage {

	//数据
	private List<DrugWithdrawingInfDto> drugWithdrawingInfDtos;
	//页码
	private int pageNum;
	
	public DrugWithdrawingMessage() {
		// TODO Auto-generated constructor stub
	}

	public DrugWithdrawingMessage(List<DrugWithdrawingInfDto> drugWithdrawingInfDtos, int pageNum) {
		super();
		this.drugWithdrawingInfDtos = drugWithdrawingInfDtos;
		this.pageNum = pageNum;
	}

	public List<DrugWithdrawingInfDto> getDrugWithdrawingInfDtos() {
		return drugWithdrawingInfDtos;
	}

	public void setDrugWithdrawingInfDtos(List<DrugWithdrawingInfDto> drugWithdrawingInfDtos) {
		this.drugWithdrawingInfDtos = drugWithdrawingInfDtos;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrugWithdrawingMessage [drugWithdrawingInfDtos=");
		builder.append(drugWithdrawingInfDtos);
		builder.append(", pageNum=");
		builder.append(pageNum);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
