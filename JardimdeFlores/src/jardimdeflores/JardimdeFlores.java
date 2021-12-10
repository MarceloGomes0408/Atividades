
package jardimdeflores;

/**
 *
 * @author Marcelo Gomes
 */
public class JardimdeFlores {

    public static void main(String[] args) {
        
       
         //8 petalas
        Petala p1 = new Petala();
        p1.setCorPetala("Preta");
                
        Petala p2 = new Petala();
        p2.setCorPetala("Branca");   
        
        Petala p3 = new Petala();
        p3.setCorPetala("Cinza"); 
        
        Petala p4 = new Petala();
        p4.setCorPetala("Ouro"); 
        
        Petala p5 = new Petala();
        p5.setCorPetala("Violeta"); 
        
        Petala p6 = new Petala();
        p6.setCorPetala("Bege"); 
        
        Petala p7 = new Petala();
        p7.setCorPetala ("Prata"); 
        
        Petala p8 = new Petala();
        p8.setCorPetala("Marron"); 
        
        //4 flores
        Flor violeta = new Flor();
        violeta.setPetala1(p1);
        
        Flor Lirio = new Flor();
        Lirio.setPetala2(p2);
        
        Flor Azaleia = new Flor();
        Azaleia.setPetala3 (p3);
        
        Flor Orquideas = new Flor();
        Orquideas.setPetala4(p4);
        
                   
        //2 jardins
        Jardim j1 = new Jardim();
        j1.setFlor1(violeta);
        j1.setFlor2(Lirio);
        
        Jardim j2 = new Jardim();
        j2.setFlor1(Azaleia);
        j2.setFlor2(Orquideas);
        
        //1 praÃ§a
        PracaBonita pracinha = new PracaBonita(); 
        pracinha.setJ1(j1);
        
        System.out.println("A Praca tem dois jardins: "); 
        System.out.println("No primeiro jardim tem as flores: "+violeta.getPetala1().getCorPetala()
        + " e " + p8.getCorPetala() + ", "+ Lirio.getPetala2().getCorPetala() + " e " +p3.getCorPetala());
        System.out.println("No segundo jardim te as flores: "+Azaleia.getPetala3().getCorPetala() + " e " 
        + p5.getCorPetala() + ", "+ Orquideas.getPetala4().getCorPetala()+ " e " +p7.getCorPetala());
    }
    
    
}
