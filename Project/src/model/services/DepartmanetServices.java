package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Departament;

public class DepartmanetServices {
	public List<Departament> findAll(){
		List<Departament> list = new ArrayList<>();
		list.add(new Departament(1,"BOoks"));
		list.add(new Departament(2,"computers"));
		list.add(new Departament(3,"Eletronicos"));
		return list;
	}
}
