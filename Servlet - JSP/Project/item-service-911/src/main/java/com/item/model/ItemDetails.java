package com.item.model;

public class ItemDetails {

	private Long itemId;
	private String description;
	private String category;

	public ItemDetails() {
	}

	public ItemDetails(Long itemId, String description, String category) {
		this.itemId = itemId;
		this.description = description;
		this.category = category;
	}

	public Long getItemId() { return itemId; }
	public void setItemId(Long itemId) { this.itemId = itemId; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
}
