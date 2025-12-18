package ru.yandex.practicum.delivery;

public abstract class Parcel {
    String description;
    int weight;
    String deliveryAddress;
    int sendDay;

    public Parcel(String deliveryAddress, String description, int weight, int sendDay) {
        this.deliveryAddress = deliveryAddress;
        this.description = description;
        this.weight = weight;
        this.sendDay = sendDay;
    }

    public void packageItem(Parcel parcel){
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver(Parcel parcel){
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public abstract int calculateDeliveryCost();

    public int getWeight() {
        return weight;
    }


}
