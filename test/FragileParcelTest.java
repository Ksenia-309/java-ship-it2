import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;

import static org.junit.jupiter.api.Assertions.*;

public class FragileParcelTest {

    private final FragileParcel fragileParcel = new FragileParcel("Посылка маме",
            10,
            "Курган",
            21);

    @Test
    public void shouldReturn40ForFragileAndWeightIs10() {
        int weight = fragileParcel.calculateDeliveryCost();
        Assertions.assertEquals(40, weight);

    }

    @Test
    public void addFrParcelWhenUnderLimit(){
        ParcelBox<FragileParcel> fragileTest = new ParcelBox<>(30);
        FragileParcel lightParcel = new FragileParcel("Посылка маме",
                10,
                "Курган",
                21);
        boolean add = fragileTest.addParcel(lightParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, fragileTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

    @Test
    public void dontAddFrParcelWhenOverLimit(){
        ParcelBox<FragileParcel> fragileTest = new ParcelBox<>(30);
        FragileParcel heavyParcel = new FragileParcel("Посылка маме",
                100,
                "Курган",
                21);
        boolean add = fragileTest.addParcel(heavyParcel);
        assertFalse(add, "Не должно получится");
        assertEquals(0, fragileTest.getParcelCount(), "В коробке должно быть пусто!");

    }

    @Test
    public void addFrParcelWhenBorderLimit(){
        ParcelBox<FragileParcel> fragileTest = new ParcelBox<>(30);
        FragileParcel borderParcel = new FragileParcel("Посылка маме",
                30,
                "Курган",
                21);
        boolean add = fragileTest.addParcel(borderParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, fragileTest.getParcelCount(), "В коробке должна быть одна посылка");

    }

}
