# Problema do Barbeiro

Projeto em Java criado para a disciplina de Sistemas Operacionais, visando criar uma solução para o problema do barbeiro dorminhoco.

Membros:
- Pedro Henrique Alexandre
- Vinicius Cavalcante Pequeno

# Descrição

O problema envolve um barbeiro que atende a uma fila de clientes, mas só pode atender um de cada vez. Quando não há clientes, o barbeiro dorme, e quando um cliente chega, ele acorda o barbeiro, que o atende. Se todas as cadeiras estiverem ocupadas, o cliente vai embora. A solução foi implementada em Java utilizando threads e sincronização com `synchronized` para garantir que as operações entre as threads do barbeiro e dos clientes fossem feitas de forma ordenada e sem conflitos.

# Objetivo

O objetivo dessa implementação foi aplicar os conceitos de sincronização e comunicação entre processos para resolver o problema do barbeiro dorminhoco de maneira eficiente, sem deadlocks, starvation ou inconsistências. Fornecendo assim uma solução eficiente para o problema do barbeiro dorminhoco.

# Solução Implementada

- **Threads**: Cada cliente e o barbeiro são representados como threads independentes. O barbeiro dorme até que um cliente chegue, e os clientes esperam sua vez.
- **Sincronização**: O acesso à fila de clientes foi sincronizado com a palavra-chave `synchronized` para garantir que as operações de adicionar clientes à fila e o barbeiro cortar cabelo ocorram de forma segura e sem condições de corrida.
- **Estrutura de Dados**: Utilizou-se uma lista (`LinkedList`) para gerenciar a fila de clientes na sala de espera.

### Fluxo do Programa

1. O barbeiro começa dormindo.
2. Quando um cliente chega:
   - Se houver cadeiras disponíveis, ele entra na sala de espera e, se houver barbeiro disponível, o cliente é atendido imediatamente.
   - Se não houver cadeiras ou barbeiros disponíveis, o cliente vai embora ou espera.
3. O barbeiro atende os clientes um de cada vez, e o atendimento é simulado por uma pausa de 5 segundos.
4. Quando o barbeiro terminar de atender, ele volta a dormir até que um novo cliente chegue.