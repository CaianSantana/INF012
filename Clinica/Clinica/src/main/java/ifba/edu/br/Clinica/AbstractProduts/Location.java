package ifba.edu.br.Clinica.AbstractProduts;

import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.enums.Specialty;

public interface Location {
		
		public String getPublicPlace();
		public String getNeighborhood();		
		public String getCity();		
		public String getState();				
		public void setPublicPlace(String publicPlace);				
		public void setNeighborhood(String neighborhood);		
		public void setCity(String city);		
		public void setState(String state);		
		
}
		
	

		

