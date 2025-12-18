package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private static final int BASE_COST_FOR_PERISHABLE = 3;
    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(deliveryAddress, description, weight, sendDay);
        this.timeToLive = timeToLive;
    }

    protected boolean isExpired(int currentDay) {
        int sum = sendDay + timeToLive;
        if(sum >= currentDay) {
            System.out.println("Срок годности посылки еще не истек!");
            return false;
        } else {
            System.out.println("Посылка испортилась!");
            return true;
        }
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST_FOR_PERISHABLE * weight;
    }
}
