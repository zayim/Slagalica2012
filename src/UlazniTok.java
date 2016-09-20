import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UlazniTok {
	public String unesiString() {
		String unos = new String();
		try {
			BufferedReader ulazniTok = new BufferedReader(
					new InputStreamReader(System.in));
			unos = ulazniTok.readLine();
			if (unos.length() == 0)
				return null;
		} catch (IOException e) {
			System.out.println("Izuzetak prilikom unosa stringa!");
		}
		return unos;
	}

	public int unesiInt() {
		String tmp = unesiString();
		int broj=0;
		if (tmp==null) return 0;
		else try{
			 broj = Integer.parseInt(tmp);
		}
		catch(Exception e){
			broj=unesiInt();
		}
		return broj;
	}
}
