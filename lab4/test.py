lista = [1, 2, 3, 3, 4, 3, 2, 5, 3]
sökTal = 3
resultatLista = []
for tal in lista:
    if sökTal == tal:
        resultatLista.append(tal)

print(resultatLista)