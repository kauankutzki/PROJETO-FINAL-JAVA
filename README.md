# Sistema de Gestão de RH

Trabalho A1 — Desenvolvimento de Software | Java Orientado a Objetos | MVC

---

## Descrição

Sistema de gerenciamento de Recursos Humanos desenvolvido em Java puro, com interface via console. Permite cadastrar e gerenciar funcionários (CLT, PJ e Estagiários), departamentos, cargos, benefícios, vagas, candidatos e folha de pagamento.

## Objetivo

Aplicar na prática os conceitos de Orientação a Objetos — herança, polimorfismo, encapsulamento, interfaces e classes abstratas — dentro de uma arquitetura MVC organizada em pacotes.

## Funcionalidades

- Cadastro e listagem de Funcionários CLT, PJ e Estagiários
- Gerenciamento de Departamentos e Cargos
- Controle de Benefícios
- Geração de Folha de Pagamento e Holerites
- Cadastro de Vagas e Candidatos
- Recrutamento com conversão de Candidato em Funcionário

## Principais Classes e Relacionamentos

- `Pessoa` (abstrata) → `Funcionario` (abstrata) → `FuncionarioCLT`, `FuncionarioPJ`, `Estagiario`
- `Pessoa` → `Candidato`
- `Departamento` agrega `Funcionario` (agregação)
- `FolhaPagamento` compõe `Holerite` (composição)
- `Funcionario` referencia `Cargo` (associação)
- Interfaces: `Avaliavel`, `Repositorio<T>`, `Identificavel`

## Como executar

1. Abra o projeto no IntelliJ IDEA
2. Certifique-se de que o JDK está configurado (Java 11+)
3. Execute o arquivo `Main.java`

## Divisão entre os integrantes

| Aluno | Módulo |
|---|---|
| Aluno 1 | Pessoa, Funcionário (CLT/PJ/Estagiário), infraestrutura |
| Aluno 2 | Departamento e Cargo |
| Aluno 3 | Benefícios e Folha de Pagamento |
| Aluno 4 | Avaliação de Desempenho e Treinamento |
| Aluno 5 | Vagas, Candidatos, Recrutamento e Menu Principal |

## Uso de Inteligência Artificial

A IA foi utilizada para validação do sistema — verificando erros, testando fluxos e conferindo o cumprimento dos requisitos — e para idealização da estrutura do projeto, auxiliando nas decisões de arquitetura como organização de pacotes, hierarquia de classes e separação de responsabilidades entre as camadas MVC.

## Referências

- Documentação oficial Java: https://docs.oracle.com/javase/tutorial/
- Conteúdo das aulas de Desenvolvimento de Software — UP
