package pl.mazurmarcin.javastart.lec15.homework;

public enum OnlineStoreMenu {
	SORT(1), CHANGE_STATUS(2), ADD_ORDER(3), EXIT(0);

	private final int option;

	private OnlineStoreMenu(int option) {
		this.option = option;
	}

	public int getValue() {
		return option;
	}
	
	public static OnlineStoreMenu findByValue(int value) {
		
		for (OnlineStoreMenu onlineStoreMainMenu : OnlineStoreMenu.values()) {
			if(onlineStoreMainMenu.getValue() == value)
				return  onlineStoreMainMenu;
		}
		
		return null;
	}

}
