#define _CRT_SECURE_NO_WARNINGS 1
#include "algoritmo.h"
#include "funcao.h"

// Calcula a qualidade de uma solucao
// Parametros de entrada: solucao (sol), capacidade da mochila (d), matriz com dados do problema (mat) e numero de objectos (v)
// Parametros de saida: qualidade da solucao (se a capacidade for excedida devolve 0)
float eval_individual(int sol[], struct info d, int mat[][2], int *v)
{
	int     i;
	float   sum_weight, sum_profit;

	sum_weight = sum_profit = 0;
	// Percorre todos os objectos
	for (i=0; i < d.numGenes; i++)
	{
        // Verifica se o objecto i esta na mochila
		if (sol[i] == 1)
		{
            // Actualiza o peso total
			sum_weight += mat[i][0];
            // Actualiza o lucro total
			sum_profit += mat[i][1];
		}
	}
	if (sum_weight > d.capacity)
	{
        // Solucao invalida
		*v = 0;
		return 0;
	}
	else
	{
        // Solucao valida
		*v = 1;
		return sum_profit;
	}
}

// Avaliacao da populacao
// Parametros de entrada: populacao (pop), estrutura com parametros (d) e matriz com dados do problema (mat)
// Parametros de saida: Preenche pop com os valores de fitness e de validade para cada solucao
void evaluate(pchrom pop, struct info d, int mat[][2])
{
	int i;

	for (i=0; i<d.popsize; i++)
		pop[i].fitness = eval_individual(pop[i].p, d, mat, &pop[i].valido);
}
