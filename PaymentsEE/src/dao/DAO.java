package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;

public interface DAO<T> {
    T save(T t) throws SQLException;
    T get(Serializable id) throws SQLException, ParseException;
    void update(T t) throws  SQLException;
    int delete (Serializable id) throws SQLException;
}
