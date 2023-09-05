package com.example.finance.presentation.util

import com.example.finance.data.Nivel


class CompanionPerguntasRespostas{
    companion object{
        fun criarPerguntas(): List<PerguntaLoad>{
            val listaPerguntas = listOf(
                PerguntaLoad(0, "Qual dos seguintes não é um componente essencial de um orçamento pessoal?",
                    Nivel.INICIANTE),
                PerguntaLoad(1, "Qual das seguintes opções é uma maneira eficaz de economizar dinheiro a longo prazo?",
                    Nivel.INICIANTE),
                PerguntaLoad(2, "Qual é o objetivo principal de um fundo de aposentadoria (previdência privada)?",
                    Nivel.INICIANTE),
                PerguntaLoad(3, "O que é a taxa de juros composta?",
                    Nivel.INICIANTE),
                PerguntaLoad(4, "Qual dos seguintes não é um método comum de investimento?",
                    Nivel.INICIANTE),
                PerguntaLoad(5, "Qual é a melhor maneira de reduzir dívidas de cartão de crédito?",
                    Nivel.INICIANTE),
                PerguntaLoad(6, "O que é um investimento de alto risco e potencialmente alto retorno?",
                    Nivel.INICIANTE),
                PerguntaLoad(7, "Qual é o propósito de um fundo de emergência?",
                    Nivel.INICIANTE),
                PerguntaLoad(8, "Qual dos seguintes não é um tipo comum de imposto sobre a renda pessoal?",
                    Nivel.INICIANTE),
                PerguntaLoad(9, "O que é a diversificação de investimentos?",
                    Nivel.INICIANTE)
            )

            return listaPerguntas
        }

        fun criarRespostas(): List<RespostaLoad>{
            val listaResposta = listOf(
                RespostaLoad("Contas a pagar", false, 0),
                RespostaLoad("Renda mensal", false, 0),
                RespostaLoad("Metas de gastos", false, 0),
                RespostaLoad("Cor da parede do quarto", true, 0),

                RespostaLoad("Gastar todo o seu salário imediatamente", false, 1),
                RespostaLoad(" Investir em ações arriscadas", false, 1),
                RespostaLoad("Criar um fundo de emergência", true, 1),
                RespostaLoad("Comprar bens de luxo constantemente", false, 1),

                RespostaLoad("Comprar uma casa", false, 2),
                RespostaLoad("Financiar férias", false, 2),
                RespostaLoad("Garantir segurança financeira na aposentadoria", true, 2),
                RespostaLoad("Pagar dívidas de cartão de crédito", false, 2),

                RespostaLoad("Uma taxa de juros fixa", false, 3),
                RespostaLoad("Uma taxa de juros que não muda", false, 3),
                RespostaLoad("Uma taxa de juros que é aplicada apenas uma vez", false, 3),
                RespostaLoad("Uma taxa de juros sobre o principal mais os juros acumulados", true, 3),

                RespostaLoad("Poupança", false, 4),
                RespostaLoad("Certificado de depósito (CD)", false, 4),
                RespostaLoad("Hipoteca", true, 4),
                RespostaLoad("Ações", false, 4),

                RespostaLoad("Aumentar os gastos com cartão de crédito", false, 5),
                RespostaLoad("Pagar apenas o valor mínimo devido", false, 5),
                RespostaLoad("Transferir o saldo para um cartão com taxa de juros mais alta", false, 5),
                RespostaLoad("Pagar mais do que o mínimo devido mensalmente", true, 5),

                RespostaLoad("Títulos do governo", false, 6),
                RespostaLoad("Conta de poupança", false, 6),
                RespostaLoad("Fundos mútuos", false, 6),
                RespostaLoad("Ações em uma startup", true, 6),

                RespostaLoad("Financiar férias de luxo", false, 7),
                RespostaLoad("Cobrir despesas diárias", false, 7),
                RespostaLoad("Lidar com despesas inesperadas", true, 7),
                RespostaLoad("Investir em ações", false, 7),

                RespostaLoad("Imposto de Renda Federal", false, 8),
                RespostaLoad("Imposto de Propriedade", true, 8),
                RespostaLoad("Imposto de Renda Estadual", false, 8),
                RespostaLoad("Imposto de Renda Municipal", false, 8),

                RespostaLoad("Colocar todos os seus recursos em um único investimento", false, 9),
                RespostaLoad("Espalhar seus investimentos por diferentes tipos de ativos", true, 9),
                RespostaLoad("Ignorar completamente investimentos", false, 9),
                RespostaLoad("Gastar todo o dinheiro em bens de consumo", false, 9)
            )
            return listaResposta
        }
    }
}