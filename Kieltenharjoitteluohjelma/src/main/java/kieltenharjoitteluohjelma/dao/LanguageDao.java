package kieltenharjoitteluohjelma.dao;

import java.util.*;
import java.sql.*;

import java.util.HashMap;

public interface LanguageDao {

    HashMap<String, String> words(Integer language) throws SQLException;

    boolean addWord(Integer language, String finnish, String foreign) throws SQLException;
}
