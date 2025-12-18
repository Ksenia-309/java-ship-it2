package ru.yandex.practicum.delivery;
import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();
    private final int maxWeight;
    private int startWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        int parcelWeight = parcel.getWeight();
        if (startWeight + parcelWeight <= maxWeight){
            parcels.add(parcel);
            startWeight += parcelWeight;
            return true;
        } else {
            System.out.println("Допустимый вес коробки превышен! Посылка не будет добавлена.");
            return false;
        }
    }

    public List<T> getParcels() {
        return parcels;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getStartWeight() {
        return startWeight;
    }

    public void showContents() {
        System.out.println("Содержимое коробки: " + parcels.size() + ". Вес коробки: " + startWeight);
    }

    public int getParcelCount(){
        return parcels.size();
    }
}
