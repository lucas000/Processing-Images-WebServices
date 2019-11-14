package com.servicosprojeto.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.servicosprojeto.config.BDConfig;
import com.servicosprojeto.modelo.Usuario;


public class UsuarioDAO {

	public void criarUsuario(Usuario usuario) throws Exception {
        Connection conexao = BDConfig.getConnection();

        String sql = "INSERT INTO usuario(usuario,senha) VALUES (?,?)";

        PreparedStatement pstmt = conexao.prepareStatement(sql);

		pstmt.setString(1, usuario.getNome());
		pstmt.setString(2, usuario.getSenha());
		
		pstmt.execute();
		pstmt.close();
    }
	
	public Usuario buscaUsuario(String nomeUsuario, String senha) throws Exception {
		Usuario usuario = null;

        Connection conexao = BDConfig.getConnection();

        String sql = "SELECT * FROM usuario WHERE usuario = ? and senha = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, nomeUsuario);
        statement.setString(2, senha);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
        	usuario = new Usuario();
        	
        	usuario.setId(rs.getInt("id"));
        	usuario.setNome(rs.getString("usuario"));
        	usuario.setSenha(rs.getString("senha"));
        }
        
        statement.close();
        conexao.close();
        
        return usuario;
    }
   
}
