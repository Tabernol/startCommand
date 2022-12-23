package dao.impl;

import dao.Dao;
import dao.connection.MyDataSource;
import models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDao implements Dao<Test> {
    public void createTest(String name, long subjectId, int difficult, int duration) {
        String sql = "insert into test (id, subject_id, name, difficult, duration) values(default, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, subjectId);
            pst.setString(2, name);
            pst.setInt(3, difficult);
            pst.setInt(4, duration);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Test> getAll() {
        String sql = "select * from test";
        List<Test> tests;
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            tests = new ArrayList<>();
            Test test;
            while (resultSet.next()) {
                test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setSubjectId(resultSet.getLong("subject_id"));
                test.setName(resultSet.getString("name"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Test get(Long id) {
        String sql = "select * from test where id = ?";
        Test test = null;
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setSubjectId(resultSet.getLong("subject_id"));
                test.setName(resultSet.getString("name"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
            }
            return test;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {
        String sql = "delete from test where id = ?";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}