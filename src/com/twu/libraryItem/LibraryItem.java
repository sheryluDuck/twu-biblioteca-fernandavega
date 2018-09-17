package com.twu.libraryItem;

public interface LibraryItem {

    ItemAvailability getStatus();

    void setStatus(ItemAvailability itemAvailability);

    boolean isAvailable();

    String getItemName();

}
