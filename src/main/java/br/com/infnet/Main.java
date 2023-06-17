package br.com.infnet;

import br.com.infnet.model.Cliente;
import br.com.infnet.model.Pagamento;
import br.com.infnet.model.Produto;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Exercicio 01 --- Criar produtos, clientes e pagamentos");
        System.out.println("--- Exercicio 02 --- Ordenar pela data da compra");
        System.out.println("--- Exercicio 03 --- Calcular a soma dos valores com optional e double");
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
        List<Produto> listaProdutos1 = List.of(produto1, produto2);
        List<Produto> listaProdutos2 = List.of(produto3);
        List<Produto> listaProdutos3 = List.of(produto1, produto3);

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

        // Calcular e exibir o valor total de cada pagamento
        for (Pagamento pagamento : pagamentos) {
            BigDecimal valorTotal = pagamento.calcularValorTotal(Optional.of(10.0)); // Exemplo de desconto de 10% usando Optional
            BigDecimal valorTotalDouble = pagamento.calcularValorTotalDouble(10.0); // Exemplo de desconto de 10% usando Double diretamente
            exibirInformacoesPagamento(pagamento, valorTotal, valorTotalDouble);
        }
    }

    public static void exibirInformacoesPagamento(Pagamento pagamento, BigDecimal valorTotal, BigDecimal valorTotalDouble) {
        System.out.println("Cliente: " + pagamento.getCliente().getNome());
        System.out.println("Data da Compra: " + pagamento.getDataCompra());
        System.out.println("Valor Total Optional: " + valorTotal);
        System.out.println("Valor Total Double: " + valorTotalDouble);
        System.out.println("Produtos comprados:");
        for (Produto produto : pagamento.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
        System.out.println("----------------------------------");
    }
}