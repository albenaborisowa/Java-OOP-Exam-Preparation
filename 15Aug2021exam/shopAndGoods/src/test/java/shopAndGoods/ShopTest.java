package shopAndGoods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop testShop;

    @Before
    public void setUp() {
        testShop = new Shop();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetShelves_ShouldReturn_UnmodifiableCollection() {
        testShop.getShelves().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods_ShouldFail_ForInvalidShelve() throws OperationNotSupportedException {
        testShop.addGoods("invalid_test_shelve", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods_ShouldFail_ForExistingGood() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        testShop.addGoods("Shelves1", goods);
        testShop.addGoods("Shelves1", goods);
    }

    @Test
    public void test_AddGoods_ShouldReturn_CorrectMessage_OnAddition()
            throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        String expected = "Goods: test_code is placed successfully!";
        String actual = testShop.addGoods("Shelves1", goods);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods_ShouldFail_ForInvalidShelve() throws OperationNotSupportedException {
        testShop.removeGoods("invalid_test_shelve", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods_ShouldFail_ForDifferentGood_OnTheSameShelve()
            throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        Goods goodsOther = new Goods("test_good_other", "test_code_other");
        testShop.addGoods("Shelves1", goods);
        testShop.removeGoods("Shelves1", goodsOther);
    }

    @Test
    public void test_RemoveGoods_ShouldReturn_CorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        testShop.addGoods("Shelves1", goods);
        String expected = "Goods: test_code is removed successfully!";
        String actual = testShop.removeGoods("Shelves1", goods);
        assertEquals(expected, actual);
    }

    @Test
    public void test_RemoveGoods_ShouldSet_TheShelveValue_ToNull()
            throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        testShop.addGoods("Shelves1", goods);
        testShop.removeGoods("Shelves1", goods);

        Goods emptySlot = testShop.getShelves().get("Shelves1");

        assertNull(emptySlot);
    }

}