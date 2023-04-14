package dao.impl;

import common.DBConnectionPool;
import common.config.Configuration;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.vavr.control.Either;

@Log4j2
public class DaoNewspapers {

    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private PreparedStatement pstmt2;
    private final DBConnectionPool pool;

    @Inject
    public DaoNewspapers(DBConnectionPool pool) {
        this.pool = pool;
    }


}
