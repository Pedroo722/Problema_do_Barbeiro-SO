# Problema do Barbeiro

Projeto criado para a disciplina de Sistemas Operacionais. Visando criar uma solução para o problema do barbeiro dorminhoco.

Membros:
- Pedro Henrique Alexandre
- Vinicius Cavalcante Pequeno

# Descrição

O problema envolve um barbeiro que atende a uma fila de clientes, mas só pode atender um de cada vez. Quando não há clientes, o barbeiro dorme, e quando um cliente chega, ele acorda o barbeiro, que o atende. Se todas as cadeiras estiverem ocupadas, o cliente vai embora. A solução foi implementada em Java utilizando threads e mecanismos de sincronização usando semáforos, para garantir que as operações entre as threads do barbeiro e dos clientes fossem feitas de forma ordenada e sem conflitos.

# Objetivo

O objetivo dessa implementação foi aplicar os conceitos de sincronização e comunicação entre processos para resolver o problema escolhido de forma que o sistema não apresente deadlocks, starvation ou inconsistências.

