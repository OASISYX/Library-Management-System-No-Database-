package com.book.dao;

import com.book.frame.MainFrame;
import com.book.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public static final Object[] columnNames = { "Room Name", "Status","Maximum number of users","Reasons for use","User"};
    public static final List<Object[]> data = new ArrayList<>();

    static {
        add(new Object[] { "Room 1", "Status: Unused" ,"5","Unused","null"});
        add(new Object[] { "Room 2", "Status: Occupied","2","Personal Use","BC213123"});
        add(new Object[] { "Room 3", "Status: Occupied","5","Group Use","BC123132" });
        add(new Object[] { "Room 4", "Status: Occupied","2","Personal Use","BB435435" });
        add(new Object[] { "Room 5", "Status: Unused","2","Unused","null" });
        add(new Object[] { "Room 6", "Status: Unused","5","Unused","null" });
        add(new Object[] { "Room 7", "Status: Unused","8","Unused","null" });
        add(new Object[] { "Room 8", "Status: Unused","12","Unused","null" });
    }
    //Updating of rooms
    public static void updateRoom(String name, String reason, User user) {
        Object[] room = findByName(name);
        if (room != null) {
            room[3] = reason;
            room[4] = user.getId();
        }
    }
    //Approval to borrow a room
    public static void approveBookRoom(String name) {
        Object[] room = findByName(name);
        if (room != null) {
            room[1] = "Status: Occupied";
        } else {
            System.out.println("Room not found: " + name);
        }
    }
    public static Object[][] toList(List<Object[]> list) {
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    //Find room information by room name
    public static Object[] findByName(String name) {
        for (Object[] d : data) {
            if (d[0].equals(name)) {
                return d;
            }
        }
        return null;
    }

    public static void add(Object[] obj) {
        data.add(obj);
    }
}