# E1 - Programação de Software Aplicado

Alunos: Isadora Fonseca, Isadora Morari e Jhone Salvador

## Problema

As lojas de Sbornia usam terminais antigos para registrar vendas. Cada venda relaciona um usuário a produtos e quantidades, e o valor final é calculado assim:

```
valor final = valor dos produtos + impostos
```

O imposto muda de acordo com a categoria do produto:

| Categoria | Alíquota |
|-----------|----------|
| Alimentício | 5% |
| Automotivo | 30% |
| Bebida alcoólica | 100% |
| Outros | 17% |

Regras sobre o usuário:

- Usuários com mais de 60 anos não pagam imposto, exceto na compra de bebida alcoólica
- Usuários com mais de 3 dependentes têm 50% de desconto no imposto, exceto na compra de bebida alcoólica

## Padrões de projeto usados

**Strategy**: usamos essa técnica na interface CalculadoraImposto, com uma implementação para cada categoria (ImpostoAlimenticio, ImpostoAutomotivo, ImpostoBebidaAlcoolica, ImpostoPadrao). Sem isso, o cálculo do imposto viraria um monte de if/else dentro do serviço e toda vez que aparecesse uma categoria nova teríamos que mexer nesse código. Com Strategy, criamos uma classe nova e não precisamos tocar no que já existe

**Factory**: a FabricaCalculadoraImposto decide qual CalculadoraImposto usar para cada categoria de produto. Isso deixa esse mapeamento em um único lugar, fácil de encontrar e de estender

As regras de idade e de número de dependentes ficam dentro do VendaService, não dentro das estratégias de imposto. Porque essas regras dependem de quem está comprando, não do produto em si. A exceção da bebida alcoólica é tratada com o método isBebidaAlcoolica(), que é verificado antes de aplicar qualquer desconto do usuário
