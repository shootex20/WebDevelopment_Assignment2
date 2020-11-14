/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.util.List;
import models.Item;
import models.User;

/**
 *
 * @author 813017
 */
public class Inventory {
        public Item get(int id) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        Item hi = hiDB.get(id);
        return hi;
    }
    
    public List<Item> getAll(String username) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        List<Item> homeitems = hiDB.getAll(username);
        return homeitems;
    }
    
    public void insert(Integer itemID, String itemName, double price, String owner) throws Exception {
        Item hi = new Item(itemID, itemName, price);
        UserDB userDB = new UserDB();
        User user = userDB.get(owner);
        hi.setOwner(user);
        
        ItemsDB hiDB = new ItemsDB();
        hiDB.insert(hi);
    }
    
    public void update(Integer itemID, String itemName, double price, String owner) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        Item hi = hiDB.get(itemID);
        hi.setItemName(itemName);
        hi.setPrice(price);
        
        hiDB.update(hi);
    }
    
    public void delete(int id) throws Exception {
        ItemsDB hiDB = new ItemsDB();
        Item hi = hiDB.get(id);
        hiDB.delete(hi);
    }
}
