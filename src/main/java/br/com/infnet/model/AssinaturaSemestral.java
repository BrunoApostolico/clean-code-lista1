package br.com.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssinaturaSemestral implements iAssinatura {
    private BigDecimal valorMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public AssinaturaSemestral(BigDecimal valorMensal, LocalDate dataInicio) {
        this.valorMensal = valorMensal;
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusMonths(6).minusDays(1);
    }

    @Override
    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    @Override
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    @Override
    public LocalDate getDataFim() {
        return dataFim;
    }
}