package pharmacy.web.management.model.dto;

import java.util.List;

import pharmacy.web.management.model.Port;
/**
 * 	端口菜单权限获取
 * @author ZQH
 *
 */
public class PortAndMenuAndLimitDto extends Port{

	private List<MenuDto> listDtos;
	
	public PortAndMenuAndLimitDto() {
		// TODO Auto-generated constructor stub
	}

	public PortAndMenuAndLimitDto(List<MenuDto> listDtos) {
		super();
		this.listDtos = listDtos;
	}

	public List<MenuDto> getListDtos() {
		return listDtos;
	}

	public void setListDtos(List<MenuDto> listDtos) {
		this.listDtos = listDtos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PortAndMentAndLimitDto [listDtos=");
		builder.append(listDtos);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
