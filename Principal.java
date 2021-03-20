package principal;

import java.util.Random;

import javax.swing.JOptionPane;

public class Principal {

	static int disponibilidade = 8;

	static int capacidade = 25;

	static int[] valores = { 1, 1, 1, 1, 0, 0, 0, 0 };

	static int[] beneficio = { 4, 5, 3, 4, 6, 8, 5, 7 };

	static int[] peso = { 1, 2, 3, 10, 3, 2, 4, 8, 4, 2 };

	// -------------------

	static int[][] geracao = new int[8][8];

	static boolean primeira = true;

	// --------------------------

	static int[] somaDosBeneficios = new int[8];
	static int[] somaDosPesos = new int[8];
	static double[] calculoFitness = new double[8];

	// --------------------------
	public static void main(String[] args) {

		GeraGeracoes();

	}

	private static void GeraGeracoes() {

		int somaP = 0;
		int somaB = 0;

		if (primeira) {

			Random random = new Random();

			for (int i = 0; i < geracao.length; i++) {
				for (int j = 0; j < geracao.length; j++) {

					int indice = random.nextInt(7);

					somaP += peso[indice];// soma
					somaB += beneficio[indice];// soma

					geracao[i][j] = valores[indice];// nomes dos individuos
				}

				somaDosBeneficios[i] = somaB;
				somaDosPesos[i] = somaP;
				somaB = 0;
				somaP = 0;
			}

			for (int i = 0; i < geracao.length; i++) {
				for (int j = 0; j < geracao.length; j++) {

					System.out.print(geracao[i][j] + "\t");
				}

				System.out.println();
			}

			System.out.println();
			System.out.println();

			System.out.print("Beneficios");

			System.out.println();

			for (int i = 0; i < geracao.length; i++) {

				System.out.print(somaDosBeneficios[i] + " - ");
			}

			System.out.println();

			System.out.print("pesos");

			System.out.println();

			for (int i = 0; i < geracao.length; i++) {

				System.out.print(somaDosPesos[i] + " - ");
			}

			System.out.println();
			System.out.println();

			Fitness();
		}

	}

	private static void Fitness() {

		int fitnessTotal = 0;

		int menor = 9999999;

		for (int i = 0; i < somaDosPesos.length; i++) {

			if (somaDosPesos[i] > capacidade) {

				calculoFitness[i] = (somaDosBeneficios[i] - 100);

			} else {

				calculoFitness[i] = somaDosBeneficios[i];
			}
		}

		System.out.println();

		System.out.print("calculo fitness");

		System.out.println();

		for (int i = 0; i < geracao.length; i++) {

			System.out.print(calculoFitness[i] + " ");

			if (menor > calculoFitness[i]) {

				menor = (int) calculoFitness[i];

			}

		}
		System.out.println();
		System.out.println();

		System.out.println("menor");
		System.out.println(menor);

		System.out.println();
		System.out.println("subtra��o do menor valor");
		System.out.println();

		for (int i = 0; i < calculoFitness.length; i++) {

			calculoFitness[i] = (calculoFitness[i] - menor);

			fitnessTotal += calculoFitness[i];

			System.out.print((int) calculoFitness[i] + " ");
		}
		System.out.println();
		System.out.println();

		System.out.println("fitness total");

		System.out.print(fitnessTotal);

		System.out.println();

		for (int i = 0; i < calculoFitness.length; i++) {

			calculoFitness[i] /= fitnessTotal;
		}

		System.out.println();
		System.out.println();

		for (int i = 0; i < geracao.length; i++) {
			for (int j = 0; j < geracao.length; j++) {
				System.out.print(geracao[i][j] + "\t");
			}
			System.out.print("  " + (calculoFitness[i] * 100) + "%");
			System.out.println();
		}

		System.out.println();
		SelecaoDosPais();

	}

	private static void SelecaoDosPais() {

		int[] porcentagem = new int[100];

		int[] selecionados = new int[4];

		int individuo = 0;

		int atual = 0;
		
		int c = 0;

		for (int i = 0; i < calculoFitness.length; i++) {
			calculoFitness[i] *= 100;
		}

		for (int i = 0; i < beneficio.length; i++) {

			if ((int) calculoFitness[i] == 0) {

				individuo++;

			} else {

				atual += (int) calculoFitness[i];

				if (atual <= 99) {
					
						c = 99 - atual;
						
					for (int j = 0; j < c; j++) {
						porcentagem[j] = individuo;						
					}

					individuo++;
				}

			}
		}
	
		Random random = new Random();

		for (int i = 0; i <selecionados.length; i++) {

			int indice = random.nextInt(atual);

			selecionados[i] = porcentagem[indice];		
		}

		System.out.println();
		System.out.println("selecionados");

		for (int i = 0; i < selecionados.length; i++) {
			System.out.print(selecionados[i]+" = ");
			for (int j = 0; j < geracao.length; j++) {
				System.out.print(geracao[selecionados[i]][j]);
			}
			System.out.println();
		}
		CrossOver();
	}

	private static void CrossOver() {
			
	}

}