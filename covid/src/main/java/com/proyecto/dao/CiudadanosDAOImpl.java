package com.proyecto.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.entity.Ciudadanos;
import com.proyecto.entity.Triaje;
import com.proyecto.entity.Usuarios;

@Repository
public class CiudadanosDAOImpl implements CiudadanosDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Ciudadanos registrarActualizaCiudadano(Ciudadanos c) {
		Session session=null;
		try {
			session=sessionFactory.openSession();
			session.getTransaction().begin();
			session.saveOrUpdate(c);//insert y update
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return c;
	}

	@Transactional(readOnly = true)
	public List<Triaje> listaCiudadanos() {
		Session session = null;
		List<Triaje> lista = null;
		try {
			session=sessionFactory.openSession();
			Query query = session.createQuery("select c from Triaje c");
			session.getTransaction().begin();
			lista = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.print("Error  en el metodo listaCiudadanos:" +e.toString());
			e.printStackTrace();
		}finally{
			session.close();
		}
		return lista;
	}

	@Transactional(readOnly = true)
	public Usuarios loginSistema(String pLogin, String pContrasenia) {
		// TODO Auto-generated method stub
				Session session = sessionFactory.getCurrentSession();;
				Usuarios oUsuario = null;
				try {
					String sql = "select u from Usuarios u where u.user=?1 and u.pass=?2";
					Query query = session.createQuery(sql);
					query.setParameter(1, pLogin);
					query.setParameter(2, pContrasenia);
					System.out.print("Conectandose Usuario..."+"\n");
					List list = query.getResultList();
					System.out.print("Query Ejecutado Usuario"+"\n");
					if((list!=null && list.size()>0)) {
						oUsuario = (Usuarios) query.getSingleResult();
						System.out.print("Usuario encontrado :)"+"\n");
						return oUsuario;
					}else {
						System.out.print("Usuario NO encontrado"+"\n");
						return oUsuario;
					}
				} catch (Exception e) {
					System.out.print("Error en CiudadanosDAOImpl.loginSistema"+"\n");
					e.printStackTrace();
				}
				return oUsuario;
	}
	
	@Transactional(readOnly = true)
	public Triaje findCiudadanos(int cod) {
		Triaje m=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			m=session.get(Triaje.class, cod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	@Transactional
	public Triaje registrarTriaje(Triaje c) {
		Session session=null;
		try {
			session=sessionFactory.openSession();
			session.getTransaction().begin();
			session.save(c);//insert y update
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return c;
	}

	@Override
	@Transactional
	public Triaje updateTriaje(Triaje c) {
		Session session=null;
		try {
			session=sessionFactory.openSession();
			session.getTransaction().begin();
			session.update(c);//insert y update
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return c;
	}
	
}