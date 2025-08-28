package model;

public class RelatorioVenda {
	private String idVenda;
    private String idCliente;
    private String idProduto;
    private String idFuncionario;
    private String PrecoUn;
    private String valorTotal;
    private String quantidade;
    private String dataVenda;
	public RelatorioVenda(String idVenda, String idCliente, String idProduto, String idFuncionario, String precoUn,
			String valorTotal, String quantidade, String dataVenda) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.idProduto = idProduto;
		this.idFuncionario = idFuncionario;
		PrecoUn = precoUn;
		this.valorTotal = valorTotal;
		this.quantidade = quantidade;
		this.dataVenda = dataVenda;
	}
	public RelatorioVenda() {
		super();
	}
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getPrecoUn() {
		return PrecoUn;
	}
	public void setPrecoUn(String precoUn) {
		PrecoUn = precoUn;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
}
