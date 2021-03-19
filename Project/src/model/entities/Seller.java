package model.entities;

import java.util.Date;

public class Seller {
	private Integer id;
	private String nome;
	private String email;
	private Date birgthDate;
	private Integer departId;

	public Seller(Integer id, String nome, String email, Date birgthDate, Integer departId) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.birgthDate = birgthDate;
		this.departId = departId;
	}
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirgthDate() {
		return birgthDate;
	}
	public void setBirgthDate(Date birgthDate) {
		this.birgthDate = birgthDate;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", nome=" + nome + ", email=" + email + ", birgthDate=" + birgthDate + ", departId="
				+ departId + "]";
	}
	
	
	
	
	
}
