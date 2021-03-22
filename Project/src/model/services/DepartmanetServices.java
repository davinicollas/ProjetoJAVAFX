package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Departament;

public class DepartmanetServices {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	public List<Departament> findAll(){
	return dao.findAll();
	}
}
