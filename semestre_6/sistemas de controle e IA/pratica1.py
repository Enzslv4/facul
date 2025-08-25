# Procurando numero dentro de matriz

print("Algoritmo de Busca")

matriz = [
    [0,0,0],
    [0,1,0],
    [0,0,0]
]

linha = ''
coluna = ''
encontrado = False

for i in range(0, len(matriz)):
    for j in range(0, len(matriz[i])):
        if matriz[i][j] == 1:
            linha = i
            coluna = j
            encontrado = True
            break

    if encontrado:
        break

if encontrado:
    print(f"O valor 1 foi encontrado na linha {linha}, e na coluna {coluna}")
else:
    print("O valor 1 n foi encontrado.")