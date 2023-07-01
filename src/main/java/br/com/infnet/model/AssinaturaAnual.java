package br.com.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssinaturaAnual implements iAssinatura{

    private BigDecimal valorMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public AssinaturaAnual(BigDecimal valorMensal, LocalDate dataInicio) {
        this.valorMensal = valorMensal;
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusYears(1).minusDays(1);
    }

    @Override
    public BigDecimal getValorMensal() {
        return null;
    }

    @Override
    public LocalDate getDataInicio() {
        return null;
    }

    @Override
    public LocalDate getDataFim() {
        return null;
    }
}
