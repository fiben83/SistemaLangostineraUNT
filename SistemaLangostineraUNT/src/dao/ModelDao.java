package dao;

public interface ModelDao {
    public String getDataTableBasic(String modelo)throws Exception;
    public String getSelectBasic(String modelo,String[]campos)throws Exception;
    public String getSelectBasicAnio(String modelo, String[] campos )throws Exception;
    public String create(String modelo,Object[] campos)throws Exception;
    public String update(String modelo,Object[] campos)throws Exception;
    public String remove(String modelo,Integer id)throws Exception;
    public void removeRow(String modelo,Integer id);
}
