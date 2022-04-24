package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MemberDao {

    public JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * selectByEmail 과 selectAll 메서드에서 사용할 RowMapper.mapRow 재정의
     */

    public class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
            );
            member.setId(rs.getLong("ID"));
            return member;
        }
    }

    public Member selectByEmail(String email){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?", new MemberRowMapper(), email);      // email 이 쿼리문의 ? 를 담당

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)",
                        new String[]{"ID"});

                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                return pstmt;
            }
        }, keyHolder);

        /**
         * prepareStatement 의 두 번째 파라미터로 new String[] {"ID"} 라고 해 주었다.
         * 자동 생성되는 키 컬럼 목록을 지정할 때 사용한다. (지금 사용하고자 하는 MEMBER 테이블의 자동 증가 키 칼럼 이름은 "ID")
         *
         * 그리고 JdbcTemplate 의 update 메서드는 PreparedStatement 실행 후
         * 자동 생성된 키 값을 두 번째 파라미터인 keyHolder 객체에 보관한다.
         */

        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());


        /**
         * 람다식을 사용하여 아래와 같이 작성할 수도 있다.
         */

        /*
        jdbcTemplate.update( (Connection con) -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)",
                    new String[]{"ID"});

            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

            return pstmt;
        }, keyHolder);

        */

    }

    public void update(Member member){
        jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                            member.getName(),
                            member.getPassword(),
                            member.getEmail() );

        /**
         * PreparedStatement 의 set 메서드로 직접 파라미터의 값을 설정해줄 수도 있다.
         * jdbcTemplate.update(new PreparedStatementCreator() {
         *             @Override
         *             public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
         *                 PreparedStatement pstmt = conn.prepareStatement(
         *                         "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)"
         *                 );
         *
         *                 pstmt.setString(1, member.getEmail());
         *                 pstmt.setString(2, member.getPassword());
         *                 pstmt.setString(3, member.getName());
         *                 pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
         *                 return pstmt;
         *             }
         *         });
         */

    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER", new MemberRowMapper());
        return results;
    }

    /**
     * queryForObject() : 결과가 1행인 경우 사용할 수 있음. 두 번째 파라미터는 컬럼을 읽어올 때 사용할 타입 지정
     *                    결과가 정확히 1행이 아니면 Exception 발생
     */
    public int count() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
        return count;

        /**
         * 실행 결과에 컬럼이 두 개 이상이면 RowMapper를 파라미터로 전달해서
         * 원하는 타입의 결과로 반환받을 수 있다.
         *
         * Member member = jdbcTemplate.queryForObject(
         *                 "select * from MEMBER where ID = ?",
         *                 new RowMapper<Member>() {
         *                     @Override
         *                     public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
         *                         Member member = new Member(
         *                                 rs.getString("EMAIL"),
         *                                 rs.getString("PASSWORD"),
         *                                 rs.getString("NAME"),
         *                                 rs.getTimestamp("REGDATE").toLocalDateTime()
         *                                 );
         *
         *                         member.setId(rs.getLong("ID"));
         *                         return member;
         *                     }
         *                 }, 100);
         *
         *
         */

    }

}
