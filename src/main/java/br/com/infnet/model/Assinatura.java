package br.com.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

    public class Assinatura {
        private BigDecimal mensalidade;
        private LocalDate inicio;
        private Optional<LocalDate> fim;
        private Cliente cliente;

        public Assinatura(BigDecimal mensalidade, LocalDate begin, Optional<LocalDate> fim, Cliente cliente) {
            this.mensalidade = mensalidade;
            this.inicio = begin;
            this.fim = fim;
            this.cliente = cliente;
        }

        public boolean isAtiva() {
            return !fim.isPresent();
        }

        public int getTempoEmMesesAtiva() {
            if (isAtiva()) {
                LocalDate dataAtual = LocalDate.now();
                return Period.between(inicio, dataAtual).getMonths();
            }
            return 0;
        }

        public int getTempoEntreInicioEFimEmMeses() {
            if (fim.isPresent()) {
                return Period.between(inicio, fim.get()).getMonths();
            }
            return 0;
        }

        public BigDecimal getValorPagoAteMomento() {
            LocalDate dataAtual = LocalDate.now();
            int mesesPagos = Period.between(inicio, dataAtual).getMonths();
            return mensalidade.multiply(BigDecimal.valueOf(mesesPagos));
        }

        @Override
        public String toString() {
            String status = fim.isPresent() ? "Encerrada" : "Ativa";
            return "Assinatura [Mensalidade: " + mensalidade + ", Inicio: " + inicio + ", Fim: " + fim.orElse(null) + ", Status: " + status + ", Cliente: " + cliente.getNome() + "]";
        }

        public BigDecimal getMensalidade() {
            return mensalidade;
        }

        public LocalDate getInicio() {
            return inicio;
        }

        public Optional<LocalDate> getFim() {
            return fim;
        }

        public Cliente getCliente() {
            return cliente;
        }
}


