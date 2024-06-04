package com.user.poc.Inventory;

import com.util.MiscUtil;

import java.util.logging.Logger;

public class InventoryService {
    private static Logger LOGGER = Logger.getLogger("InventoryService.class");

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean updateInventory(int productId, int quantity) {
        String instanceId = MiscUtil.getInstance();
        LOGGER.info("Update Inventory " + instanceId);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
            return true;
        } else {
            return false;
        }
    }

}

