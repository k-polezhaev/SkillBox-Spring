package org.example.contactlist.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.contactlist.entity.Contact;
import org.example.contactlist.exception.ContactNotFoundException;
import org.example.contactlist.repository.mapper.ContactRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ContactRepository implements MyRepository<Contact> {
    private final JdbcTemplate jdbcTemplate;

    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long insert(Contact contact) {
        log.debug("Calling ContactRepository -> insert with contact: {}",contact);
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (id, first_name, last_name, phone, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail());
        return contact.getId();
    }
    @Override
    public Optional<Contact> selectById(long id) {
        log.debug("Calling ContactRepository -> selectById with ID{}",id);
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(),1))
        );
        return Optional.ofNullable(contact);
    }
    @Override
    public List<Contact> selectAll() {
        log.debug("Calling ContactRepository -> selectAll");
        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Calling ContactRepository -> update with contact {}", contact);
        Contact existedContact = selectById(contact.getId()).orElse(null);
        if (existedContact != null){
            String sql = "UPDATE contact SET first_name = ?, last_name = ?, phone = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getPhone(),
                    contact.getEmail(),
                    contact.getId());
            return contact;
        }
        log.warn("Contact with id {} not found!", contact.getId());
        throw new ContactNotFoundException("Contact not found for update! ID = " + contact.getId());
    }
    @Override
    public void removeById(long id) {
        log.debug("Calling ContactRepository -> removeById with id: {}", id);
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        log.debug("Calling ContactRepository -> batchInsert");
        String sql = "INSERT INTO contact (id, first_name, last_name, phone, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = contacts.get(i);
                ps.setLong(1,contact.getId());
                ps.setString(2, contact.getFirstName());
                ps.setString(3, contact.getLastName());
                ps.setString(4, contact.getPhone());
                ps.setString(5,contact.getEmail());
            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }
}
