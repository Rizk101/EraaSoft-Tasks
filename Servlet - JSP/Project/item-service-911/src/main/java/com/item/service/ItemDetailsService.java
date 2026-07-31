package com.item.service;

import java.util.Set;

import com.item.model.ItemDetails;

public interface ItemDetailsService {

	boolean addItemDetails(ItemDetails itemDetails);

	boolean updateItemDetails(ItemDetails itemDetails);

	ItemDetails getItemDetailsByItemId(Long itemId);

	Set<Long> getItemIdsWithDetails();

	boolean removeItemDetailsByItemId(Long itemId);
}
