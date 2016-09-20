import java.util.ArrayList;
import java.util.Collections;

public class Matrica {

	ArrayList<ArrayList<Integer>> matrica;
	int level,stranica;
	int pozicijaX,pozicijaY;
	int[] xHash,yHash;
	int brojPoteza;
	boolean lagana;
	
	public Matrica(int lvl,boolean lagana__){
		level=lvl;
		stranica=level+2;
		brojPoteza=0;
		lagana=lagana__;
		
		matrica = new ArrayList<ArrayList<Integer>>(stranica);
		for (int i=0; i<stranica; i++) matrica.add(new ArrayList<Integer>(stranica));
		
		xHash=new int[stranica*stranica]; yHash=new int[stranica*stranica];
		
		pozicijaX=pozicijaY=0;
		
		generisiSlucajno();
	}
	
	public void generisiSlucajno(){
		ArrayList<Integer> pomocna = new ArrayList<Integer>(stranica*stranica-1);
		for (int i=1; i<stranica*stranica; i++) pomocna.add(i);
		Collections.shuffle(pomocna);
		pomocna.add(0,0);
		
		for (int i=0; i<stranica; i++)
			for (int j=0; j<stranica; j++){
				matrica.get(i).add(pomocna.get(i*stranica + j));
				xHash[pomocna.get(i*stranica+j)]=j;
				yHash[pomocna.get(i*stranica+j)]=i;
			}
	}
	
	public String toString(){
		String vrati="";
		for (int i=0; i<matrica.size(); i++){
			for (int j=0; j<matrica.get(i).size(); j++){
				vrati+=matrica.get(i).get(j) + " ";
			}
			vrati+="\n";
		}
		return vrati;
	}
	
	public void pomjeriLijevo(){
		if (pozicijaX-1>=0) {
			int pomocna=matrica.get(pozicijaY).get(pozicijaX-1);
			matrica.get(pozicijaY).set(pozicijaX, pomocna);
			matrica.get(pozicijaY).set(pozicijaX-1, 0);
			pozicijaX--;
			brojPoteza++;
		}
		else if (pozicijaX==0 && lagana){
			int pomocna=matrica.get(pozicijaY).get(stranica-1);
			matrica.get(pozicijaY).set(0,pomocna);
			matrica.get(pozicijaY).set(stranica-1,0);
			pozicijaX=stranica-1;
			brojPoteza++;
		}
	}
	
	public void pomjeriDesno(){
		if (pozicijaX+1<stranica) {
			int pomocna=matrica.get(pozicijaY).get(pozicijaX+1);
			matrica.get(pozicijaY).set(pozicijaX, pomocna);
			matrica.get(pozicijaY).set(pozicijaX+1, 0);
			pozicijaX++;
			brojPoteza++;
		}
		else if (pozicijaX==stranica-1 && lagana){
			int pomocna=matrica.get(pozicijaY).get(0);
			matrica.get(pozicijaY).set(stranica-1,pomocna);
			matrica.get(pozicijaY).set(0,0);
			pozicijaX=0;
			brojPoteza++;
		}
	}
	
	public void pomjeriDole(){
		if (pozicijaY+1<stranica) {
			int pomocna=matrica.get(pozicijaY+1).get(pozicijaX);
			matrica.get(pozicijaY).set(pozicijaX, pomocna);
			matrica.get(pozicijaY+1).set(pozicijaX, 0);
			pozicijaY++;
			brojPoteza++;
		}
		else if (pozicijaY==stranica-1 && lagana){
			int pomocna=matrica.get(0).get(pozicijaX);
			matrica.get(0).set(pozicijaX,0);
			matrica.get(stranica-1).set(pozicijaX,pomocna);
			pozicijaY=0;
			brojPoteza++;
		}
	}
	
	public void pomjeriGore(){
		if (pozicijaY-1>=0) {
			int pomocna=matrica.get(pozicijaY-1).get(pozicijaX);
			matrica.get(pozicijaY).set(pozicijaX, pomocna);
			matrica.get(pozicijaY-1).set(pozicijaX, 0);
			pozicijaY--;
			brojPoteza++;
		}
		else if (pozicijaY==0 && lagana){
			int pomocna=matrica.get(stranica-1).get(pozicijaX);
			matrica.get(stranica-1).set(pozicijaX,0);
			matrica.get(0).set(pozicijaX,pomocna);
			pozicijaY=stranica-1;
			brojPoteza++;
		}
	}
	
	public void pokreniCheat(){
		int z=1;
		for (int i=0; i<stranica; i++){
			for (int j=0; j<stranica; j++){
				matrica.get(i).set(j,z++);
			}
		}
		matrica.get(stranica-1).set(stranica-1,0);
	}
	
	public boolean daLiJeIgraGotova(){
		for (int i=0; i<stranica-1; i++)
			for (int j=0; j<stranica; j++)
				if (matrica.get(i).get(j)!=i*stranica+j + 1) return false;
		for (int j=0; j<stranica-1; j++)
			if (matrica.get(stranica-1).get(j)!=(stranica-1)*stranica + j + 1) return false;
		if (matrica.get(stranica-1).get(stranica-1)!=0) return false;
		return true;
	}
	
	public int vratiBrojPoteza(){
		return brojPoteza;
	}
	
	public int vratiStranicu(){
		return stranica;
	}
	
	public int element(int i, int j){
		return matrica.get(i).get(j);
	}
	
	public void foo(){
		for (int i=0; i<stranica*stranica; i++){
			System.out.println("Broj:" + i + " Poz: (" + xHash[i] + "," + yHash[i] + ")");
		}
	}
}
