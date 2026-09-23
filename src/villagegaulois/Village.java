package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	private class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {

			int i = 0;
			while(etals[i].isEtalOccupe()) {
				i++;
			}
			if(!etals[i].isEtalOccupe()) {
				return i;
			}
			
			return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			Etal[] etalsAvecProduit;
			
			int i = 0, j = 0;
			while(etals[i].isEtalOccupe() || !etals[i].isEtalOccupe()) {
				try {
					  
					if(etals[i].contientProduit(produit)) {
						i++;
					}
					
				}
				catch(ArrayIndexOutOfBoundsException e){
					break;	
				}
			}
			
			etalsAvecProduit = new Etal[i];
			i = 0;
			
			while(etals[i].isEtalOccupe() || !etals[i].isEtalOccupe()) {
				try {
					  
					if(etals[i].contientProduit(produit)) {
						etalsAvecProduit[j] = etals[i];
						j++;
						i++;
					}
					
				}
				catch(ArrayIndexOutOfBoundsException e){
					break;	
				}
			}
		
			return etalsAvecProduit;
		}

		
		Etal trouverVendeur(Gaulois gaulois) {
			
			int i = 0;
			while(etals[i].isEtalOccupe() || !etals[i].isEtalOccupe()) {
				try {
					  
					if(etals[i].getVendeur() == gaulois) {
						return etals[i];
					}
					i++;
					
				}
				catch(ArrayIndexOutOfBoundsException e){
					break;	
				}
			}
			
			return null;
		}
		
		void afficehrMarcher() {
			
			for (int i = 0;etals[i].isEtalOccupe() || !etals[i].isEtalOccupe(); i++) {
				try {  
					if(etals[i].isEtalOccupe()) {
						System.out.println(etals[i].getVendeur().getNom() + " vend " + etals[i].afficherEtal().replace("L'étal de " + etals[i].getVendeur().getNom() + " est garni de ", ""));	
					}
				}
				catch(ArrayIndexOutOfBoundsException e){
					break;	
				}
			}
			
		}
		
		
	}
}













