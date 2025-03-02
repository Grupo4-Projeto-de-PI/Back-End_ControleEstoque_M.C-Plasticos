# CONTROLE DE ESTOQUE M.C PLÁSTICOS 

Sistema de controle de estoque para a empresa M.C Plásticos

## Requisitos para Commit

Antes de realizar um commit, **certifique-se de que está na branch `dev`**. Para isso, siga os passos abaixo:

### Verificando a Branch Atual
Para verificar em qual branch você está, utilize o seguinte comando no terminal:

```sh
git branch
```

A branch em que você está atualmente estará marcada com um `*`.

### Entrando na Branch `dev`
Se você não estiver na branch `dev`, entre nela com o comando:

```sh
git checkout dev
```

Ou, caso esteja utilizando uma versão mais recente do Git:

```sh
git switch dev
```

Se a branch `dev` não existir localmente, você pode baixá-la do repositório remoto com:

```sh
git fetch origin
git checkout dev
```

Agora você pode fazer seus commits normalmente dentro da branch `dev`!

---

## Como Contribuir
1. Verifique se está na branch `dev`.
2. Faça suas alterações no código.
3. Adicione as mudanças ao stage:
   ```sh
   git add .
   ```
4. Faça um commit descrevendo as mudanças:
   ```sh
   git commit -m "Descrição breve das mudanças"
   ```
5. Envie suas alterações para o repositório remoto:
   ```sh
   git push origin dev
   ```

Agora suas alterações estão na branch `dev`! 🚀

---

## Criando um Pull Request para a branch `prod`
Após realizar suas alterações na branch `dev`, siga os passos abaixo para criar um Pull Request (PR) para a branch `prod`:

1. Acesse o repositório no GitHub.
2. Vá até a aba **Pull Requests**.
3. Clique em **New Pull Request**.
4. Selecione `dev` como branch de origem e `prod` como branch de destino.
5. Revise as alterações e adicione uma descrição explicando as mudanças.
6. Clique em **Create Pull Request**.
7. Aguarde a aprovação do PR e, após a revisão, faça o merge para `prod`.

Agora suas alterações estão na branch `prod`! 🎉

