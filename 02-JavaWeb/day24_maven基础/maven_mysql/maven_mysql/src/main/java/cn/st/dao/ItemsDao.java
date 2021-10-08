package cn.st.dao;

import cn.st.domain.Items;

import java.util.List;

public interface ItemsDao {
    public List<Items> findAll() throws Exception;
}
