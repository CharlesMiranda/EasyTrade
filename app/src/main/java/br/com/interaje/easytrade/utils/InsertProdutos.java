package br.com.interaje.easytrade.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class InsertProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_produtos);

        ArrayList<Produto> lista = new ArrayList<>();

        lista.add(
                new Produto(
                        "Renew Genics Creme Dia FPS 25 - 30g",
                        54.99d,
                        15,
                        "O Renew Genics Creme Dia FPS 25 reduz visivelmente a aparência das rugas em 3 dias, descolorações e ilumina sua pele instantaneamente. Dupla ação: Com FPS 25, protege o DNA da pele dos danos causados pelos raios UVA e UVB e deixa a pele com uma aparência dez anos mais jovem*. "
                )
        );

        lista.add(
                new Produto(
                        "Protetor Solar Anti-idade FPS50 - 50 ml",
                        29.99d,
                        15,
                        "Com tecnologia RepairShield, o Protetor Solar Anti-idade FPS50 foi desenvolvido desenvolvido para ajudar a proteger a estrutura do DNA da pele contra os raios UV, e reparar os sinais dos danos causados pelo sol. Contém 50ml."
                )
        );

        lista.add(
                new Produto(
                        "Kit Renew Ultimate 7S 45+ Dia e Noite",
                        99.98d,
                        15,
                "Kit Renew Ultimate 7S | 45+ Dia e Noite, pele mais suave e macia.\n" +
                        "Com a revolucionária tecnologia Pro7 Sirtuin, que ajuda a estimular as sete sirtuínas e proporciona uma aparência mais jovem ao rosto e pescoço, Renew Ultimate 7S é recomendado para mulheres com 45 anos ou mais. "
                )
        );

        lista.add(
                new Produto(
                        "Renew Genics Creme Dia FPS 25 - 30g",
                        54.99d,
                        15,
                        "O Renew Genics Creme Dia FPS 25 reduz visivelmente a aparência das rugas em 3 dias, descolorações e ilumina sua pele instantaneamente. Dupla ação: Com FPS 25, protege o DNA da pele dos danos causados pelos raios UVA e UVB e deixa a pele com uma aparência dez anos mais jovem*. "
                )
        );

        lista.add(
                new Produto(
                        "Renew Ultimate 7S Creme Noite - 50g",
                        49.99d,
                        15,
                        "O Renew Ultimate 7S Creme Noite, para mulheres de +45 anos, ajuda a recuperar a firmeza da pele, e instantaneamente aumenta a hidratação em até 180%. Em duas semanas, repara a aparência de rugas profundas. Com tecnologia Pro7 Sirtuin sua fórmula foi desenvolvida para ajudar a estimular as sete sirtuínas, ajudando a proporcionar uma aparência mais jovem ao rosto e pescoço."
                )
        );

        lista.add(
                new Produto(
                        "Renew Ultimate 7S Creme Dia FPS 25 - 50g",
                        49.99d,
                        15,
                        "O Renew Ultimate 7S Creme Dia FPS 25, para mulheres de +45 anos, proporciona instantaneamente à pele uma sensação de mais suavidade e elasticidade, aumentando a hidratação em 204%. Em duas semanas, preenche a aparência de linhas e rugas. Com tecnologia Pro7 Sirtuin, sua fórmula foi desenvolvida para ajudar a estimular as sete sirtuínas, ajudando a proporcionar uma aparência mais jovem ao rosto e pescoço."
                )
        );

        lista.add(
                new Produto(
                        "Big e Illusion Máscara Extra Volume com Efeito de Cílios Postiços - 10g",
                        14.90d,
                        15,
                "Volume com o dobro de cílios.SEM cola, SEM curvex, SEM esforço!\n" +
                        "O Big e Illusion Máscara Extra Volume com Efeito de Cílios Postiços - 10g eleva, alonga e dá volume instantaneamente!"
                )
        );

        lista.add(
                new Produto(
                        "Batom Hidratante Color Trend FPS 15",
                        6.99d,
                        15,
                        "O Batom Hidratante Color Trend FPS 15 hidrata instantaneamente para ajudar a manter os lábios macios e suaves. Fácil de aplicar para uma cobertura suave e uniforme. Dá a sensação de lábios cheios e cremosos. Proporciona cobertura completa. Longa duração, contém vitamina E e FPS 15."
                )
        );

        lista.add(
                new Produto(
                        "Big e Impact Máscara Extra Volume À Prova D´Água - 10g",
                        15.99d,
                        15,
                        "Você vive atrás daquela máscara perfeita para os cílios? Com a Big & Impact Avon você já verá seus cílios longos e 6 vezes mais volumosos a partir da primeira aplicação."
                )
        );

        lista.add(
                new Produto(
                        "Lápis Delineador Color Trend",
                        8.99,
                        15,
                "    Descrição do Produto\n" +
                        "\n" +
                        "O Lápis Delineador Color Trend tem formato de lápis e é super prático na hora da aplicação. Aplicação cremosa, aveludada e macia que desliza sobre as pálpebras. Boa cobertura. Desenha com precisão o contorno dos olhos para um delineado perfeito."
                )
        );


        lista.add(
                new Produto(
                        "Batom Extra Lasting",
                        14.99d,
                        15,
                        "Batom Extralasting tem longa duração, mantém a cor viva e uniforme por até 8 horas além de lábios hidratados por até 12 horas. Contém FPS 15 que ajuda a proteger seus lábios dos raios UVB e UVA. Batom com qualidade Avon. Baseado em estudos realizados com consumidoras."
                )
        );


        lista.add(
                new Produto(
                        "Colônia Desodorante Adidas Deep Energy 100ml",
                        28.90d,
                        15,
                        "A Colônia Desodorante Adidas Deep Energy, possui fragrância amadeirada e se distingue das demais pelas características que marcam o olfato. A Deep Energy foi feita especialmente para homens que não tem medo de arriscar e tentam sempre fazer do impossível possível. Notas de cabeça: bergamota, mandarina, folhas verdes, acordo marítimo. Notas de coração: cardamomo, maça verde, lavenda, violeta. Notas de fundo: cedar, galac, patchouli, ambar, musk. Madeira Adocicado"
                )
        );

        lista.add(
                new Produto(
                        "300 km/h Pole Position Colônia Desodorante - 100ml",
                        31.99d,
                        15,
                "O 300 km/h Pole Position Colônia Desodorante é uma combinação eletrizante do frescor do manjericão com os acordes de lavanda e musk. Uma fragrância para homens que vão além e não temem riscos. 300 km/h Pole Position – Ultrapasse seus limites. Contém 100ml."
                )
        );

        lista.add(
                new Produto(
                        "Colônia Desodorante Musk Marine - 90ml",
                        8.99d,
                        15,
               "Colônia Desodorante Musk Marine - 90ml é inspirado em um novo conceito de frescor, a fragrância traz a brisa do mar, a efervescência das notas cítricas na saída e a radiância de madeiras nobres numa base com nuances sensuais de ambar e musk. Notas de saída: Bergamota, Abacaxi, Hortelã, Folhas Verdes, Gengibre, Lavanda, Cravo."
                )
        );

        lista.add(
                new Produto(
                        "Colônia Desodorante Musk Fresh - 90ml",
                        8.99d,
                        15,
                "Colônia Desodorante Musk Fresh - 90ml é uma fragrância Fougère Fresco. O frescor e energia das notas fougère. Marcante e masculino, tem fundo musk amadeirado. Fragrância com direção olfativa fougère fresco, indicada para o dia."
                )
        );

        lista.add(
                new Produto(
                        "Colônia Desodorante Spirit 100ml",
                        19.90d,
                        15,
                "Colônia Desodorante Spirit traz o prazer das coisas simples da vida, a sofisticação de uma fragrância clássica em perfeita harmonia com notas modernas e atuais. Perfeito para o homem elegante, que acompanha o seu tempo.Transmite a sensação de conforto e descontração."
                        )
        );

        lista.add(
                new Produto(
                        "Colônia Mulher e Poesia Doce Balanço - 50ml",
                        39.99d,
                        15,
                "Mulher e Poesia Doce Balanço possui deliciosa peras crocantes envolvidos pela madeira e o toque feminino da magnólia. Desperte a musa que existe em cada mulher! Spray. Floral adocicado. Contém 50ml."
                )
        );

        lista.add(
                new Produto(
                        "Desodorante Roll-On Antitranspirante 50ml - Erva Doce",
                        3.49d,
                        15,
                "Desodorante Roll-On Antitranspirante Erva Doce proteção natural para o seu dia a dia. Encontre equilíbrio em seu dia a dia. O toque delicado da fragrância refrescante de erva doce suaviza sua pele, proporcionando uma agradável sensação. "
                )
        );

        lista.add(
                new Produto(
                        "Colônia Desodorante Adidas Fruity Rhythm 75ml",
                        35.90d,
                        15,
                "A Colônia Desodorante Adidas Fruity Rhythm é para mulheres estilosas com um modo de vida dinâmico. Uma doce fragrância floral frutal, que valoriza uma atitude feminina e irreverente.Você está procurando dinamismo e estílo? Deixe-se seduzir pela Colônia Fruity Rhythm! Notas de cabeça: Groselha, framboesa Notas de coração: Ciclamen, fresia Notas de fundo: Sandalo, musk Frutal Flora"
                )
        );

        lista.add(
                new Produto(
                        "Beyoncé Heat Rush Deocolônia 100ml",
                        85.90d,
                        15,
                "Sinta-se rodeada de luz com a nova colônia Beyoncé Heat Rush Deocolônia. Notas frutais envolvidas pela feminilidade da orquídea junto à sensualidade do âmbar. Curta o momento vibrante, tênue, fresco, sedutor e sensual. A nova fragrância de sedução Beyoncé Heat Rush, um perfume para marcar, além de estar entre os 3 mais vendidos no mundo e a Avon trouxe para você."
                )
        );

        lista.add(
                new Produto(
                        "Desodorante Creme Antitranspirante 55g - Erva Doce",
                        2.69d,
                        15,
                "Desodorante Creme Antitranspirante de Erva Doce, proteção natural para o seu dia a dia. Encontre equilíbrio em seu dia a dia. O toque delicado da fragrância refrescante de erva doce suaviza sua pele, proporcionando uma agradável sensação. O desodorante em creme Avon Naturals possui uma efetiva proteção antitranspirante. Tenha uma pele protegida dos odores e da umidade causados pela transpiração. Proteção e confiança ao seu lado durante todo o dia."
                )
        );


        for(Produto produtoCorrente : lista){

            ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(this));
            ProdutoDAO dao = new ProdutoDAOImpl();


            //Log.i("insert",produtoCorrente.getNome()+"\n "+produtoCorrente.getValor()+"\n "+produtoCorrente.getQuantidade()+"\n "+produtoCorrente.getDescricao());
            produtoCorrente.setId(0l);
            dao.salvar(produtoCorrente, produtoDatabase);

        }
    }
}
