package kz.gvs.spring;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class RecordDAO implements IRecordDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public void insert(Record record) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO my_phonebook (firstName, lastName, number) VALUES(?, ?, ?)",
                new Object[]{record.getFirstName(), record.getLastName(), record.getNumber()});
    }

    @Override
    public void append(String firstName, String lastName, String number) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO my_phonebook (firstName, lastName, number) VALUES(?, ?, ?)",
                new Object[]{firstName, lastName, number});
    }

    @Override
    public void deleteByLastName(String lastName) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM my_phonebook WHERE lastName LIKE ?", new Object[]{'%' + lastName + '%'});
    }

    @Override
    public void delete(String firstName, String lastName) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute((TransactionStatus status) -> {
            try {
                JdbcTemplate delete = new JdbcTemplate(dataSource);
                delete.update("DELETE FROM my_phonebook WHERE firstName = ? AND lastName = ?", new Object[]{firstName, lastName});
            } catch (RuntimeException e) {
                status.setRollbackOnly();
                throw e;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @Override
    public void deleteAll() {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM my_phonebook");
    }

    @Override
    public void update(String oldLastName, String newLastName) {
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE my_phonebook SET lastName = ? WHERE lastName = ?", new Object[]{newLastName, oldLastName});
    }

    @Override
    public Record findByNumber(String number) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<Record> records = select.query("SELECT * FROM my_phonebook WHERE number = ?",
                new Object[]{number}, new RecordRowMapper());
        return records.size() > 0 ? records.get(0) : null;
    }

    @Override
    public List<Record> selectAll() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM my_phonebook", new RecordRowMapper());
    }

}
