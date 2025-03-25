package main

import "fmt"

func main() {
    var resultado int
    defer fmt.Println("O resultado da soma é: ", resultado)

    resultado = calcular()
}

func calcular() int {
    return 12
}