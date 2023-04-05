package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    private String FIND_BY_USERNAME_SQL = "select * from user where username = ?";
    private String INSERT_USER_SQL = "insert into user (user_id, username, name) values (?, ?, ?)";
    
    public Optional<User> findUserByUsername(String username){

        SqlRowSet rs = template.queryForRowSet(FIND_BY_USERNAME_SQL, username);

        if (!rs.next()){
            return Optional.empty();
        }

        return Optional.of(Utils.toUser(rs));
    }
   
    public String insertUser(User user){

        String userId = UUID.randomUUID().toString().substring(0,8);

        if (user.getName()== null){
            user.setName(user.getUsername());
        }

        Integer insertedRows = template.update(INSERT_USER_SQL, userId, user.getUsername(),user.getName());

        if (insertedRows > 0){
            return userId;

        } else {

            return "Error";
        }
    }

}
