package classes;

import javax.swing.JOptionPane;
import telas.*;
import java.sql.*;
import dal.ModuloConexao;
import net.proteanit.sql.DbUtils;

public class Produto {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String nome;
    private float preco;
    private float desconto;
    private int id;
    private int qtd;

    public void setNome(String n) {
        this.nome = n;
    }

    public void setPreco(float p) {
        this.preco = p;
    }

    public void setDesconto(float d) {
        this.desconto = d;
    }

    public void setID(String i) {
        this.id = Integer.parseInt(i);
    }
    
    public void setQTD(int q) {
        this.qtd = q;
    }

    public void addProduto() {
        conexao = ModuloConexao.conector(); //Chama o modulo de conexao

        String sql = "insert into produto(nome,preco,desconto,qtd) values (?,?,?,?)";
        try {
            //As linhas abaixo consultam o banco de acordo com o que foi digitado nos campos de login
            // o interroga '?' é substituido pelo conteudo das variaveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setFloat(2, preco);
            pst.setFloat(3, desconto);
            pst.setInt(4, qtd);

            //Execução da query
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void alterarProduto() {
        conexao = ModuloConexao.conector(); //Chama o modulo de conexao

        String sql = "update produto set nome=?,preco=?,desconto=?,qtd=? where id=?";
        try {
            //As linhas abaixo consultam o banco de acordo com o que foi digitado nos campos de login
            // o interroga '?' é substituido pelo conteudo das variaveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setFloat(2, preco);
            pst.setFloat(3, desconto);
            pst.setInt(4, qtd);
            pst.setInt(5, id);

            //Execução da query
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void excluirProduto() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este produto ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_NO_OPTION) {
            conexao = ModuloConexao.conector(); //Chama o modulo de conexao

            String sql = "delete from produto where id=?";
            try {
                //As linhas abaixo consultam o banco de acordo com o que foi digitado nos campos de login
                // o interroga '?' é substituido pelo conteudo das variaveis
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, id);

                //Execução da query
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto Removido com Sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

}
