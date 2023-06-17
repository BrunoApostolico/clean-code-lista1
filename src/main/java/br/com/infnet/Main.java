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

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Exercicio 01 ---");
        System.out.println("--- Exercicio 02 ---");
        System.out.println("----------------------------------");

        // Criar produtos
        Produto produto1 = new Produto("Musica 1", Path.of("caminho/para/musica1.mp3"), BigDecimal.valueOf(9.99));
        Produto produto2 = new Produto("Video 1", Path.of("caminho/para/video1.mp4"), BigDecimal.valueOf(19.99));
        Produto produto3 = new Produto("Imagem 1", Path.of("caminho/para/imagem1.jpg"), BigDecimal.valueOf(4.99));

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

        // Exibir informações dos pagamentos ordenados
        for (Pagamento pagamento : pagamentos) {
            exibirInformacoesPagamento(pagamento);
        }
    }

    public static void exibirInformacoesPagamento(Pagamento pagamento) {
        System.out.println("Cliente: " + pagamento.getCliente().getNome());
        System.out.println("Data da Compra: " + pagamento.getDataCompra());
        System.out.println("Produtos comprados:");
        for (Produto produto : pagamento.getProdutos()) {
            System.out.println("- " + produto.getNome());
        }
        System.out.println("----------------------------------");
    }
}