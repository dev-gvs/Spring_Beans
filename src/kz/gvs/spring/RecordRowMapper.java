package kz.gvs.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class RecordRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        RecordResultSetExtractor extractor = new RecordResultSetExtractor();
        return extractor.extractData(rs);
    }

    class RecordResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Record record = new Record();
            record.setId(rs.getInt(1));
            record.setFirstName(rs.getString(2));
            record.setLastName(rs.getString(3));
            record.setNumber(rs.getString(4));
            return record;
        }
    }
}
