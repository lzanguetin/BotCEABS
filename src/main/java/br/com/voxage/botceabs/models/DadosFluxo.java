package br.com.voxage.botceabs.models;

import br.com.voxage.vbot.BotInputResult;

public class DadosFluxo {
    private String cpf;
    private String os;
	private String prop;
	private String marca;
	private String ano;
	private String cor;
	private String placa;
	private String option;
	private String chassi;
	private String prest;
	private String local;
	private String contato;
	private String motivoi;
	private String motivor;
	private BotInputResult botInputResult;
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getOs() {
        return os;
    }
    
    public void setOs(String os) {
        this.os = os;
    }
    
    public String getProp() {
    	return prop;
    }
    
    public void setProp(String prop) {
    	this.prop = prop;
    }
    
    public String getMarca() {
    	return marca;
    }
    
    public void setMarca(String marca) {
    	this.marca = marca;
    }
    
    public String getAno() {
    	return ano;
    }
    
    public void setAno(String ano) {
    	this.ano = ano;
    }
    
    public String getPlaca() {
    	return placa;
    }
    
    public void setPlaca(String placa) {
    	this.placa = placa;
    }
    
    public String getChassi() {
    	return chassi;
    }
    
    public void setChassi(String chassi) {
    	this.chassi = chassi;
    }
    
    public String getPrest() {
    	return prest;
    }
    
    public void setPrest(String prest) {
    	this.prest = prest;
    }
    
    public String getLocal() {
    	return local;
    }
    
    public void setLocal(String local) {
    	this.local = local;
    }
    
    public String getContato() {
    	return contato;
    }
    
    public void setContato(String contato) {
    	this.contato = contato;
    }
    
    public String getMotivoI() {
    	return motivoi;
    }
    
    public void setMotivoI(String motivoi) {
    	this.motivoi = motivoi;
    }
    
    public String getMotivoR() {
    	return motivor;
    }
    
    public void setMotivoR(String motivor) {
    	this.motivor = motivor;
    }
    
    public String getCor() {
    	return cor;
    }
    
    public void setCor(String cor) {
    	this.cor = cor;
    }
    
    public BotInputResult getBotInputResult() {
        return botInputResult;
    }

    public void setBotInputResult(BotInputResult botInputResult) {
        this.botInputResult = botInputResult;
    }

    public String getOption() {
    	return option;
    }
    
    public void setOption(String option) {
    	this.option = option;
    }  
}
