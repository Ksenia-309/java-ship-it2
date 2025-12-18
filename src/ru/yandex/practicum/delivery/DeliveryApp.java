package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(100);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(30);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(50);

    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<FragileParcel> trackingParcel = new ArrayList<>();
    private static final List<PerishableParcel> expiredParcel = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportFragileStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 6:
                    checkExpired();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Отследить местоположение хрупкой посылки");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("6 - Проверить срок годности скоропортящейся посылки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Введите тип посылки, которую Вы хотите отправить: ");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Хрупкая посылка");
        System.out.println("3 — Скоропортящаяся посылка");

        int typeParcel = Integer.parseInt(scanner.nextLine());

        switch (typeParcel) {
            case 1:
                System.out.println("Введите описание Вашей посылки: ");
                String descriptionStand = scanner.nextLine();
                System.out.println("Введите вес Вашей посылки: ");
                int weightStand = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес получателя: ");
                String deliveryAddressStand = scanner.nextLine();
                System.out.println("Введите дату отправки Вашей посылки: ");
                int sendDayStand = Integer.parseInt(scanner.nextLine());
                standardBox.addParcel(new StandardParcel(descriptionStand, weightStand,
                        deliveryAddressStand,
                        sendDayStand));
                allParcels.add(new StandardParcel(descriptionStand, weightStand,
                        deliveryAddressStand,
                        sendDayStand));
                break;
            case 2:
                System.out.println("Введите описание Вашей посылки: ");
                String descriptionFragile = scanner.nextLine();
                System.out.println("Введите вес Вашей посылки: ");
                int weightFragile = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес получателя: ");
                String deliveryAddressFragile = scanner.nextLine();
                System.out.println("Введите дату отправки Вашей посылки: ");
                int sendDayFragile = Integer.parseInt(scanner.nextLine());
                fragileBox.addParcel(new FragileParcel(descriptionFragile, weightFragile,
                        deliveryAddressFragile,
                        sendDayFragile));
                allParcels.add(new FragileParcel(descriptionFragile, weightFragile,
                        deliveryAddressFragile,
                        sendDayFragile));
                trackingParcel.add(new FragileParcel(descriptionFragile, weightFragile,
                        deliveryAddressFragile,
                        sendDayFragile));
                break;
            case 3:
                System.out.println("Введите описание Вашей посылки: ");
                String descriptionPer = scanner.nextLine();
                System.out.println("Введите вес Вашей посылки: ");
                int weightPer = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите адрес получателя: ");
                String deliveryAddressPer = scanner.nextLine();
                System.out.println("Введите дату отправки Вашей посылки: ");
                int sendDay = Integer.parseInt(scanner.nextLine());
                System.out.println("Укажите допустимый срок хранения Вашей посылки: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                perishableBox.addParcel(new PerishableParcel(descriptionPer, weightPer, deliveryAddressPer,
                        sendDay,
                        timeToLive));
                allParcels.add(new PerishableParcel(descriptionPer, weightPer, deliveryAddressPer,
                        sendDay,
                        timeToLive));
                expiredParcel.add(new PerishableParcel(descriptionPer, weightPer, deliveryAddressPer,
                        sendDay,
                        timeToLive));
                break;
            default:
                System.out.println("К сожалению, такого типа посылок у нас нет.");
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem(parcel);
            parcel.deliver(parcel);
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int totalStandardCost = 0;
        for (Parcel parcel : allParcels) {
            totalStandardCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalStandardCost);

    }

    private static void reportFragileStatus() {
        System.out.println("Для хрупких посылок возможно отслеживание местоположения, " +
                "хотите воспользоваться?");
        System.out.println("1 - Да");
        System.out.println("2 - Нет");
        int answer = Integer.parseInt(scanner.nextLine());
        switch (answer) {
            case 1:
                System.out.println("Список хрупких посылок для отслеживания: ");
                for (int i = 0; i < trackingParcel.size(); i++) {
                    System.out.println((i+1) + "-" + trackingParcel.get(i));
                }
                System.out.println("Введите номер Вашей посылки: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < trackingParcel.size()) {
                    System.out.println("Введите местоположение Вашей посылки: ");
                    String newLocation = scanner.nextLine();
                    trackingParcel.get(index).reportStatus(newLocation);
                } else {
                    System.out.println("Неверный номер посылки. Попробуйте еще раз.");
                }
                break;
            case 2:
                break;
            default:
                System.out.println("Извините, такого варианта нет. Попробуйте еще раз.");
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки, содержимое которой Вы хотели бы просмотреть: :");
        System.out.println("1 — Коробка со стандартными посылками");
        System.out.println("2 — Коробка с хрупкими посылками");
        System.out.println("3 — Коробка со скоропортящимися посылками");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                standardBox.showContents();
                break;
            case 2:
                fragileBox.showContents();
                break;
            case 3:
                perishableBox.showContents();
                break;
            default:
                System.out.println("Такого типа коробки у нас нет! Попробуйте еще раз.");
        }

    }

    private static void checkExpired(){
        System.out.println("Введите текущую дату: ");
        int currentDay = Integer.parseInt(scanner.nextLine());
        for(PerishableParcel pp : expiredParcel) {
            pp.isExpired(currentDay);
        }



    }

}

