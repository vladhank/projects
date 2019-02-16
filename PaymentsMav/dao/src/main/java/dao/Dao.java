package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface Dao<T> {

    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(T t);

    void refresh(T t);

//    Cli findByLogin(String login7);

//    List<T> getAll();
}
