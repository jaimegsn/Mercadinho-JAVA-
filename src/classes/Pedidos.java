package classes;

import javax.swing.JOptionPane;
import telas.*;
import java.sql.*;
import dal.ModuloConexao;
import net.proteanit.sql.DbUtils;


public class Pedidos {
    
    private int id;
    private int qtd;
    private String status;
    private String nome;
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
    public void setID(int i){
        this.id=i;
    }
    public void setQTD(int q){
        this.qtd=q;
    }
    public void setStatus(String s){
        this.status=s;
    }
    public void setNome(String n){
        this.nome=n;
    }
    
    
    
    public void realizarPedido(){
        conexao = ModuloConexao.conector();
        
        String sql = "insert into pedidos(nome,qtd,status) values (?,?,?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, qtd);
            pst.setString(3, status);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Pedido Cadastrado com Sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    
    
    public void excluirPedido() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este pedido ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_NO_OPTION) {
            conexao = ModuloConexao.conector(); //Chama o modulo de conexao

            String sql = "delete from pedidos where id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, id);

                //Execução da query
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Pedido Removido com Sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
    
    
}
