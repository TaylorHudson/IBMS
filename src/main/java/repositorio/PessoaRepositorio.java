package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidade.Pessoa;

public class PessoaRepositorio {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	private EntityManager em = emf.createEntityManager();
	
	public void cadastrarAniversariante(Pessoa pessoa) {
		
		pessoa.setNome(pessoa.getNome().toUpperCase());

		try {
			Query query = em.createNativeQuery("SELECT nome FROM membros WHERE nome = '" + pessoa.getNome() + "'");
			String nome = (String) query.getSingleResult();
			JOptionPane.showMessageDialog(null, "Membro já cadastrado.");
		} catch (NoResultException e) {
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Cadastro Concluído.");
		}
    }
	
	public List<Pessoa> retornarPessoas() {
		Query query = em.createNativeQuery("SELECT * FROM membros ORDER BY nome",Pessoa.class);
		List<Pessoa> lista = query.getResultList();
		return lista;
    }
}
