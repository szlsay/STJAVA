package cn.st.test;

import cn.st.dao.Impl.ItemsDaoImpl;
import cn.st.dao.ItemsDao;
import cn.st.domain.Items;
import org.junit.Test;

import java.util.List;

public class ItemsTest {
    @Test
    public void findAll() throws  Exception{
        ItemsDao itemsDao = new ItemsDaoImpl();
        List<Items> list = itemsDao.findAll();
        for (Items items : list) {
            System.out.println(items.getName());
        }
    }
}
