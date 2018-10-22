package managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Produto;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ProdutoMB {
	private Produto produto = new Produto();
	private EntityManagerFactory emf;
	
	public ProdutoMB() {
		emf = Persistence.createEntityManagerFactory("PrCadastroProduto");
	}

	public void salvar() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			em.persist(produto);
		em.getTransaction().commit();
		em.close();
		produto = new Produto();
	}

	public List<Produto> getProdutos() {
		List <Produto> lista;
		EntityManager em = emf.createEntityManager();
		lista = em.
				createQuery("Select p from Produto p").
				getResultList();
		em.close();
		return lista;
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
