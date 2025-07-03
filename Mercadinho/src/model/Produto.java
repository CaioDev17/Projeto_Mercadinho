package model;

public class Produto {
    private String idProduto;
    private String nomeProduto;
    private String codBarra;
    private String tipoUn;
    private String precoUn;
    private String dataFab;
    private String dataVal;
    private String estoque;
	public Produto(String idProduto, String nomeProduto, String codBarra, String tipoUn, String precoUn, String dataFab,
			String dataVal, String estoque) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.codBarra = codBarra;
		this.tipoUn = tipoUn;
		this.precoUn = precoUn;
		this.dataFab = dataFab;
		this.dataVal = dataVal;
		this.estoque = estoque;
	}
	public Produto() {
		super();
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getTipoUn() {
		return tipoUn;
	}
	public void setTipoUn(String tipoUn) {
		this.tipoUn = tipoUn;
	}
	public String getPrecoUn() {
		return precoUn;
	}
	public void setPrecoUn(String precoUn) {
		this.precoUn = precoUn;
	}
	public String getDataFab() {
		return dataFab;
	}
	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}
	public String getDataVal() {
		return dataVal;
	}
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
    
    
}