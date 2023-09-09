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
                    Nivel.INICIANTE),

                PerguntaLoad(10, "Qual é o primeiro passo para criar um orçamento eficaz", Nivel.APRENDIZ),
                PerguntaLoad(11, "O que é um fundo de emergência?", Nivel.APRENDIZ),
                PerguntaLoad(12, "Qual é a diferença entre ativos e passivos?", Nivel.APRENDIZ),
                PerguntaLoad(13, "O que é a taxa de juros?", Nivel.APRENDIZ),
                PerguntaLoad(14, "O que é o planejamento tributário?", Nivel.APRENDIZ),
                PerguntaLoad(15, "O que é um investimento de baixo risco?", Nivel.APRENDIZ),
                PerguntaLoad(16, "O que é a inflação", Nivel.APRENDIZ),
                PerguntaLoad(17, "Qual é a importância de diversificar os investimentos?", Nivel.APRENDIZ),
                PerguntaLoad(18, "O que é um fundo de índice (ETF)?", Nivel.APRENDIZ),
                PerguntaLoad(19, "O que é a taxa de câmbio?", Nivel.APRENDIZ),

                PerguntaLoad(20, "O que é a Teoria Moderna de Portfólio (MPT) no contexto de investimentos?", Nivel.AVANCADO),
                PerguntaLoad(21, "O que é o CAPM (Modelo de Precificação de Ativos Financeiros)?", Nivel.AVANCADO),
                PerguntaLoad(22, "O que são derivativos financeiros?", Nivel.AVANCADO),
                PerguntaLoad(23, "O que é uma opção de venda (put option) em investimentos?", Nivel.AVANCADO),
                PerguntaLoad(24, "O que é o índice Dow Jones?", Nivel.AVANCADO),
                PerguntaLoad(25, "O que é o PIB (Produto Interno Bruto) e qual é a sua relevância no contexto econômico?", Nivel.AVANCADO),
                PerguntaLoad(26, "O que é um hedge fund?", Nivel.AVANCADO),
                PerguntaLoad(27, "O que é a taxa SELIC e qual é a sua importância na economia brasileira?", Nivel.AVANCADO),
                PerguntaLoad(28, "O que é o Mercado de Capitais?", Nivel.AVANCADO),
                PerguntaLoad(29, "O que são CRI e CRA no contexto de investimentos?", Nivel.AVANCADO)
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
                RespostaLoad("Gastar todo o dinheiro em bens de consumo", false, 9),

                RespostaLoad("Estabelecer metas financeiras", true, 10),
                RespostaLoad("Registrar todos os gastos", false, 10),
                RespostaLoad("Calcular a renda total", false, 10),
                RespostaLoad("Pagar as contas", false, 10),

                RespostaLoad("Um fundo de investimento com alto risco", false, 11),
                RespostaLoad("Uma reserva de dinheiro para despesas inesperadas", true, 11),
                RespostaLoad("Um fundo de ações", false, 11),
                RespostaLoad("Uma conta poupança de longo prazo", false, 11),

                RespostaLoad("Ativos são o que você deve, passivos são o que você possui", false, 12),
                RespostaLoad("Ativos são o que você possui, passivos são o que você deve", true, 12),
                RespostaLoad("Ativos são contas bancárias, passivos são dívidas", false, 12),
                RespostaLoad("Ativos são gastos, passivos são ganhos", false, 12),

                RespostaLoad("A quantia de dinheiro que você economiza", false, 13),
                RespostaLoad("O custo do empréstimo ou o retorno de um investimento", true, 13),
                RespostaLoad("A taxa de câmbio entre duas moedas", false, 13),
                RespostaLoad("O valor do salário mínimo", false, 13),

                RespostaLoad("Uma estratégia para economizar dinheiro em compras", false, 14),
                RespostaLoad("Um plano para maximizar a eficiência fiscal", true, 14),
                RespostaLoad("Um método para economizar em combustível", false, 14),
                RespostaLoad("Uma técnica para aumentar os gastos", false, 14),

                RespostaLoad("Ações de empresas novas", false, 15),
                RespostaLoad("Títulos do governo", true, 15),
                RespostaLoad("Criptomoedas", false, 15),
                RespostaLoad("Fundos de hedge", false, 15),

                RespostaLoad("Aumento no poder de compra", false, 16),
                RespostaLoad("Redução no custo de vida", false, 16),
                RespostaLoad("Aumento geral nos preços dos bens e serviços", true, 16),
                RespostaLoad("Aumento nos salários", false, 16),

                RespostaLoad("Para evitar qualquer risco financeiro", false, 17),
                RespostaLoad("Para maximizar os lucros", false, 17),
                RespostaLoad("Para reduzir o risco de perdas", true, 17),
                RespostaLoad("Para garantir um retorno rápido", false, 17),

                RespostaLoad("Um tipo de conta poupança", false, 18),
                RespostaLoad("Um fundo de investimento em ações", false, 18),
                RespostaLoad("Um fundo que rastreia um índice de mercado", true, 18),
                RespostaLoad("Um plano de previdência privada", false, 18),

                RespostaLoad("O custo de uma ação no mercado de ações", false, 19),
                RespostaLoad("A taxa de juros cobrada pelos bancos", false, 19),
                RespostaLoad("O valor de uma moeda em relação a outra", true, 19),
                RespostaLoad("A taxa de crescimento econômico de um país", false, 19),

                RespostaLoad("Uma estratégia de diversificação de investimentos", false, 20),
                RespostaLoad("Um modelo matemático para calcular juros compostos", false, 20),
                RespostaLoad("Um método para maximizar os ganhos em curto prazo", false, 20),
                RespostaLoad("Uma teoria sobre a gestão de carteiras de investimentos", true, 20),

                RespostaLoad("Uma fórmula para calcular o retorno de um investimento", false, 21),
                RespostaLoad("Um modelo para calcular o valor presente líquido de um projeto", false, 21),
                RespostaLoad("Um método para precificar opções de ações", false, 21),
                RespostaLoad("Um modelo que relaciona o risco e o retorno de um ativo financeiro", true, 21),

                RespostaLoad("Instrumentos financeiros que derivam do mercado de ações", false, 22),
                RespostaLoad("Contratos cujo valor é baseado em outro ativo subjacente", true, 22),
                RespostaLoad("Títulos emitidos por instituições financeiras", false, 22),
                RespostaLoad("Investimentos de alto risco e alto retorno", false, 22),

                RespostaLoad("Um contrato que dá ao comprador o direito de vender um ativo a um preço específico", true, 23),
                RespostaLoad("Um tipo de investimento em ouro", false, 23),
                RespostaLoad("Um título de renda fixa emitido pelo governo", false, 23),
                RespostaLoad("Um fundo de investimento em ações de baixo risco", false, 23),

                RespostaLoad("Um índice que mede a volatilidade do mercado de ações", false, 24),
                RespostaLoad("Um índice que representa o desempenho de 500 grandes empresas nos EUA", false, 24),
                RespostaLoad("Um índice que reflete o desempenho de 30 grandes empresas americanas", true, 24),
                RespostaLoad("Um índice que mede a taxa de inflação nos EUA", false, 24),

                RespostaLoad("Representa o lucro total de uma empresa no ano", false, 25),
                RespostaLoad("Mede a produção econômica de um país em um determinado período", true, 25),
                RespostaLoad("Calcula o valor dos ativos de uma empresa", false, 25),
                RespostaLoad("Reflete o valor total dos investimentos em um mercado de ações", false, 25),

                RespostaLoad("Um tipo de fundo de investimento de baixo risco", false, 26),
                RespostaLoad("Um fundo de investimento que busca proteção contra riscos de mercado", true, 26),
                RespostaLoad("Um fundo de ações focado em empresas em crescimento", false, 26),
                RespostaLoad("Um fundo que investe exclusivamente em títulos do governo", false, 26),

                RespostaLoad("A taxa de juros cobrada em empréstimos pessoais", false, 27),
                RespostaLoad("A taxa de câmbio entre o real e o dólar", false, 27),
                RespostaLoad("A taxa básica de juros da economia brasileira", true, 27),
                RespostaLoad("A taxa de retorno de investimentos em ações", false, 27),

                RespostaLoad("Um mercado onde são negociados títulos públicos", false, 28),
                RespostaLoad("Um mercado onde são negociados títulos de dívida corporativa", false, 28),
                RespostaLoad("Um mercado onde são negociados valores mobiliários e instrumentos financeiros", true, 28),
                RespostaLoad("Um mercado de câmbio internacional", false, 28),

                RespostaLoad("Siglas de organizações internacionais de regulamentação financeira", false, 29),
                RespostaLoad("Certificados de Renda Imobiliária e Certificados de Renda Agrícola", true, 29),
                RespostaLoad("Cédulas de Responsabilidade Internacional e Cédulas de Responsabilidade Ambiental", false, 29),
                RespostaLoad("Contratos de Renda Individual e Contratos de Renda Associativa", false, 29)
            )
            return listaResposta
        }
    }
}