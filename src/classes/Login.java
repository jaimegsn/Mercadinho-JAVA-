package classes;

import javax.swing.JOptionPane;
import telas.*;
import java.sql.*;
import dal.ModuloConexao;

public class Login {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    TelaLogin tl = new TelaLogin();
    TelaCadastro tc = new TelaCadastro();

    private String usuario;
    private String senha;

    public void setUsuario(String txt) {
        this.usuario = txt;
    }

    public void setSenha(String pss) {
        this.senha = pss;
    }

    public void entrar() {
        conexao = ModuloConexao.conector(); //Chama o modulo de conexao

        String sql = "select * from login where usuario=? and senha=?";
        try {
            //As linhas abaixo consultam o banco de acordo com o que foi digitado nos campos de login
            // o interroga '?' é substituido pelo conteudo das variaveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, senha);

            //Execução da query
            rs = pst.executeQuery();

            if (rs.next()) {
                TelaPrincipal Telap = new TelaPrincipal();
                Telap.setVisible(true);
                tl.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Senha e/ou Usuário invalido(s)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cadastrar() {
        conexao = ModuloConexao.conector(); //Chama o modulo de conexao

        String sql = "insert into login(usuario,senha) values (?,?)";
        try {
            //As linhas abaixo consultam o banco de acordo com o que foi digitado nos campos de login
            // o interroga '?' é substituido pelo conteudo das variaveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, senha);

            //Execução da query
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Informações Cadastradas com Sucesso");
                tc.dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
