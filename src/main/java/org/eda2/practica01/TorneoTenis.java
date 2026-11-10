package org.eda2.practica01;

import java.util.Scanner;

public class TorneoTenis {
	
	private int[][] Tabla;
	
	public TorneoTenis(int[][] t) {
		this.Tabla = t;
		
	}

	public static void torneoRecursivoCaso1(int[][] tabla, int n) {
		if(n == 2) {
			tabla[1][1] = 2;
			tabla[2][1] = 1; 
		} else {
			torneoRecursivoCaso1(tabla, n/2);
			completarTablaCaso1(tabla, n);
		}
	}

	public static void torneoRecursivoCaso2(int[][] tabla, int n) {
				
		if(n == 2) {
			tabla[1][1] = 2;
			tabla[2][1] = 1; 
		} 
		
		else if(n % 2 != 0) {
			torneoRecursivoCaso2(tabla, n + 1);
			
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(tabla[i][j] == n + 1) tabla[i][j] = 0;
				}
			}
			
		} else {
			completarTablaCaso2(tabla, n);
		}
				
	}
	
	public static void torneoRecursivoCaso3(int[][] tabla, int n) {
		if(n == 2) {
			tabla[1][1] = 2;
			tabla[1][2] = 1;
		} else {
			torneoRecursivoCaso3(tabla, n/2);
			completarTablaCaso3(tabla, n);
		}
	}

	public int[][] getTabla() {
		return Tabla;
	}

	private static void completarTablaCaso1(int[][] tabla, int n) {
		int medio = n/2;
		for(int i = medio + 1; i<=n ; i++) {
			for(int j = 1; j < medio; j++) {
				tabla[i][j] = tabla[i - medio][j] + medio;
			}
		}
		
		for(int i = 1; i <= medio; i++) {
			for(int j=medio; j<n; j++) {
				int x = (i + j - 1) % medio + medio + 1;
				tabla[i][j] = x;
				tabla[x][j] = i;
			}
		}
	}
	
	private static void completarTablaCaso2(int[][] tabla, int n) {
		
		int m = n / 2;
		
		torneoRecursivoCaso2(tabla, m);
		
		if(m % 2 == 0) {
			
			for(int i = m + 1; i <= n; i++) {
				for(int j = 1; j <= m - 1; j++) {
					tabla[i][j] = tabla[i - m][j] + m;
				}
			}
			
			for(int i = 1; i <= m; i++) {
				for(int j = m; j <= n - 1; j++) {
					if((i + j) <= n) tabla[i][j] = i + j;
					else tabla[i][j] = i + j - m;
				}
			}
			
			for(int i = m + 1; i <= n; i++) {
				for(int j = m; j <= n - 1; j++) {
					if(i > j) tabla[i][j] = i - j;
					else tabla[i][j] = (i + m) - j;
				}
			}

		} else {
			
			for(int i = m + 1; i <= n; i++) {
				for(int j = 1; j <= m; j++) {
					if(tabla[i - m][j] == 0) {
						tabla[i][j] = 0;
					} else {
						tabla[i][j] = tabla[i - m][j] + m;
					}
				}
			}
			
			for(int i = 1; i <= m; i++) {
				for(int j = 1; j <= m; j++) {
					if(tabla[i][j] == 0) {
						tabla[i][j] = i + m;
						tabla[i + m][j] = i;
					}
				}
			}
			
			for(int i = 1; i <= m; i++) {
				for(int j = m + 1; j <= n - 1; j++) {
					if((i + j) <= n) tabla[i][j] = i + j;
					else tabla[i][j] = i + j - m;
				}
			}
			
			for(int i = m + 1; i <= n; i++) {
				for(int j = m + 1; j <= n - 1; j++) {
					if(i > j) tabla[i][j] = i - j;
					else tabla[i][j] = (i + m) - j;
				}
			}
			
		}
		

	}
	
	private static void completarTablaCaso3(int[][] tabla, int n) {
		int m = n/2;
		for(int i = 1; i < m; i++) {
			for(int jug = m + 1; jug <= n; jug++) {
				tabla[i][jug] = tabla[i][jug - m] + m;
			}
		}
		
		for(int dia = m; dia < n; dia++) {
			for(int jug = 1; jug <= m; jug++) {
				int x = (jug + dia - 1) % m + m + 1;
				tabla[dia][x] = jug;
				tabla[dia][jug] = x;
			}
		}
	}
    public String toString() {
        String s = "    ";
        
        //CABECERA
        
        for(int i = 0; i < Tabla[0].length; i ++) {
        	 s += " " + (i) + " ";
        	
        	}
        s += "\n" + "   ";
        
        for(int i = 0; i < Tabla[0].length*3; i ++) {
        	
        	s += "-";
        }
        s += "\n";
        
        //FIN CABECERA
		
		for(int i = 0; i < Tabla.length; i++) {
			
			 s += " " + (i) + " | ";
			
			for(int j = 0; j < Tabla[0].length; j++) {
				s += Tabla[i][j] + "  ";
			}
			s += "\n";
		}
		return s;
	}
	
	public static boolean esPotenciaDe2(int n) {
	    if (n <= 0) {
	        return false;
	    }
	    return (n & (n - 1)) == 0;
	}
	

	public static void main(String[] args) {
		Scanner casoEntrada = new Scanner(System.in);
		int caso;
		do {
		    System.out.print("Introduce el caso en el que quieres trabajar (1, 2 ó 3): ");
		    caso = casoEntrada.nextInt();
		    if(caso != 1 && caso != 2 && caso != 3 ) {
		        System.out.println("Caso incorrecto, por favor, vuelva a intentarlo");
		        System.out.println();
		    }
		} while(caso != 1 && caso != 2 && caso != 3);
		
		switch(caso) {
	        case 1:
	        	Scanner sc1 = new Scanner(System.in);
	        	int n1;
	        	do {
	        	    System.out.print("Introduce nº de filas o nº de jugadores (solo potencia de 2): ");
	        	    n1 = sc1.nextInt();
	        	    if (!esPotenciaDe2(n1)) {
	        	        System.out.println("El número introducido no es una potencia de 2. Por favor, vuelve a intentarlo.");
	        	    }
	        	} while (!esPotenciaDe2(n1));
	        	System.out.println();
	        	TorneoTenis caso1 = new TorneoTenis(new int[n1+1][n1]);

	        	TorneoTenis.torneoRecursivoCaso1(caso1.getTabla(), n1);
	        	System.out.println(caso1);
	        	break;

	        case 2:
	        	TorneoTenis caso2Par;
	        	int[][] temp = null;
	        	Scanner sc2 = new Scanner(System.in);
	    		System.out.print("Introduce nº de dias o columnas: ");
    			int n2 = 0;
	    		try {
	    			n2 = sc2.nextInt();
	    			if(n2 < 0) throw new NegativeArraySizeException("Valor negativo. ¡No válido!");
	    			
	    		} catch(Exception e) {
	    			System.out.println("¡Error!: " + e.getMessage());
	    			System.exit(1);
	    		}
	    		sc2.close();
	    		
	    		System.out.println();
	    		
	    		if(n2 % 2 != 0) {
	    			caso2Par = new TorneoTenis(new int[n2+2][n2 + 1]);
	    			temp = new int[n2 + 1][n2 + 1];
	    		}
	    		else caso2Par = new TorneoTenis(new int[n2+1][n2]);

	    		TorneoTenis.torneoRecursivoCaso2(caso2Par.getTabla(), n2);
	    		int[][] tabla = caso2Par.getTabla();
	    		
	    		if(n2 % 2 != 0) {
	    			
	    			for(int i = 0; i < temp.length; i++) {
		    			for(int j = 0; j < temp[0].length; j++) {
		    				temp[i][j] = tabla[i][j];
		    			}
		    		}
		    		TorneoTenis caso2Impar = new TorneoTenis(temp);
		    		System.out.println(caso2Impar);
	    		}
	    		else System.out.println(caso2Par);

	    		
	            break;
	        case 3:

	        	Scanner sc3 = new Scanner(System.in);
	        	int n3 = 0;
	        	do {
	        	    System.out.print("Introduce nº de columnas o nº de jugadores (solo potencia de 2): ");
	        	    n3 = sc3.nextInt();
	        	    if (!esPotenciaDe2(n3)) {
	        	        System.out.println("El número introducido no es una potencia de 2. Por favor, vuelve a intentarlo.");
	        	    }
	        	} while (!esPotenciaDe2(n3));
	        	System.out.println();
	        	TorneoTenis caso3 = new TorneoTenis(new int[n3][n3 + 1]);
	        	TorneoTenis.torneoRecursivoCaso3(caso3.getTabla(), n3);
	        	System.out.println(caso3);
	            break;
		}

	}
}