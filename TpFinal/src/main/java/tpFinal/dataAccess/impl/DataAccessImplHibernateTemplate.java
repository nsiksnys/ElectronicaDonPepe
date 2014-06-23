package tpFinal.dataAccess.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

import tpFinal.dataAccess.DataAccessInterface;

import java.util.List;

public class DataAccessImplHibernateTemplate implements DataAccessInterface {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object get(Class entityClass, int id) {
		return this.hibernateTemplate.get(entityClass, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAll(Class entityClass) {
		return (List) this.hibernateTemplate.loadAll(entityClass);
	}

	@Override
	public void save(Object registro) {
		this.hibernateTemplate.save(registro);
	}

	@Override
	public void merge(Object registro) {
		this.hibernateTemplate.merge(registro);
	}

	@Override
	public void saveOrUpdate(Object registro) {
		this.hibernateTemplate.saveOrUpdate(registro);
	}

	@Override
	public void update(Object registro) {
		this.hibernateTemplate.update(registro);
	}

	@Override
	public void delete(Object registro) {
		this.hibernateTemplate.delete(registro);
	}
	
	@SuppressWarnings("rawtypes")
	public List find(String query)//TODO: por query o por criteria?
	{
		return this.hibernateTemplate.find(query);
	}
}