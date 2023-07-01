package br.com.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface iAssinatura {
    BigDecimal getValorMensal();

    LocalDate getDataInicio();

    LocalDate getDataFim();
}
