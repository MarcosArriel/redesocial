package br.com.redesocial.modelo.dao;

import br.com.redesocial.modelo.dto.Postagem;
import br.com.redesocial.modelo.dto.PostagemMultimidia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para conexão com o banco de dados
 * @author Daniel, Macilon
 */
public class PostagemMultimidiaDAO extends DAOBase {

    public void inserir(PostagemMultimidia dto) throws Exception {
        Connection conexao = getConexao();
        
        if(dto.getPostagem() == null || dto.getMultimidia() == null){
            throw new Exception("A descrição ou a mídia não podem estar vazias.");
        }
        
        PreparedStatement pstmt;
        
        pstmt = conexao.prepareStatement("insert into postagens_multimidias(postagem, multimidia) values (?, ?)");
        
        pstmt.setInt(1, dto.getPostagem().getId());
        pstmt.setInt(2, dto.getMultimidia().getId());
        
        pstmt.executeQuery();
        
        
    }

    public void alterar(PostagemMultimidia dto, PostagemMultimidia dtoNovo) throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("update postagens_multimidias set postagem = ?, multimidia = ? where postagem = ? and multimidia = ?");
        
        pstmt.setInt(1, dtoNovo.getPostagem().getId());
        pstmt.setInt(2, dtoNovo.getMultimidia().getId());
        pstmt.setInt(3, dto.getPostagem().getId());
        pstmt.setInt(4, dto.getMultimidia().getId());
        
        pstmt.executeQuery();
    }

    public PostagemMultimidia selecionar(int postagem, int multimidia) throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("select * from postagens_multimidas where postagem = ? and multimidia = ?");
        pstmt.setInt(1, postagem);
        pstmt.setInt(2, multimidia);
        
        ResultSet rs;
        rs = pstmt.executeQuery();
        
        MultimidiaDAO multimidiaDAO = new MultimidiaDAO();
        PostagemDAO postagemDAO = new PostagemDAO();
        
        if (rs.next()){
            PostagemMultimidia pm = new PostagemMultimidia();
            pm.setPostagem(postagemDAO.selecionar(rs.getInt("postagem")));
            pm.setMultimidia(multimidiaDAO.selecionar(rs.getInt("multimidia")));
            
            return pm;
        } else {
            return null;
        }
    }

    public List listar() throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("select * from postagens order by data desc");
        ResultSet rs;
        rs = pstmt.executeQuery();
        
        PostagemDAO postagemDAO = new PostagemDAO();
        MultimidiaDAO multimidiaDAO = new MultimidiaDAO();
        List lista;
        lista = new ArrayList();
                
        while(rs.next()){
            PostagemMultimidia pm = new PostagemMultimidia();
            pm.setPostagem(postagemDAO.selecionar(rs.getInt("postagem")));
            pm.setMultimidia(multimidiaDAO.selecionar(rs.getInt("multimidia")));
        }
        
        return lista;
    }

    public void excluir(int postagem, int multimidia) throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("delete from postagens_multimidas where postagem = ? and multimidia = ?");
        
        pstmt.setInt(1, postagem);
        pstmt.setInt(2, multimidia);;
        pstmt.executeUpdate();
    }
    
}