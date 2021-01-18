# Sistema de Gestão de Produtividade Acadêmica

## Descrição

O sistema de gestão de produtividade acadêmica objetiva o gerenciamento da produção acadêmica de um laboratório de pesquisa, incluindo informações sobre projetos de pesquisa e colaboradores. O administrador do sistema é o usuário responsável pela manutençã o de todas as informações do sistema.

## Como usar

#### Instalação

No terminal

```html
   git clone https://github.com/Lucasl3/Sistema-Produtividade-Refatorado.git
```

 Depois de ter o repósitorio clonado, execute o arquivo src/Administrador.java

---

## Designs patterns aplicados
#### Strategy
> Foi aplicado na criação dos colaboradores, onde cada tipo de colaborador tem um metódo específico para ser criado, com o strategy, o metódo de criação é especificado no constructor de cada colaborador, portanto, é apenas necessário a chamada de uma função única para a criação do colaborador.
#### Chain of Responsibility
> Foi aplicado na criação de um projeto, temos uma cadeia sequencial de dados que são necessários para a criação de um projeto e enquanto um dos dados não for autorizado ou validado, o sistema irá ficar em loop até o usuário fornecer um dado satisfatório para aquela entrada e então partirá para o próximo, até que todos os dados sejam coletados e portanto seja possível a criação do projeto.
