package br.com.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public BigDecimal calcularValorTotal(Optional<Double> desconto) {
        BigDecimal valorTotal = produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (desconto.isPresent()) {
            double valorDesconto = desconto.get();
            BigDecimal descontoPercentual = BigDecimal.valueOf(valorDesconto / 100.0);
            BigDecimal valorDescontoAplicado = valorTotal.multiply(descontoPercentual);
            valorTotal = valorTotal.subtract(valorDescontoAplicado);
        }

        return valorTotal;
    }

    public BigDecimal calcularValorTotalDouble(Double desconto) {
        BigDecimal valorTotalDouble = produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (desconto != null) {
            BigDecimal descontoPercentual = BigDecimal.valueOf(desconto / 100.0);
            BigDecimal valorDescontoAplicado = valorTotalDouble.multiply(descontoPercentual);
            valorTotalDouble = valorTotalDouble.subtract(valorDescontoAplicado);
        }

        return valorTotalDouble;
    }

    public int getQuantidadeProdutos() {
        return produtos.size();
    }

    @Override
    public int compareTo(Pagamento outroPagamento) {
        return this.dataCompra.compareTo(outroPagamento.dataCompra);
    }
}

