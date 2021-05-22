package com.yt.generic;

import java.io.Serializable;
import java.util.Collection;

/**
 * 泛型接口
 * @param <T>
 * @param <ID>
 */
public interface Dao<T,ID extends Serializable> {


    /**
     * 通过主键查询实体
     *
     * @param pk
     * @return T
     */
    T get(ID pk);

    /**
     * 插入实体
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 更新实体
     *
     * @param t
     */
    int update(T t);

    /**
     * 主键集合批量删除实体
     *
     * @param idList
     * @return
     */
    int deleteById(Collection<ID> idList);
}
