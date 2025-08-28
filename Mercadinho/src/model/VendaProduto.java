package model;

public class VendaProduto {
	private String idVendaProduto;
	private String idVenda;
	private String idProduto;
	private String precoUn;
	private String quantidade;
	private String precoTotal;
	private String tipoUn;
	
	public String getTipoUn() {
		return tipoUn;
	}
	public void setTipoUn(String tipoUn) {
		this.tipoUn = tipoUn;
	}
	public VendaProduto(String idVendaProduto, String idVenda, String idProduto, String precoUn, String quantidade,
			String precoTotal) {
		super();
		this.idVendaProduto = idVendaProduto;
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.precoUn = precoUn;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
	}
	public VendaProduto() {
		super();
	}
	public String getIdVendaProduto() {
		return idVendaProduto;
	}
	public void setIdVendaProduto(String idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getPrecoUn() {
		return precoUn;
	}
	public void setPrecoUn(String precoUn) {
		this.precoUn = precoUn;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	
}
