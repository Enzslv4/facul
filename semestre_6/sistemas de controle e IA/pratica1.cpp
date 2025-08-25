#include <iostream>
#include <stdio.h>

int main() {
    std::cout << "Algoritmo de busca." << std::endl;

    int matriz[3][3] = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    int linha, coluna;
        bool encontrado = false;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 1) {
                    linha = i;
                    coluna = j;
                    encontrado = true;
                    break;
                }
        }
        if (encontrado) {
            break;    
        }
    }

    if (encontrado) {
        std::cout << "O valor 1 foi encontrado na posição: (" << linha << ", " << coluna << ")" << std::endl;
    
    } else {
        std::cout << "O valor 1 não foi encontrado na matriz" << std::endl;
    }
}