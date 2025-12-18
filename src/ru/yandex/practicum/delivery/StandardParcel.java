package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {
    private static final int BASE_COST_FOR_STANDARD = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(deliveryAddress, description, weight, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST_FOR_STANDARD * weight;
    }

}
