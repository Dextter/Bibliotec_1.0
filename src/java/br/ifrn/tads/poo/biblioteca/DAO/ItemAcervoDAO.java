/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifrn.tads.poo.biblioteca.DAO;

import br.ifrn.tads.poo.biblioteca.acervo.Apostila;
import br.ifrn.tads.poo.biblioteca.conexao.ConexaoBd;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
import br.ifrn.tads.poo.biblioteca.acervo.Texto;
import br.ifrn.tads.poo.biblioteca.usuario.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablopc
 */
public class ItemAcervoDAO {
    private final Connection conexaobd;
    private Usuario usuario;
    private Livro livro;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public ItemAcervoDAO()throws ClassNotFoundException{
        this.conexaobd = new ConexaoBd().getConnection();      
    }
    
    //faz o cadastro de um livro
    public boolean cadastrarLivro(Livro livro){
        String bd = "INSERT INTO livro(isbn_livro, autor_livro,  titulo_livro, edicao_livro, data_livro, alugado) "                
                + "VALUES(?,?,?,?,?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){            
            ps.setString(1,livro.getIsbn());            
            ps.setString(2,livro.getAutor());
            ps.setString(3,livro.getTitulo());
            ps.setInt(4,livro.getEdicao());   
            ps.setString(5, livro.getData());
            ps.setString(6, "nao");            
            int testar  = ps.executeUpdate();
                if(testar == 1){
                    return true;                   
                }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
  
     //faz o cadastro de um apostila
    public boolean cadastrarApostila(Apostila apostila){
        String bd = "INSERT INTO apostila (autor_apostila, titulo_apostila, alugado)"                
                + "VALUES(?,?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){
            ps.setString(1,apostila.getAutor());                      
            ps.setString(2,apostila.getTitulo());                                  
            ps.setString(3, "nao");            
            int testar  = ps.executeUpdate();
              if(testar == 1){
                  return true;                   
              }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
    
    //faz o cadastro de um texto
    public boolean cadastrarTexto(Texto texto){
        String bd = "INSERT INTO texto(autor, alugado)"                
                + "VALUES(?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){
            ps.setString(1,texto.getAutor());                                                                  
            ps.setString(2, "nao");            
            int testar  = ps.executeUpdate();
              if(testar == 1){
                  return true;                   
              }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
    
    //lista todos itens não alugados em ordem decrescente
    public List<Livro> listarLivros(){
        String sqls = "SELECT * FROM livro WHERE alugado = ?  ORDER BY titulo_livro DESC ";
         
        List<Livro> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Livro livros = new Livro();
                        livros.setIsbn(result.getString("isbn_livro"));
                        livros.setAutor(result.getString("autor_livro"));                        
                        livros.setTitulo(result.getString("titulo_livro"));                        
                        livros.setEdicao(result.getInt("edicao_livro"));  
                        livros.setAlugado(result.getBoolean("alugado"));
                        livros.setData(result.getString("data_livro"));
                        livros.setLivroPago(result.getBoolean("pago"));
                        livros.setReservado(result.getBoolean("reservado"));
                        livros.setCustoLivro(result.getDouble("preco_livro"));
                        livros.setCodeReservante(result.getInt("codUsuarioReservante"));
                    lista.add(livros);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    //lista todos os livros alugados
    public List<Livro> listarLivrosAlugados(){
        String sqls = "SELECT * FROM livro WHERE alugado = ? ";
         
        List<Livro> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Livro livros = new Livro();
                        livros.setIsbn(result.getString("isbn_livro"));
                        livros.setAutor(result.getString("autor_livro"));                        
                        livros.setTitulo(result.getString("titulo_livro"));                        
                        livros.setEdicao(result.getInt("edicao_livro"));  
                        livros.setAlugado(result.getBoolean("alugado"));
                        livros.setData(result.getString("data_livro"));
                        livros.setLivroPago(result.getBoolean("pago"));
                        livros.setReservado(result.getBoolean("reservado"));
                        livros.setCustoLivro(result.getDouble("preco_livro"));
                        livros.setCodeReservante(result.getInt("codUsuarioReservante"));
                    lista.add(livros);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }    
    
    //lista todas as apostilas não alugadas em ordem decrescente
    public List<Apostila> listarApostilas(){
        String sqls = "SELECT * FROM apostila WHERE alugado = ?  ORDER BY titulo_apostila DESC ";
         
        List<Apostila> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Apostila apostilas = new Apostila();                        
                        apostilas.setAutor(result.getString("autor_apostila"));                        
                        apostilas.setTitulo(result.getString("titulo_apostila"));                                                
                    lista.add(apostilas);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    //lista todos as apostilas alugadas
    public List<Apostila> listarApostilasAlugados(){
        String sqls = "SELECT * FROM apostila WHERE alugado = ? ";
         
        List<Apostila> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Apostila apostilas = new Apostila();
                        apostilas.setAutor(result.getString("autor_apostila"));                                             
                        apostilas.setTitulo(result.getString("titulo_apostila"));                               
                    lista.add(apostilas);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
        return null;
    }    
    
    //lista todos textos não alugados em ordem decrescente
    public List<Texto> listarTextos(){
        String sqls = "SELECT * FROM texto WHERE alugado = ?  ORDER BY autor DESC ";
         
        List<Texto> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Texto textos = new Texto();                        
                        textos.setAutor(result.getString("autor"));                                                                        
                    lista.add(textos);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    //lista todos os textos alugados
    public List<Texto> listarTextosAlugados(){
        String sqls = "SELECT * FROM texto WHERE alugado = ? ";
         
        List<Texto> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Texto textos = new Texto();
                        textos.setAutor(result.getString("autor"));                        
                    lista.add(textos);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }    

    public int verificarReservanteDeLivro(int codUsuarioRequisitante){
        String sqls = "SELECT * FROM livro WHERE codUsuarioReservante = ? ";        
        
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setInt(1, codUsuarioRequisitante);
            ResultSet result = pst.executeQuery();            
                while(result.next()){
                    Livro livroR = new Livro();                        
                        livroR.setCodeReservante(result.getInt("codUsuarioReservante"));                                                                                             
                        int code = livroR.getCodeReservante();
                        return code;
                }                            
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }             
        return 0;
    }
    
    public boolean reservarLivros(Livro livro, int codReservante){
        String sql = "UPDATE livro SET reservado = ?,  pago = ?, usuarioReservante = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"nao");        
            ps.setInt(3, codReservante);
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return false;
    }
    
    public boolean alugarLivros(Livro livro, boolean pago, int codUsuario) {
        String pagamento; //TESTAR SE O METODO TA ALTERANDO TODOS OS ATRIBUTOS DA TABELA
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE livro SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataAluguel = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"sim");   //AQUI VAI VIM A VAARIAVEL PAGAMENTO   
            ps.setInt(3, codUsuario);
            ps.setDate(4, livro.getDataA());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return  false;
    }

    public boolean devolverLivros(Livro livro, boolean pago){
         String pagamento;
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE livro SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataDevolucao = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "nao");        
            ps.setString(2,"nao");        
            ps.setInt(3, 0);
            ps.setDate(4, livro.getDataD());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
         return false;
    }
    
    public int verificarReservanteDeApostila(int codUsuarioRequisitante){
        String sqls = "SELECT * FROM apostila WHERE codUsuarioReservante = ? ";        
        
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setInt(1, codUsuarioRequisitante);
            ResultSet result = pst.executeQuery();            
                while(result.next()){
                    Apostila apostilaR = new Apostila();                        
                        apostilaR.setCodeReservante(result.getInt("codUsuarioReservante"));                                                                                             
                        int code = apostilaR.getCodeReservante();
                        return code;
                }                            
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }             
        return 0;
    }
    
    public boolean reservarApostilas(Apostila apostila, int codReservante){
        String sql = "UPDATE apostila SET reservado = ?,  pago = ?, usuarioReservante = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"nao");        
            ps.setInt(3, codReservante);
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return false;
    }
    
    
     public boolean alugarApostilas(Apostila apostila, boolean pago, int codUsuario) {
        String pagamento; //TESTAR SE O METODO TA ALTERANDO TODOS OS ATRIBUTOS DA TABELA
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE apostila SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataAluguel = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"sim");  //AQUI VAI VIM A VARIAVEL PAGAMENTO     
            ps.setInt(3, codUsuario);
            ps.setDate(4, apostila.getDataA());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return  false;
    }

     public boolean devolverApostilas(Apostila apostila, boolean pago){
         String pagamento;
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE apostila SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataDevolucao = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "nao");        
            ps.setString(2,"nao");        
            ps.setInt(3, 0);
            ps.setDate(4, apostila.getDataD());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
         return false;
    }
    
