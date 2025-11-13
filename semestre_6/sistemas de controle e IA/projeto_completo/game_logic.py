from data import *

"""
Como as posições estão sendo definidas:
Posição: 01
0 = Casa 0
1 = Linha 1
"""

def return_free_places(positions):
    free_places = []
    for i in range(1, 9):
        for j in range(0,4):
            if positions[f"line{i}"][j] == " ":
                position = f"{j}{i}"
                free_places.append(position)
    
    return free_places

def possible_places_to_move(free_places, current_place):
    possible_places = {
        "forward_places": {
            "right_way": [],
            "left_way": []
        },
        "backward_places": {
            "right_way": [],
            "left_way": []
        },
        "same_line_places": [],
        "to_far_places": []
    }

    for i in range(0, len(free_places)):
        # Se a linha do lugar atual é uma linha a mais da linha da casa livre em questão:
        if int(free_places[i][1]) == (int(current_place[1]) + 1):
            if int(free_places[i][0]) == int(current_place[0]):
                # print(free_places[i])
                possible_places["forward_places"]["right_way"].append(free_places[i])
            elif int(free_places[i][0]) == (int(current_place[0]) - 1):
                # print(free_places[i])
                possible_places["forward_places"]["left_way"].append(free_places[i])

        # Senão, se a linha do lugar atual é uma linha a menos da linha da casa livre em questão:
        elif int(free_places[i][1]) == (int(current_place[1]) - 1):
            if int(free_places[i][0]) == (int(current_place[0]) + 1):
                # print(free_places[i])
                possible_places["backward_places"]["right_way"].append(free_places[i])
            elif int(free_places[i][0]) == int(current_place[0]):
                # print(free_places[i])
                possible_places["backward_places"]["left_way"].append(free_places[i])

        # Senão, se a linha do lugar atual é igual à linha da casa livre em questão:
        elif int(current_place[1]) == int(free_places[i][1]):
            # print("mesma linha", free_places[i])
            possible_places["same_line_places"].append(free_places[i])

        # Senão, se a linha do lugar atual está muito longe da linha do espaço vazio:
        else:
            # print("fora dos limites", free_places[i])
            possible_places["to_far_places"].append(free_places[i])
    
    return possible_places

def find_current_place(positions):
    """Deve perguntar ao jogador qual a casa atual que ele quer jogar, convertendo o padrão do tabuleiro, como 'A3' na linguagem do jogo, que seria por exemplo '03'."""
    # Dicionario tradutor para linguagem interna:
    lines_dict = "ABCDEFGH"

    # Posições ocupadas:
    ocupied_places = []

    # Perguntar ao jogador que peça mover
    print("Digite primeiro a coluna (ex.: 'A'), e a linha (ex.: 1)")
    print('Exemplo de input: A2') # Aqui deve retornar "02"
    current_desired_place = input("Digite o local da peça que deseja mover: ")

    # Traduz pra linguagem interna:
    for i in range(0, len(lines_dict)):
        if current_desired_place[0] == lines_dict[i]:
            current_desired_place[0] = i
            
    # Verificar se a casa esta vazia ou nao
    for i in range(1, 9):
        for j in range(0,4):
            if positions[f"line{i}"][j] != " ":
                position = f"{j}{i}"
            else:
                print("Local vazio, tente outro.")
                break
    # Verificar tipo de jogar, se é o "x" ou o "o"

    # Retornar a string correta

def move_to(positions):
    pass

# p = possible_places_to_move(free_places=return_free_places(positions), current_place="13")
# print(p["forward_places"]["right_way"])
# print(p["forward_places"]["left_way"])
# print(p["backward_places"]["right_way"])
# print(p["backward_places"]["left_way"])
# print(p["same_line_places"])
# print(p["to_far_places"])