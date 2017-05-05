package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // Opcional padrao nao precisa fazer a anotaçoes
//@Interceptors({ LogInterceptador.class })
public class AutorDao {

	@PersistenceContext
	  private EntityManager manager ;
	
//	@Inject
//	UserTransaction tx;
	
	@PostConstruct
	void aposCriacao(){
		System.out.println("AutorDao foi criado");
		
	}

//	@TransactionAttribute(TransactionAttributeType.REQUIRED)//REQUIRED E Opcional padrao nao precisa fazer a anotaçoes
	//Ao usar o Dao e preciso ter uma transacao rodando quem faz essa chamada precisa se preocupar com isso e fazer uma transação rodar
	// REQUIRES_NEW indica que semprme de ter uma transação rodando caso exista a transação sera suspensa para abrir uma nova
	//MANDATORY => Verifica se ja existe uma transacao rodando caso contrario joga um exeção
	// TRANSAÇÃO NEVER indica que jamais e para haver transação em execução
	// BEAN porque o sessionBean gerencia a transação (Bean Managerd Transaction => BMT)
//	@TransactionAttribute(TransactionAttributeType.MANDATORY)
//	public void salva(Autor autor) {
//		System.out.println("Salvando Autor "+ autor.getNome());
////		
////		try {
////			Thread.sleep(20000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//
//		try {
//			tx.begin();
//			manager.persist(autor);
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("Salvou Autor "+ autor.getNome());
//	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salva(Autor autor) {
		System.out.println("Salvando Autor "+ autor.getNome());

			manager.persist(autor);
		
		System.out.println("Salvou Autor "+ autor.getNome());
		
		//Chamada ao serviço externo que gera um erro ou exerção
		
	//	throw new RuntimeException("Serviço externo deu erro");
		
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("Select a from Autor a", Autor.class ).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = manager.find(Autor.class, autorId);
		return autor;
	}
	
}
