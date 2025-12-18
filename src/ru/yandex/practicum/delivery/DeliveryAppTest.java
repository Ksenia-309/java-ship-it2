package ru.yandex.practicum.delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryAppTest {
    private final ru.yandex.practicum.delivery.StandardParcel standardParcel = new ru.yandex.practicum.delivery.StandardParcel("Посылка Никите", 3,
            "Валенсия",
            25);
    private final FragileParcel fragileParcel = new FragileParcel("Посылка маме", 10,
            "Курган",
            21);
    private final PerishableParcel perishableParcel = new PerishableParcel("Посылка для бабушки",
            5,
            "Одесса",
            23,
            7);

    @Test
    public void shouldReturn6ForStandAndWeightIs3() {
        int weight = standardParcel.calculateDeliveryCost();
        Assertions.assertEquals(6, weight);

    }

    @Test
    public void shouldReturn40ForFragileAndWeightIs10() {
        int weight = fragileParcel.calculateDeliveryCost();
        Assertions.assertEquals(40, weight);

    }

    @Test
    public void shouldReturn15ForPerishableAndWeightIs5() {
        int weight = perishableParcel.calculateDeliveryCost();
        Assertions.assertEquals(15, weight);

    }

    @Test
    public void addStandParcelWhenUnderLimit(){
        ParcelBox<ru.yandex.practicum.delivery.StandardParcel> standardTest = new ParcelBox<>(100);
        ru.yandex.practicum.delivery.StandardParcel lightParcel = new ru.yandex.practicum.delivery.StandardParcel("Посылка Никите", 3,
                "Валенсия",
                25);
        boolean add = standardTest.addParcel(lightParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, standardTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

    @Test
    public void dontAddStandParcelWhenOverLimit(){
        ParcelBox<ru.yandex.practicum.delivery.StandardParcel> standardTest = new ParcelBox<>(100);
        ru.yandex.practicum.delivery.StandardParcel heavyParcel = new ru.yandex.practicum.delivery.StandardParcel("Посылка Никите", 300,
                "Валенсия",
                25);
        boolean add = standardTest.addParcel(heavyParcel);
        assertFalse(add, "Не должно получится");
        assertEquals(0, standardTest.getParcelCount(), "В коробке должно быть пусто!");

    }

    @Test
    public void addFrParcelWhenUnderLimit(){
        ParcelBox<FragileParcel> fragileTest = new ParcelBox<>(30);
        FragileParcel lightParcel = new FragileParcel("Посылка маме", 10,
                "Курган",
                21);
        boolean add = fragileTest.addParcel(lightParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, fragileTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

    @Test
    public void dontAddFrParcelWhenOverLimit(){
        ParcelBox<FragileParcel> fragileTest = new ParcelBox<>(30);
        FragileParcel heavyParcel = new FragileParcel("Посылка маме", 100,
                "Курган",
                21);
        boolean add = fragileTest.addParcel(heavyParcel);
        assertFalse(add, "Не должно получится");
        assertEquals(0, fragileTest.getParcelCount(), "В коробке должно быть пусто!");

    }

    @Test
    public void addPrParcelWhenUnderLimit(){
        ParcelBox<PerishableParcel> perTest = new ParcelBox<>(50);
        PerishableParcel lightParcel = new PerishableParcel("Посылка для бабушки",
                5,
                "Одесса",
                23,
                7);
        boolean add = perTest.addParcel(lightParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, perTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

    @Test
    public void dontAddPrParcelWhenOverLimit(){
        ParcelBox<PerishableParcel> perTest = new ParcelBox<>(50);
        PerishableParcel heavyParcel = new PerishableParcel("Посылка для бабушки",
                55,
                "Одесса",
                23,
                7);
        boolean add = perTest.addParcel(heavyParcel);
        assertFalse(add, "Не должно получится");
        assertEquals(0, perTest.getParcelCount(), "В коробке должно быть пусто!");

    }

    @Test
    public void isPerParselNotExpected() {
        PerishableParcel perTest = new PerishableParcel("Посылка для бабушки",
                55,
                "Одесса",
                10,
                7);
        int currentDay = 13;
        boolean result = perTest.isExpired(currentDay);
        assertFalse(result, "Не должна быть просрочена!");

    }

    @Test
    public void isPerParselExpected() {
        PerishableParcel perTest = new PerishableParcel("Посылка для бабушки",
                55,
                "Одесса",
                10,
                7);
        int currentDay = 20;
        boolean result = perTest.isExpired(currentDay);
        assertTrue(result, "Посылка просрочена!");

    }

}
