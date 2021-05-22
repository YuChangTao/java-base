package com.yt.generic;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Service基类，实现了数据的CRUD
 * 泛型类
 */
public abstract class ServiceImpl<DAO extends Dao<T,ID>,ID extends Serializable,T extends PersistentObject>
        implements Service<T,ID>{


    /**
     * 由子类注入实体DAO
     */
    protected DAO dao;

    public abstract void setDao(DAO dao);

    /**
     * 通过主键查询实体
     *
     * @param pk
     * @return
     */
    @Override
    public T get(ID pk) {
        return dao.get(pk);
    }

    /**
     * 通过主键集合查询实体
     *
     * @param pks
     * @return
     */
    @Override
    public List<T> get(Collection<ID> pks) {
        List<T> list = new ArrayList<T>(pks.size());
        for (ID pk : pks) {
            list.add(get(pk));
        }
        return list;
    }

    /**
     * 插入/更新实体
     *
     * @param t
     */
    @Override
    public void save(T t) {
        if (t.getId() == null) {
            dao.insert(t);
        } else {
            dao.update(t);
        }
    }

    /**
     * 插入/更新实体集合
     *
     * @param ts
     */
    @Override
    public void save(Collection<T> ts) {
        for (T t : ts) {
            save(t);
        }
    }

    /**
     * 更新实体
     *
     * @param t
     */
    @Override
    public void update(T t) {
        verifyRows(dao.update(t), 1, "数据库更新失败");
    }

    /**
     * 更新实体集合
     *
     * @param ts
     */
    @Override
    public void update(Collection<T> ts) {
        for (T t : ts) {
            update(t);
        }
    }

    /**
     * 删除实体
     *
     * @param t
     */
    @Override
    public void delete(T t) {
        deleteById((ID) t.getId());
    }

    /**
     * 删除实体集合
     *
     * @param ts
     */
    @Override
    public void delete(Collection<T> ts) {
        for (T t : ts) {
            delete(t);
        }
    }

    /**
     * 通过主键删除实体
     *
     * @param id
     */
    @Override
    public void deleteById(ID id) {
        deleteById(Arrays.asList(id));
    }

    /**
     * 通过主键集合删除实体 注：这里别把List改为Collection，会导致覆盖方法的List<ID>调用不到
     *
     * @param idList
     */
    @Override
    public void deleteById(List<ID> idList) {
        verifyRows(dao.deleteById(idList), idList.size(), "数据库删除失败");
    }

    /**
     * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
     *
     * @param updateRows
     * @param rows
     * @param message
     */
    protected void verifyRows(int updateRows, int rows, String message) {
        if (updateRows != rows) {
            System.out.println(("need update is" + rows + ", but real update rows is " + updateRows + "."));
        }
    }
}
