package com.example.finance.presentation.util

import com.example.finance.data.Nivel


class CompanionPerguntasRespostas{
    companion object{
        fun criarPerguntas(): List<PerguntaLoad>{
            val listaPerguntas = listOf(
                PerguntaLoad("Qual dos seguintes não é um componente essencial de um orçamento pessoal?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual das seguintes opções é uma maneira eficaz de economizar dinheiro a longo prazo?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual é o objetivo principal de um fundo de aposentadoria (previdência privada)?",
                    Nivel.INICIANTE),
                PerguntaLoad("O que é a taxa de juros composta?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual dos seguintes não é um método comum de investimento?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual é a melhor maneira de reduzir dívidas de cartão de crédito?",
                    Nivel.INICIANTE),
                PerguntaLoad("O que é um investimento de alto risco e potencialmente alto retorno?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual é o propósito de um fundo de emergência?",
                    Nivel.INICIANTE),
                PerguntaLoad("Qual dos seguintes não é um tipo comum de imposto sobre a renda pessoal?",
                    Nivel.INICIANTE),
                PerguntaLoad("O que é a diversificação de investimentos?",
                    Nivel.INICIANTE)
            )

            return listaPerguntas
        }

        fun criarRespostas(): List<RespostaLoad>{
            val listaResposta = listOf(
                RespostaLoad("Contas a pagar", false),
                RespostaLoad("Renda mensal", false),
                RespostaLoad("Metas de gastos", false),
                RespostaLoad("Cor da parede do quarto", true),

                RespostaLoad("Gastar todo o seu salário imediatamente", false),
                RespostaLoad(" Investir em ações arriscadas", false),
                RespostaLoad("Criar um fundo de emergência", true),
                RespostaLoad("Comprar bens de luxo constantemente", false),

                RespostaLoad("Comprar uma casa", false),
                RespostaLoad("Financiar férias", false),
                RespostaLoad("Garantir segurança financeira na aposentadoria", true),
                RespostaLoad("Pagar dívidas de cartão de crédito", false),

                RespostaLoad("Uma taxa de juros fixa", false),
                RespostaLoad("Uma taxa de juros que não muda", false),
                RespostaLoad("Uma taxa de juros que é aplicada apenas uma vez", false),
                RespostaLoad("Uma taxa de juros sobre o principal mais os juros acumulados", true),

                RespostaLoad("Poupança", false),
                RespostaLoad("Certificado de depósito (CD)", false),
                RespostaLoad("Hipoteca", true),
                RespostaLoad("Ações", false),

                RespostaLoad("Aumentar os gastos com cartão de crédito", false),
                RespostaLoad("Pagar apenas o valor mínimo devido", false),
                RespostaLoad("Transferir o saldo para um cartão com taxa de juros mais alta", false),
                RespostaLoad("Pagar mais do que o mínimo devido mensalmente", true),

                RespostaLoad("Títulos do governo", false),
                RespostaLoad("Conta de poupança", false),
                RespostaLoad("Fundos mútuos", false),
                RespostaLoad("Ações em uma startup", true),

                RespostaLoad("Financiar férias de luxo", false),
                RespostaLoad("Cobrir despesas diárias", false),
                RespostaLoad("Lidar com despesas inesperadas", true),
                RespostaLoad("Investir em ações", false),

                RespostaLoad("Imposto de Renda Federal", false),
                RespostaLoad("Imposto de Propriedade", true),
                RespostaLoad("Imposto de Renda Estadual", false),
                RespostaLoad("Imposto de Renda Municipal", false),

                RespostaLoad("Colocar todos os seus recursos em um único investimento", false),
                RespostaLoad("Espalhar seus investimentos por diferentes tipos de ativos", true),
                RespostaLoad("Ignorar completamente investimentos", false),
                RespostaLoad("Gastar todo o dinheiro em bens de consumo", false)
            )
            return listaResposta
        }
    }
}