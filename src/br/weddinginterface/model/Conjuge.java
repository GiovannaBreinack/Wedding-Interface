package br.weddinginterface.model;

import br.weddinginterface.controller.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Conjuge {

    private String nome, dataCerimonia, dataRecepcao, anotacoes;
    private double valorGasto, orcamentoPrevisto, acessorios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCerimonia() {
        return dataCerimonia;
    }

    public void setDataCerimonia(String dataCerimonia) {
        this.dataCerimonia = dataCerimonia;
    }

    public String getDataRecepcao() {
        return dataRecepcao;
    }

    public void setDataRecepcao(String dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public double getOrcamentoPrevisto() {
        return orcamentoPrevisto;
    }

    public void setOrcamentoPrevisto(double orcamentoPrevisto) {
        this.orcamentoPrevisto = orcamentoPrevisto;
    }

    public double getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(double acessorios) {
        this.acessorios = acessorios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conjuge other = (Conjuge) obj;
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return "Conjuge{" + "nome=" + nome + ", dataCerimonia=" + dataCerimonia + ", dataRecepcao=" + dataRecepcao + ", anotacoes=" + anotacoes + ", valorGasto=" + valorGasto + ", orcamentoPrevisto=" + orcamentoPrevisto + ", acessorios=" + acessorios + '}';
    }

    public void insereConjuge(Conjuge con) {

        Conexao conexao = new Conexao();

        PreparedStatement st = null;

        try {

            String sql = "";
            sql += "";
            sql += "INSERT INTO tb_conjuge"
                    + "(n_nome, n_DataCerimonia, n_DataRecepcao, n_Cerimonialista, n_Anotacoes, n_ValorCerimonialista, n_ValorGasto, n_OrcamentoPrevisto, n_Acessorios)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            st = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, con.getNome());
            st.setString(2, con.getDataCerimonia());
            st.setString(3, con.getDataRecepcao());
            st.setString(5, con.getAnotacoes());
            st.setDouble(7, con.getValorGasto());
            st.setDouble(8, con.getOrcamentoPrevisto());
            st.setDouble(9, con.getAcessorios());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    String nome = rs.getString(1);
                    con.setNome(nome);
                }
                rs.close();
            } else {
                throw new SQLException("Erro inesperado! Nenhuma linha afetada!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexao.fechaConexao();
        }
// teste
    }
}
