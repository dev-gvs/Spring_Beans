package kz.gvs.spring;

import java.util.List;
import javax.sql.DataSource;

public interface IRecordDAO {

    void setDataSource(DataSource ds);

    void insert(Record record);

    void append(String firstName, String lastName, String number);

    void deleteByLastName(String lastName);

    void delete(String firstName, String lastName);

    void deleteAll();

    void update(String oldLastName, String newLastName);

    Record findByNumber(String number);

    List<Record> selectAll();

}
