package gifts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift gift;

    @Before
    public void setUp() {
        this.giftFactory = new GiftFactory();
        this.gift = new Gift("Car", 1.1);
    }

    @Test
    public void test_getCount() {
        this.giftFactory.createGift(this.gift);
        assertEquals(1, this.giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_createPresent_WithNULL() {
        this.giftFactory.createGift(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createException_EqualName() {
        Gift presentNew = new Gift("Car", 1.1);
        this.giftFactory.createGift(this.gift);
        this.giftFactory.createGift(presentNew);
    }

    @Test
    public void test_createPresent() {
        this.giftFactory.createGift(this.gift);
        assertEquals("Car", this.gift.getType());
    }

    @Test(expected = NullPointerException.class)
    public void test_remove_NullName() {
        this.giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_remove_NullName_Empty() {
        this.giftFactory.removeGift("     ");
    }

    @Test
    public void test_remove() {
        this.giftFactory.createGift(this.gift);
        assertTrue(this.giftFactory.removeGift("Car"));
    }

    @Test
    public void test_getPresent_With_LeastMagic() {
        Gift presentNew = new Gift("Car0", 0.1);
        this.giftFactory.createGift(this.gift);
        this.giftFactory.createGift(presentNew);

        assertEquals(presentNew, this.giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void test_get_Present() {
        Gift presentNew = new Gift("Car0", 0.1);
        this.giftFactory.createGift(this.gift);
        this.giftFactory.createGift(presentNew);

        assertEquals(this.gift, this.giftFactory.getPresent("Car"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_IfTrue_ToModifiedCollection_ThrowException() {
        this.giftFactory.createGift(this.gift);
        this.giftFactory.getPresents().remove(this.gift);
    }
}
