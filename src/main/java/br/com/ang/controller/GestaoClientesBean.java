package br.com.ang.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.ang.model.Cliente;
import br.com.ang.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class GestaoClientesBean {
	private Cliente cliente;
	private List<Cliente> clientes;

	public void inserir() {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		cliente = em.merge(cliente);
		em.persist(cliente);
		tx.commit();
		em.close();
		cliente = new Cliente();
		atualizar();
	}

	public void removerRegistro() {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		cliente = em.merge(cliente);
		em.remove(cliente);
		tx.commit();
		em.close();
		atualizar();
		cliente = new Cliente();
	}

	public void inserirMensagem() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Cadastro realizado com sucesso!", "Cliente cadastro com sucesso."));
		System.out.println("mensagem inserida");
	}

	public GestaoClientesBean() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void atualizar() {
		EntityManager em = HibernateUtil.getEntityManager();
		Query q = em.createNativeQuery("select * from cliente", Cliente.class);
		this.clientes = q.getResultList();
		em.close();
	}
	
	@PostConstruct
	public void Construct () {
		atualizar();
	}
	
	public List<Cliente> getClientes() {
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getNome());
		}

		return clientes;
	}

}
