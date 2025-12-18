package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{
    private static final int BASE_COST_FOR_FRAGILE = 4;

    @Override
    public String toString() {
        return "хрупкая{" +
                "описание='" + description + '\'' +
                ", вес=" + weight +
                ", адрес получателя='" + deliveryAddress + '\'' +
                ", дата отправки=" + sendDay +
                '}';
    }

    @Override
    public void packageItem(Parcel parcel) {
        System.out.println("Посылка " + parcel + " обёрнута в защитную пленку");
        super.packageItem(parcel);
    }

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(deliveryAddress, description, weight, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST_FOR_FRAGILE * weight;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + description + "изменила местоположение на " + newLocation);
    }
}
