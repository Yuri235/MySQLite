package ua.init.team.mysqlite.sqlite;

import java.util.List;

/**
 * Created by dev on 21.09.17.
 */

public interface MyDBHelper {

    public void addModels(String name, String secondName,String bDay);
    public String getModels(int id);
    public List<String> getAllModels();
//    int[] getAllID();

}
