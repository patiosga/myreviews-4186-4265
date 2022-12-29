package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Accommodation implements Serializable {

    private float avgRating;
    private int totalEvaluations; //avgRating και totalEvaluations αλλάζουν κάθε φορά που προστίθεται αξιολόγηση στο κατάλυμα
    private final long singularId; //Για να αποφευχθούν προβλήματα συνωνυμίας
    private final Provider provider;
    private String name;
    private String description;
    private String stayType; //ξενοδοχείο, διαμέρισμα, μεζονέτα --> hotel, apartment, maisonette
    private Location place;

    //Παροχές - Προκαθορισμένες σε μορφή checklist σε συνεργασία με το GUI --> ένας θεος ξέρει πως
    private ArrayList<Utility> typesOfUtilities;

    public Accommodation(String name, String description, String stayType, Location location, Provider provider) {
        this.name = name;
        this.description = description;
        this.stayType = stayType;
        totalEvaluations = 0;
        place = location;
        this.provider = provider;
        singularId = provider.getUserName().hashCode() + name.hashCode(); //αποκλείουμε το γεγονός ο ίδιος πάροχος να έχει δύο καταλύματα με το ίδιο όνομα οπότε το id του καταλύματος είναι μοναδικό
        avgRating = 0;

        typesOfUtilities = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStayType() {
        return stayType;
    }

    public void setStayType(String stayType) {
        this.stayType = stayType;
    }

    public Location getLocation() { return place; }

    public void setPlace(Location place) {
        this.place = place;
    }

    public Provider getProvider() {
        return provider;
    }

    public long getSingularId() {
        return singularId;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public int getTotalEvaluations() {
        return totalEvaluations;
    }

    public void setTotalEvaluations(int totalEvaluations) {
        this.totalEvaluations = totalEvaluations;
    }

    public void updateAvgRatingOfAccomodation(HashSet<Evaluation> evaluations) {
        if (evaluations.size() == 0) {
            avgRating = 0;
            return;
        }
        float totalSum = 0;
        int numOfEvaluations = 0;
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getAccommodation().getSingularId() == this.singularId) {
                totalSum += evaluation.getGrade();
                numOfEvaluations++;
            }
        }
        if (numOfEvaluations == 0) {
            avgRating = 0;
            totalEvaluations = 0;
            return;
        }
        totalEvaluations = numOfEvaluations;
        avgRating = totalSum / numOfEvaluations;
    }


    /**
     * Για να μπορούν να προστεθούν και άλλα types στο μέλλον, π.χ. δωμάτιο σπα. Δεν πρόκειται να χρειαστεί η
     * αφαίρεση π.χ. της παροχής view οπότε για την ώρα δεν υλοποιείται μέθοδος remove.
     * @param objUtil ο νέος τύπος παροχής που προστίθεται
     * @return επιστρέφει true αν το αντικείμενο δεν υπήρχε ήδη και προστέθηκε τώρα
     */
    public boolean addUtilityType(Utility objUtil) {
        if (!typesOfUtilities.isEmpty()) {
            if (typesOfUtilities.contains(objUtil))
                return false;
        }
        typesOfUtilities.add(objUtil);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accommodation that)) return false;
        return getSingularId() == that.getSingularId();
    }
}
