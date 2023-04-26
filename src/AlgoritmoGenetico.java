import java.util.Arrays;
import java.util.Random;

//FUNCION DE EVALUACION DEL INDIVIDUO = CONTAR CUANTOS NUMEROS COINCIDEN CON EL BUSCADO
//SE USA PARA DESPUES DETERMINAR EL FITNESS DE CADA INDIVIDUO
// VALOR DE CADA INDIVIDUO

//FITNESS = NUMERO DE CIFRAS QUE DIFIEREN DEL OBJETIVO
//SERVIRÁ PARA DETERMINAR QUÉ INDIVIDUOS SE SELECCIONAN PARA LA REPRODUCCION
//SELECCION DE INDIVIDUOS

//CROMOSOMA = VECTOR DE ENTEROS,  DE LONGITUD FIJA

//LOS GENES DENTRO DEL CROMOSOMA IRAN DEL 1 AL 100

//PASOS:

//ARRAY DE TAMAÑO ELEMENTOS POBLACION

//metodo primeraGeneracion() cromosomas con numeros aleatorios

//metodo evaluarCromosoma()  se da el fitness a cada cromosoma

//metodo ordenarPoblacion() en funcion del fitness

//metodo siguienteGeneracion() se toma un 10% de la elite, con mejor fitness

//metodo cruzar() se cruza cada individuo de la elite con un individuo aleatorio
//de la poblacion NO ELITE, forma de cruce al gusto, de 2 individuos obtenemos 1

//metodo mutacion() a cada individuo lo mutamos

//ordenarPoblacion() de nuevo en funcion de su fitness

//extincionSelectiva() seleccionamos los n mejores y eliminamos el resto, la poblacion debe mantenerse

// iteramos hasta encontrar la solucion o el numero maximo de iteraciones


public class AlgoritmoGenetico {

    private static final int LONGITUD_CROMOSOMA = 10;
    private static final int ELEMENTOS_POBLACION = 100;
    private static final int MAX_ITERACIONES = 1000;

    //CODIGO QUE DEBEMOS ENCONTRAR CON EL ALGORITMO GENETICO
    private static final int[] CODIGO_OBJETIVO = {1, 5, 7, 9, 12, 20, 30, 50, 75, 100};

    private static Random rnd = new Random();

    private static class Cromosoma implements Comparable<Cromosoma> {
        private int[] genes;
        private int fitness;

        public Cromosoma() {
            this.genes = new int[LONGITUD_CROMOSOMA];
        }

        public void inicializarGenes() {
            for (int i = 0; i < genes.length; i++) {
                genes[i] = rnd.nextInt(100) + 1;
            }
        }

        public int getFitness() {
            return fitness;
        }

        public void calcularFitness() {
            int contador = 0;
            for (int i = 0; i < genes.length; i++) {
                if (genes[i] != CODIGO_OBJETIVO[i]) {
                    contador++;
                }
            }
            fitness = contador;
        }

        public void cruzar(Cromosoma otro) {
            int puntoCorte = rnd.nextInt(LONGITUD_CROMOSOMA - 1) + 1;
            for (int i = puntoCorte; i < genes.length; i++) {
                int aux = genes[i];
                genes[i] = otro.genes[i];
                otro.genes[i] = aux;
            }
        }

        public void mutar() {
            int posicion = rnd.nextInt(LONGITUD_CROMOSOMA);
            genes[posicion] = rnd.nextInt(100) + 1;
        }

        @Override
        public int compareTo(Cromosoma o) {
            return Integer.compare(fitness, o.fitness);
        }
    }

    public static void main(String[] args) {
        Cromosoma[] poblacion = new Cromosoma[ELEMENTOS_POBLACION];
        int iteracionActual = 0;

        // Crear población inicial
        for (int i = 0; i < ELEMENTOS_POBLACION; i++) {
            Cromosoma cromosoma = new Cromosoma();
            cromosoma.inicializarGenes();
            poblacion[i] = cromosoma;
        }

        while (iteracionActual < MAX_ITERACIONES) {
            // Evaluar fitness
            for (Cromosoma cromosoma : poblacion) {
                cromosoma.calcularFitness();
            }

            // Ordenar población por fitness
            Arrays.sort(poblacion);

            // Mostrar el mejor cromosoma de la generación actual
            System.out.println("Mejor cromosoma de la generación " + iteracionActual + ": " + Arrays.toString(poblacion[0].genes));

            // Comprobar si se ha alcanzado el objetivo
            if (poblacion[0].fitness == 0) {
                System.out.println("¡Objetivo alcanzado en la generación " + iteracionActual + "!");
                break;
            }

            // Crear nueva generación

            //Cromosoma[] nuevaGeneracion = new Cromosoma[ELEMENTOS_POBLACION



}
    }

}
