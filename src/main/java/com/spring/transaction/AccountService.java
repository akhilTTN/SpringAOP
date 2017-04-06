package com.spring.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akhil on 29/3/17.
 */
@Service
public class AccountService {

    TempService tempService;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    @Autowired
    public TempService getTempService() {
        return tempService;
    }

    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    public void transferAmount(String sender, double balance, String accno1, String reciever, String accno2) {
        jdbcTemplate.queryForList("select balance from bank where name=? and accno=?", new Object[]{sender, accno1});
        jdbcTemplate.update("update bank set balance = balance + ? where accno=? and name =?", new Object[]{balance, accno2, reciever});
        jdbcTemplate.update("update bank set balance = balance - ? where accno=? and name =?", new Object[]{balance, accno1, sender});
    }

    public void transferAmountTransaction(String sender, double balance, String accno1, String reciever, String accno2) {
        TransactionDefinition transactionDefinition = new DefaultTransactionAttribute();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            jdbcTemplate.update("update bank set balance = balance + ? where accno=? and name =?", new Object[]{balance, accno2, reciever});
//            int i=1/0;
            jdbcTemplate.update("update bank set balance = balance - ? where accno=? and name =?", new Object[]{balance, accno1, sender});
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
        }
    }


    @Transactional(readOnly = true)
    public void transferAmountTransactionUsingAnnotationsWithReadOnly(String sender, double balance, String accno1, String reciever, String accno2) {

        jdbcTemplate.update("update bank set balance = balance + ? where accno=? and name =?", new Object[]{balance, accno2, reciever});
        jdbcTemplate.update("update bank set balance = balance - ? where accno=? and name =?", new Object[]{balance, accno1, sender});

    }

    public void addUser(double balance, String name, String accno, String mobile) {
        jdbcTemplate.update("insert into bank values(?,?,?,?)", new Object[]{name, accno, balance, mobile});
    }


    public void deleteUser(String accno) {
        jdbcTemplate.update("delete from bank where accno=?", new Object[]{accno});
    }

    public void getAll() {
        jdbcTemplate.query("select * from bank", new ResultSetExtractor<UserAccount>() {

            public UserAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + "\t" + rs.getString("accno") + "\t" + rs.getString("mobile") + "\t" + rs.getDouble("balance"));
                }
                return null;
            }
        });
    }

    public void getAll1() {
        jdbcTemplate.query("select * from bank", new RowMapper<UserAccount>() {
            public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + "\t" + rs.getString("accno") + "\t" + rs.getString("mobile") + "\t" + rs.getDouble("balance"));
                }
                return null;
            }
        });
    }


    public void showDetails(String accno) {
        UserAccount userAccount = jdbcTemplate.queryForObject("select * from bank where accno=?", new Object[]{accno}, new RowMapper<UserAccount>() {
            public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAccount userAccount = new UserAccount();
                userAccount.setAcount_no(rs.getString("accno"));
                userAccount.setName(rs.getString("name"));
                userAccount.setBalance(rs.getDouble("balance"));
                userAccount.setMobile_no(rs.getString("mobile"));
                return userAccount;
            }
        });
        System.out.println(userAccount.getAcount_no());
        System.out.println(userAccount.getBalance());
        System.out.println(userAccount.getMobile_no());
        System.out.println(userAccount.getName());
    }

    @Transactional
    public void transferAmountTransactionUsingAnnotations(String sender, double balance, String accno1, String reciever, String accno2) {

        jdbcTemplate.update("update bank set balance = balance + ? where accno=? and name =?", new Object[]{balance, accno2, reciever});
        int i = 1 / 0;
        jdbcTemplate.update("update bank set balance = balance - ? where accno=? and name =?", new Object[]{balance, accno1, sender});

    }


    @Transactional
    void updateFunds(String name, double balance) {
        System.out.println("inside updateFunds");
        jdbcTemplate.update("update funds set balance =balance + ? where name=?", new Object[]{balance, name});
    }

    @Transactional
    void runPropogationInRequiredNewMode() {
        System.out.println("inside runProp");

        updateFunds("akhil", 111);
        //  try{
//            tempService.transferToDummyTableWithRequiredNew();
        transferAmountTransactionUsingAnnotations("akhil", 100, "123456789", "gunjan", "123123123");

        //   }
        //    catch(Exception e){
//        e.printStackTrace();
        //   }
    }
}