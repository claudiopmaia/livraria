package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {
	
	@Inject
	AutorDao dao;
	
	
	//Bean tem muito codigo relacionado ao JSF
	//Serviço e o contralador na regra de negocio
	//Dao possui o codigo de infrainstrutura
	
	
	//por padrao transaction atribute e requerid ou seja ao chamar o metodo adiciona sera aberto uma nova transação
	public void adiciona(Autor autor) throws LivrariaException{
		
		dao.salva(autor);
		
		// uma regra de negocio deu  errado
		
		//throw new LivrariaException();
	}


	public List<Autor> todosAutores() {
		return dao.todosAutores();
	}

}
