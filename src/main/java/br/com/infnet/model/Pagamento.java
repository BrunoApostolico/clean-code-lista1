package br.com.infnet.model;

import java.time.LocalDate;
import java.util.List;

public class Pagamento implements Comparable<Pagamento> {
    private List<Produto> produtos;
    private LocalDate dataCompra;
    private Cliente cliente;

    public Pagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public int compareTo(Pagamento outroPagamento) {
        return this.dataCompra.compareTo(outroPagamento.dataCompra);
    }
}

