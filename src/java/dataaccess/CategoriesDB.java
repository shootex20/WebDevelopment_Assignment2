/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Categories;


/**
 *
 * @author 813017
 */
public class CategoriesDB {
    
          public List<Categories> getAll() throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
          TypedQuery<Categories> query = em.createNamedQuery("Categories.findAll", Categories.class);
         List<Categories> results = query.getResultList();
            return results;
    }
    
}
