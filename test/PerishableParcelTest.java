import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;

public class PerishableParcelTest {

    private final PerishableParcel perishableParcel = new PerishableParcel("Посылка для бабушки",
            5,
            "Одесса",
            23,
            7);

    @Test
    public void shouldReturn15ForPerishableAndWeightIs5() {
        int weight = perishableParcel.calculateDeliveryCost();
        Assertions.assertEquals(15, weight);

    }

    @Test
    public void addPerParcelWhenUnderLimit(){
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
    public void dontAddPerParcelWhenOverLimit(){
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
    public void dontAddPerParcelWhenBorderLimit(){
        ParcelBox<PerishableParcel> perTest = new ParcelBox<>(50);
        PerishableParcel borderParcel = new PerishableParcel("Посылка для бабушки",
                50,
                "Одесса",
                23,
                7);
        boolean add = perTest.addParcel(borderParcel);
        assertTrue(add, "Должно получится");
        assertEquals(1, perTest.getParcelCount(), "В коробке должна быть одна посылка");

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

    @Test
    public void isPerParselBorderExpected() {
        PerishableParcel perTest = new PerishableParcel("Посылка для бабушки",
                55,
                "Одесса",
                10,
                7);
        int currentDay = 17;
        boolean result = perTest.isExpired(currentDay);
        assertFalse(result, "Посылка еще не просрочена, но время практически на исходе! Стоит поторопиться!");

    }
}
