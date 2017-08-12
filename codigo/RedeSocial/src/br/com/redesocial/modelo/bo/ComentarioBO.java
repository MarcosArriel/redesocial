package br.com.redesocial.modelo.bo;

import br.com.redesocial.modelo.dao.ComentarioDAO;
import br.com.redesocial.modelo.dto.Comentario;

/**
 *
 * @author Adallberto Lucena
 */

public class ComentarioBO extends BOCRUDBase<Comentario, ComentarioDAO>{

    @Override
    protected ComentarioDAO instanciarDAO() {
        return new ComentarioDAO();
    }

    @Override
    protected void validarChavePrimaria(Comentario dto) throws Exception {
        if (dto.getId() == null) 
            throw new Exception("Preencha o campo id.");
    }

    @Override
    protected void validar(Comentario dto) throws Exception {
         if (dto.getDescricao().trim().equals("")) throw new Exception("Insira o comentario.");
         if (dto.getCurtidas()== null) throw new Exception("Insira as comentarios");
         if (dto.getData() == null) throw new Exception("Preencha o data da comentarios");
         
         if (dto.getPostagem() == null) throw new Exception("Preencha a postagem do comentario");       
       
    }
    
       
    
}
