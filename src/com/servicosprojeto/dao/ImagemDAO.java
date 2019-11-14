/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicosprojeto.dao;

import com.servicosprojeto.config.*;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Lucas
 */
public class ImagemDAO {
    
    public void salvaImagemNoBD(String caminho) throws Exception {
        Connection conexao = BDConfig.getConnection();

        String sql = "INSERT INTO imagem(nome,imagem) VALUES (?,?)";

        PreparedStatement pstmt = conexao.prepareStatement(sql);

        File arquivo = new File(caminho);
		FileInputStream inputStream = new FileInputStream(arquivo);
		
		pstmt.setString(1, arquivo.getName());
		pstmt.setBinaryStream(2,inputStream, (int) arquivo.length());
		
		pstmt.execute();
		pstmt.close();
    }
    
}
