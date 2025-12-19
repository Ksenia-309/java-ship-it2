import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;


public class StandardParcelTest {

    private final StandardParcel standardParcel = new StandardParcel("Посылка Никите", 3,
            "Валенсия",
            25);

    @Test
    public void shouldReturn6ForStandAndWeightIs3() {
        int weight = standardParcel.calculateDeliveryCost();
        Assertions.assertEquals(6, weight);

    }

    @Test
    public void addStandParcelWhenUnderLimit(){
        ParcelBox<StandardParcel> standardTest = new ParcelBox<>(100);
        StandardParcel lightParcel = new StandardParcel("Посылка Никите",
                3,
                "Валенсия",
                25);
        boolean add = standardTest.addParcel(lightParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, standardTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

    @Test
    public void dontAddStandParcelWhenOverLimit(){
        ParcelBox<StandardParcel> standardTest = new ParcelBox<>(100);
        StandardParcel heavyParcel = new ru.yandex.practicum.delivery.StandardParcel("Посылка Никите",
                300,
                "Валенсия",
                25);
        boolean add = standardTest.addParcel(heavyParcel);
        assertFalse(add, "Не должно получится");
        assertEquals(0, standardTest.getParcelCount(), "В коробке должно быть пусто!");

    }

    @Test
    public void addStandParcelWhenBorderLimit(){
        ParcelBox<StandardParcel> standardTest = new ParcelBox<>(100);
        StandardParcel borderParcel = new StandardParcel("Посылка Никите",
                100,
                "Валенсия",
                25);
        boolean add = standardTest.addParcel(borderParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, standardTest.getParcelCount(), "В коробке должна быть одна посылка");

    }
}
