package biblioteca.util;

import biblioteca.model.Carte;

public class Validator {
	
	public static boolean isStringOK(String s) throws Exception{
		boolean flag = s.matches("[a-zA-Z ]+");
		if(flag == false)
			throw new Exception("String invalid");
		return flag;
	}
	
	public static void validateCarte(Carte c)throws Exception{
		if(c.getCuvinteCheie()==null){
			throw new Exception("Lista cuvinte cheie vida!");
		}
		if(c.getReferenti()==null){
			throw new Exception("Lista autori vida!");
		}
		if(!isOKString(c.getTitlu()))
			throw new Exception("Titlu invalid!");
		for(String s:c.getReferenti()){
			if(!isOKString(s))
				throw new Exception("Autor invalid!");
		}
		for(String s:c.getCuvinteCheie()){
			if(!isOKString(s))
				throw new Exception("Cuvant cheie invalid!");
		}
		if(c.getAnAparitie() < 1 || c.getAnAparitie() > 2018)
			throw new Exception("An invalid!");
	}
	
	public static boolean isNumber(String s){
		return s.matches("[0-9]+");
	}
	
	public static boolean isOKString(String s){
		String []t = s.split(" ");
		if(t.length==2){
			boolean ok1 = t[0].matches("[a-zA-Z]+");
			boolean ok2 = t[1].matches("[a-zA-Z]+");
			return ok1 && ok2;
		}
		return s.matches("[a-zA-Z]+");
	}
	
}
