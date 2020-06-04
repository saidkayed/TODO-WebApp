/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Entity.Todo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sidad
 */
public class TodoFacade implements Serializable {

    public TodoFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Todo todo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(todo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

        public void edit(Todo todo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            todo = em.merge(todo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = todo.getId();
                if (findTodo(id) == null) {
                    throw new NonexistentEntityException("The todo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Todo todo;
            try {
                todo = em.getReference(Todo.class, id);
                todo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The todo with id " + id + " no longer exists.", enfe);
            }
            em.remove(todo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Todo> findTodoEntities() {
        return findTodoEntities(true, -1, -1);
    }

    public List<Todo> findTodoEntities(int maxResults, int firstResult) {
        return findTodoEntities(false, maxResults, firstResult);
    }

    private List<Todo> findTodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Todo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Todo findTodo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Todo.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Todo> getAllTodo(){
        EntityManager em = getEntityManager();
        
        try {
        
        Query q = em.createQuery("SELECT t FROM Todo t", Todo.class);
        List<Todo> list = new ArrayList<>();
        list = q.getResultList();
        return list;
        } finally {
            em.close();
        }
        
        
    }

    public int getTodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Todo> rt = cq.from(Todo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        TodoFacade tf = new TodoFacade(emf);
          
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        
        Todo t = new Todo("Lean", "learn Py", date);
        tf.create(t);
        System.out.println(tf.getAllTodo());
        
    }
}
