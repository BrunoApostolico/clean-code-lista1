package br.com.infnet;

import br.com.infnet.model.Assinatura;
import br.com.infnet.model.Cliente;
import br.com.infnet.model.Pagamento;
import br.com.infnet.model.Produto;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Exercicio 01 --- Criar produtos, clientes e pagamentos");
        System.out.println("--- Exercicio 02 --- Ordenar pela data da compra");
        System.out.println("--- Exercicio 03 --- Calcular a soma dos valores com optional e double");
        System.out.println("--- Exercicio 04 --- Calcular os valores de todos os pagamentos");
        System.out.println("--- Exercicio 05 --- Imprimir a quantidade de produtos vendidos");
        System.out.println("--- Exercicio 06 --- Criar um mapa de Clientes, produtos");
        System.out.println("--- Exercicio 07 --- Cliente que gastou mais");
        System.out.println("--- Exercicio 08 --- Quanto foi faturado por mês");
        System.out.println("--- Exercicio 09 --- Criação de 3 assinaturas");
        System.out.println("----------------------------------");

        // Criar produtos
        Produto produto1 = new Produto("Musica 1", Path.of("caminho/para/musica1.mp3"), BigDecimal.valueOf(10.00));
        Produto produto2 = new Produto("Video 1", Path.of("caminho/para/video1.mp4"), BigDecimal.valueOf(19.99));
        Produto produto3 = new Produto("Imagem 1", Path.of("caminho/para/imagem1.jpg"), BigDecimal.valueOf(5.50));

        // Criar clientes
        Cliente cliente1 = new Cliente("Bruno");
        Cliente cliente2 = new Cliente("Priscila");
        Cliente cliente3 = new Cliente("Eloah");

        // Criar uma lista de produtos para os pagamentos
        List<Produto> listaProdutos1 = List.of(produto1, produto2, produto3);
        List<Produto> listaProdutos2 = List.of(produto3);
        List<Produto> listaProdutos3 = List.of(produto1, produto2);

        // Criar pagamentos com diferentes datas
        LocalDate hoje = LocalDate.now();
        LocalDate ontem = LocalDate.now().minusDays(1);
        LocalDate mesPassado = LocalDate.now().minusMonths(1);

        Pagamento pagamento1 = new Pagamento(listaProdutos1, hoje, cliente1);
        Pagamento pagamento2 = new Pagamento(listaProdutos2, ontem, cliente2);
        Pagamento pagamento3 = new Pagamento(listaProdutos3, mesPassado, cliente3);

        // Criar uma lista de pagamentos
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(pagamento1);
        pagamentos.add(pagamento2);
        pagamentos.add(pagamento3);

        // Ordenar os pagamentos pela data de compra (Exercicio 2)
        Collections.sort(pagamentos);

        // Calcular e exibir o valor total de cada pagamento (Exercicio 3)
/*        for (Pagamento pagamento : pagamentos) {
            BigDecimal valorTotal = pagamento.calcularValorTotal(Optional.of(10.0)); // Exemplo de desconto de 10% usando Optional
            BigDecimal valorTotalDouble = pagamento.calcularValorTotalDouble(10.0); // Exemplo de desconto de 10% usando Double diretamente
            exibirInformacoesPagamento(pagamento, valorTotal, valorTotalDouble);
        }*/

        // Calcular o valor total de todos os pagamentos (Exercicio 4)
