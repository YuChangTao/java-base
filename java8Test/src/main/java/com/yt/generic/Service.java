package com.yt.generic;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Service接口
 * 泛型接口
 */
public interface Service<T, ID extends Serializable> {

    /**
     * 通过主键查询实体
     *
     * @param pk
     * @return
     */
    T get(ID pk);

    /**
     * 通过主键集合查询实体
     *
     * @param pks
     * @return
     */
    List<T> get(Collection<ID> pks);

    /**
     * 插入/更新实体
     *
     * @param t
     */
    void save(T t);

    /**
     * 插入/更新实体集合
     *
     * @param ts
     */
    void save(Collection<T> ts);

    /**
     * 更新实体
     *
     * @param t
     */
    void update(T t);

    /**
     * 更新实体集合
     *
     * @param ts
     */
    void update(Collection<T> ts);

    /**
     * 删除实体
     *
     * @param t
     */
    void delete(T t);

    /**
     * 删除实体集合
     *
     * @param ts
     */
    void delete(Collection<T> ts);

    /**
     * 通过主键删除实体
     *
     * @param id
     */
    void deleteById(ID id);

    /**
     * 通过主键集合删除实体 注：这里别把List改为Collection，会导致覆盖方法的List<ID>调用不到
     *
     * @param idList
     */
    void deleteById(List<ID> idList);

}