     public int verificarReservanteDeTexto(int codUsuarioRequisitante){
        String sqls = "SELECT * FROM texto WHERE codUsuarioReservante = ? ";        
        
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setInt(1, codUsuarioRequisitante);
            ResultSet result = pst.executeQuery();            
                while(result.next()){
                    Texto textoR = new Texto();                        
                        textoR.setCodeReservante(result.getInt("codUsuarioReservante"));                                                                                             
                        int code = textoR.getCodeReservante();
                        return code;
                }                            
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }             
        return 0;
    }
     
    public boolean reservarTextos(Texto texto, int codReservante){
        String sql = "UPDATE texto SET reservado = ?,  pago = ?, usuarioReservante = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"nao");        
            ps.setInt(3, codReservante);
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return false;
    }
    
     public boolean alugarTextos(Texto texto, boolean pago, int codUsuario) {
        String pagamento; //TESTAR SE O METODO TA ALTERANDO TODOS OS ATRIBUTOS DA TABELA
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE texto SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataAluguel = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "sim");        
            ps.setString(2,"sim");    //AQUI VAI VIM A VARIAVEL PAGAMENTO        
            ps.setInt(3, codUsuario);
            ps.setDate(4, texto.getDataA());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
        return  false;
    }
    
     public boolean devolverTextos(Texto texto, boolean pago){
         String pagamento;
        if (pago == true){
            pagamento = "sim";
        } else {
            pagamento = "nao";
        }
        String sql = "UPDATE texto SET alugado = ?,  pago = ?, usuarioAlugante = ?, dataDevolucao = ?";
         try(PreparedStatement ps = conexaobd.prepareStatement(sql)){
            ps.setString(1, "nao");        
            ps.setString(2,"nao");        
            ps.setInt(3, 0);
            ps.setDate(4, texto.getDataD());
             int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(conexaobd != null){
                        try {
                            conexaobd.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }               
         return false;         
    }
     
     public double consultarEstadoDePagamento(int codUser){
         String sql = "SELECT saldoAPagar FROM usuario WHERE codUsuario = ?";
          try (PreparedStatement pst = conexaobd.prepareStatement(sql)){            
            ResultSet result = pst.executeQuery();            
            pst.setInt(1, codUser);
                while(result.next()){                                        
                    Usuario usuarioP = new Usuario();
                        double pago = usuarioP.getValorPagar();
                        return pago;
                }                            
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }             
        return 0;
     }
}