/*        BigDecimal valorTotalPagamentos = BigDecimal.ZERO;
        for (Pagamento pagamento : pagamentos) {
            BigDecimal valorTotal = pagamento.calcularValorTotal(Optional.of(10.0)); // Exemplo de desconto de 10% usando Optional
            BigDecimal valorTotalDouble = pagamento.calcularValorTotalDouble(10.0); // Exemplo de desconto de 10% usando Double diretamente
            exibirInformacoesPagamento(pagamento, valorTotal, valorTotalDouble);
            valorTotalPagamentos = valorTotalPagamentos.add(valorTotal);
        }

        System.out.println("Valor Total de todos os pagamentos: " + valorTotalPagamentos);*/

        // Imprimir a quantidade de produtos vendidos (Exercicio 5)
        BigDecimal valorTotalPagamentos = BigDecimal.ZERO;
        for (Pagamento pagamento : pagamentos) {
            BigDecimal valorTotal = pagamento.calcularValorTotal(Optional.of(10.0)); // Exemplo de desconto de 10% usando Optional
            BigDecimal valorTotalDouble = pagamento.calcularValorTotalDouble(10.0); // Exemplo de desconto de 10% usando Double diretamente

            int quantidadeProdutos = pagamento.getQuantidadeProdutos();

            exibirInformacoesPagamento(pagamento, valorTotal, valorTotalDouble, quantidadeProdutos);
            valorTotalPagamentos = valorTotalPagamentos.add(valorTotal);
        }

        System.out.println("Valor Total de todos os pagamentos: " + valorTotalPagamentos);
        System.out.println("----------------------------------");

        // Criar o mapa de Cliente para List<Produto> (Exercicio 6)
        Map<String, List<Produto>> mapaClienteProdutos = new HashMap<>();

        // Associar os produtos a cada cliente no mapa
        for (Pagamento pagamento : pagamentos) {
            String nomeCliente = pagamento.getCliente().getNome();
            List<Produto> produtosCliente = mapaClienteProdutos.getOrDefault(nomeCliente, new ArrayList<>());
            produtosCliente.addAll(pagamento.getProdutos());
            mapaClienteProdutos.put(nomeCliente, produtosCliente);
        }

        // Imprimir o mapa de Cliente para List<Produto>
        for (Map.Entry<String, List<Produto>> entry : mapaClienteProdutos.entrySet()) {
            String nomeCliente = entry.getKey();
            List<Produto> produtosCliente = entry.getValue();

            System.out.println("Cliente do Mapa: " + nomeCliente);
            System.out.println("Produtos comprados:");
            for (Produto produto : produtosCliente) {
                System.out.println("- " + produto.getNome());
            }
            System.out.println("----------------------------------");
        }

        // Calcular o valor total gasto por cada cliente (Exercicio 7)
        Map<String, BigDecimal> mapaClienteGastos = new HashMap<>();
        for (Map.Entry<String, List<Produto>> entry : mapaClienteProdutos.entrySet()) {
            String nomeCliente = entry.getKey();
            List<Produto> produtosCliente = entry.getValue();

            BigDecimal valorTotal = produtosCliente.stream()
                    .map(Produto::getPreco)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            mapaClienteGastos.put(nomeCliente, valorTotal);
        }

        // Encontrar o cliente que gastou mais
        String clienteQueGastouMais = "";
        BigDecimal maiorValorGasto = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : mapaClienteGastos.entrySet()) {
            String nomeCliente = entry.getKey();
            BigDecimal valorGasto = entry.getValue();

            if (valorGasto.compareTo(maiorValorGasto) > 0) {
                maiorValorGasto = valorGasto;
                clienteQueGastouMais = nomeCliente;
            }
        }

        // Imprimir o cliente que gastou mais
        System.out.println("Cliente que gastou mais: " + clienteQueGastouMais);
        System.out.println("----------------------------------");

        // Calcular o valor total gasto por mês (Exercício 8)
        Map<YearMonth, BigDecimal> faturamentoPorMes = new TreeMap<>();
        for (Pagamento pagamento : pagamentos) {
            YearMonth mesPagamento = YearMonth.from(pagamento.getDataCompra());
            BigDecimal valorPagamento = pagamento.calcularValorTotal(Optional.of(10.00));

            BigDecimal valorTotalMes = faturamentoPorMes.getOrDefault(mesPagamento, BigDecimal.ZERO);
            valorTotalMes = valorTotalMes.add(valorPagamento);
            faturamentoPorMes.put(mesPagamento, valorTotalMes);
        }

        // Imprimir o faturamento por mês
        for (Map.Entry<YearMonth, BigDecimal> entry : faturamentoPorMes.entrySet()) {
            YearMonth mes = entry.getKey();
            BigDecimal faturamento = entry.getValue();

            System.out.println("Faturamento de " + mes + ": " + faturamento);
            System.out.println("----------------------------------");
        }

        // Criação das assinaturas (Exercício 9)
        Assinatura assinatura1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(3), Optional.empty(), cliente1);
        Assinatura assinatura2 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(6), Optional.of(LocalDate.now().minusMonths(1)), cliente2);
        Assinatura assinatura3 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(2), Optional.of(LocalDate.now().minusDays(10)), cliente3);


        // Impressão do tempo em meses das assinaturas ativas (Exercicio 10)
        System.out.println("Assinaturas:");
        if (assinatura1.isAtiva()) {
            System.out.println(assinatura1);
            System.out.println("Tempo de Assinatura: " + assinatura1.getTempoEmMeses() + " meses");
        }else {
            System.out.println(assinatura1);
        }
        if (assinatura2.isAtiva()) {
            System.out.println(assinatura2);
            System.out.println("Tempo de Assinatura: " + assinatura2.getTempoEmMeses() + " meses");
        }else {
            System.out.println(assinatura2);
        }
        if (assinatura3.isAtiva()) {
            System.out.println(assinatura3);
            System.out.println("Tempo de Assinatura: " + assinatura3.getTempoEmMeses() + " meses");
        }else {
            System.out.println(assinatura3);
        }

        // Impressão do tempo em meses entre start e end de todas as assinaturas (Exercício 11)
        System.out.println("Tempo em meses entre start e end de todas as assinaturas:");
        System.out.println("Assinatura 1: " + assinatura1.getTempoEmMesesTodos().orElse(0) + " meses");
        System.out.println("Assinatura 2: " + assinatura2.getTempoEmMesesTodos().orElse(0) + " meses");
        System.out.println("Assinatura 3: " + assinatura3.getTempoEmMesesTodos().orElse(0) + " meses");



    }

    public static void exibirInformacoesPagamento(Pagamento pagamento, BigDecimal valorTotal, BigDecimal valorTotalDouble, int quantidadeProdutos) {
        System.out.println("Cliente: " + pagamento.getCliente().getNome());
        System.out.println("Data da Compra: " + pagamento.getDataCompra());
        System.out.println("Valor Total Optional: " + valorTotal);
        System.out.println("Valor Total Double: " + valorTotalDouble);
        System.out.println("Produtos comprados:");
        for (Produto produto : pagamento.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
        System.out.println("Quantidade de produtos vendidos: " + quantidadeProdutos);
        System.out.println("----------------------------------");
    }
}