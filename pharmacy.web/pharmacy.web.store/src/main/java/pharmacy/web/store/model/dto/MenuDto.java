package pharmacy.web.store.model.dto;

import java.util.List;

import pharmacy.common.model.Limit;
import pharmacy.common.model.StairMenu;

public class MenuDto extends StairMenu {

	private List<Limit> secondMenu;

	
	
	public MenuDto(String menuid, String menuname, String icon, String pid, List<Limit> secondMenu) {
		super(menuid, menuname, icon, pid);
		this.secondMenu = secondMenu;
	}

	public MenuDto() {
	}

	public List<Limit> getSecondMenu() {
		return secondMenu;
	}

	public void setSecondMenu(List<Limit> secondMenu) {
		this.secondMenu = secondMenu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuDto [secondMenu=");
		builder.append(secondMenu);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
