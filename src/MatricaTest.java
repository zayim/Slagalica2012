
public class MatricaTest {
	
	public static void main(String[] args) {
		Matrica matrica = new Matrica(1,true);
		UlazniTok cin = new UlazniTok();
		int izbor;
		
		matrica.generisiSlucajno();
		System.out.println(matrica.toString());
		
		while (!matrica.daLiJeIgraGotova()){
			System.out.print("Komanda: ");
			izbor=cin.unesiInt();
			switch(izbor){
			case 1:
				matrica.pomjeriLijevo();
				break;
			case 2:
				matrica.pomjeriDole();
				break;
			case 3:
				matrica.pomjeriDesno();
				break;
			case 5:
				matrica.pomjeriGore();
				break;
			}
			System.out.println(matrica.toString());
		}
		
		System.out.println("KRAJ IGRE!");
		System.out.println("Broj poteza: " + matrica.vratiBrojPoteza());
	}
	

}
