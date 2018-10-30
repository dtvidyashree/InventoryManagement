package dto;

public class InventoryManagementDTO {
	private String itemName;
	private Double costPrice;
	private Double sellingPrice;
	private Double quanity;
	private boolean active;

	public boolean isActive() {
		return true;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getQuanity() {
		return quanity;
	}

	public void setQuanity(Double quanity) {
		this.quanity = quanity;
	}

	@Override
	public String toString() {
		return "InventoryManagementDTO [itemName=" + itemName + ", costPrice=" + costPrice + ", sellingPrice="
				+ sellingPrice + ", quanity=" + quanity + ", active=" + active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((costPrice == null) ? 0 : costPrice.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((quanity == null) ? 0 : quanity.hashCode());
		result = prime * result + ((sellingPrice == null) ? 0 : sellingPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryManagementDTO other = (InventoryManagementDTO) obj;
		if (active != other.active)
			return false;
		if (costPrice == null) {
			if (other.costPrice != null)
				return false;
		} else if (!costPrice.equals(other.costPrice))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (quanity == null) {
			if (other.quanity != null)
				return false;
		} else if (!quanity.equals(other.quanity))
			return false;
		if (sellingPrice == null) {
			if (other.sellingPrice != null)
				return false;
		} else if (!sellingPrice.equals(other.sellingPrice))
			return false;
		return true;
	}
}
