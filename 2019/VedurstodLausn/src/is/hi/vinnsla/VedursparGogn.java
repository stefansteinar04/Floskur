/*
Ebba Þóra Hvannberg ebba@hi.is
 */

package is.hi.vinnsla;

import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * Háskóli Íslands
 */
public class VedursparGogn {
    private Nidurstodur ollGognin;
    private ArrayList<Nidurstodur.AllarStodvar.Vedurstod> allarStodvar; // listi af veðurstöðvum - hér til þæginda

    private final String VEDURSPARXML="vedur.xml";
    private final String VILLA_Í_LESTRI_Á_XML_SKRÁ="Villa í lestri á XML skrá";
    
    /**
     * Smiður sem les inn XML skrána Sjonvarpsdagskrá
     * Birtir villu ef XML skrá fannst ekki 
     */
    public VedursparGogn() {
        InputStream inputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Nidurstodur.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            inputStream = VedursparGogn.class.getResourceAsStream(VEDURSPARXML);

            ollGognin = (Nidurstodur) jaxbUnmarshaller.unmarshal(inputStream);
            allarStodvar = (ArrayList<Nidurstodur.AllarStodvar.Vedurstod>) ollGognin.getAllarStodvar().getVedurstod();

        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println(VILLA_Í_LESTRI_Á_XML_SKRÁ);
        }

    }
    /**
     * Skilar öllum gögnunum 
     * @return 
     */
    public Nidurstodur getNidurstodur() {
        return ollGognin;
    }

    /**
     * Skilar öllum veðurstöðvum þ.m.t. veðurspám þeirra 
     * @return 
     */
    public ArrayList<Nidurstodur.AllarStodvar.Vedurstod> getAllarStodvar() {
        return allarStodvar;
    }
    
    /**
     * Prentar út veðurspá á console. 
     * Getur verið gagnlegt til að sjá hvort gögnin hafa komist inn 
    */
    public void birtaVedurstodvar() {
         for (Nidurstodur.AllarStodvar.Vedurstod s:allarStodvar) {
            System.out.println("  nafn Stöðvar "+s.getName());
        }
    }
        
}
